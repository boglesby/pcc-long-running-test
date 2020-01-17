package io.pivotal.test.client.metrics;

import io.pivotal.test.client.function.AdminFunctions;
import io.pivotal.test.client.service.TradeService;
import io.pivotal.test.metrics.Metric;
import io.pivotal.test.metrics.MetricsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MetricsProvider {

  @Autowired
  private TradeService service;

  @Autowired
  private AdminFunctions adminFunctions;

  private Map<String,Map<String,Number>> serverMetrics = new ConcurrentHashMap<>();

  private static final Logger logger = LoggerFactory.getLogger(MetricsProvider.class);

  public Map getServerMetrics() {
    Iterable serverMetricsIter = (Iterable) this.adminFunctions.getMetricsMap();
    return (Map) serverMetricsIter.iterator().next();
  }

  public Map getServerMetricsList() {
    Iterable serverMetricsIter = (Iterable) this.adminFunctions.getMetricsList();
    return (Map) serverMetricsIter.iterator().next();
  }

  private Map getClientMetrics() {
    Map clientMetrics = new TreeMap();
    addOperationMetrics(clientMetrics);
    MetricsHelper.addStatisticsMetrics(clientMetrics);
    MetricsHelper.addGCMetrics(clientMetrics);
    MetricsHelper.addMemoryMetrics(clientMetrics);
    return clientMetrics;
  }

  private void addOperationMetrics(Map clientMetrics) {
    Map operationMetrics = new TreeMap();
    clientMetrics.put("operations", operationMetrics);
    operationMetrics.put("puts", this.service.getPutOperations());
    operationMetrics.put("gets", this.service.getGetOperations());
    operationMetrics.put("destroys", this.service.getDestroyOperations());
    operationMetrics.put("cusipQueries", this.service.getQueryByCusipOperations());
    operationMetrics.put("functionUpdates", this.service.getFunctionUpdateOperations());
  }

  public Object getMetrics() {
    return new ClientAndServerMetrics(getClientMetrics(), getServerMetrics());
  }

  public double getServerMetric(String serverName, Metric metric) {
    Map<String,Number> metrics = this.serverMetrics.get(serverName);
    Number currentAtomicValue = metrics.get(metric.getMapName());
    double currentValue = 0;
    if (currentAtomicValue == null) {
      logger.warn("Metric {} cannot be retrieved since it does not exist", metric.getName());
    } else {
      if (currentAtomicValue instanceof AtomicInteger) {
        AtomicInteger ai = (AtomicInteger) currentAtomicValue;
        currentValue = ai.get();
      } else if (currentAtomicValue instanceof AtomicLong && metric.getValue() instanceof Long) {
        AtomicLong al = (AtomicLong) currentAtomicValue;
        currentValue = al.get();
      } else if (currentAtomicValue instanceof AtomicLong && metric.getValue() instanceof Double) {
        AtomicLong al = (AtomicLong) currentAtomicValue;
        currentValue = Double.longBitsToDouble(al.get());
      }
    }
    return currentValue;
  }

  @Scheduled(fixedDelay = 5000)
  public void updateServerMetrics() {
    MetricsHandlerFunction function = (serverName, metric) -> updateServerMetric(serverName, metric);
    processServerMetrics(function);
  }

  public void addServerMetric(String serverName, Metric metric, Number metricValue) {
    Map<String,Number> metrics = this.serverMetrics.get(serverName);
    if (metrics == null) {
      metrics = new ConcurrentHashMap<>();
      this.serverMetrics.put(serverName, metrics);
    }
    metrics.put(metric.getMapName(), metricValue);
  }

  public void updateServerMetric(String serverName, Metric metric) {
    Map<String,Number> metrics = this.serverMetrics.get(serverName);
    Number currentValue = metrics.get(metric.getMapName());
    if (currentValue == null) {
      logger.warn("Metric {} cannot be updated since it does not exist", metric.getName());
    } else {
      Number newValue = metric.getValue();
      if (newValue instanceof Integer && currentValue instanceof AtomicInteger) {
        AtomicInteger ai = (AtomicInteger) currentValue;
        Integer i = (Integer) newValue;
        ai.set(i);
      } else if (newValue instanceof Long && currentValue instanceof AtomicLong) {
        AtomicLong al = (AtomicLong) currentValue;
        Long l = (Long) newValue;
        al.set(l);
      } else if (newValue instanceof Double && currentValue instanceof AtomicLong) {
        AtomicLong al = (AtomicLong) currentValue;
        Long l = Double.doubleToLongBits((Double) newValue);
        al.set(l);
      }
    }
  }

  public void processServerMetrics(MetricsHandlerFunction function) {
    Map<String,List<Metric>> allMetrics = getServerMetricsList();
    for (Map.Entry<String,List<Metric>> metrics : allMetrics.entrySet()) {
      String serverName = metrics.getKey();
      for (Metric metric : metrics.getValue()) {
        function.process(serverName, metric);
      }
    }
  }
}
