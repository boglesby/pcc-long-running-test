package io.pivotal.test.client.metrics;

import io.pivotal.test.client.function.AdminFunctions;
import io.pivotal.test.client.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class ServerMetricsProvider {

  @Autowired
  private TradeService service;

  @Autowired
  private AdminFunctions adminFunctions;

  public Map getServerMetrics(String type) {
    Map serverMetrics = null;
    if (type.equals("all") || type.equals("server")) {
      Iterable serverMetricsIter = (Iterable) this.adminFunctions.getMetrics();
      serverMetrics = (Map) serverMetricsIter.iterator().next();
    }
    return serverMetrics;
  }

  private Map getClientMetrics(String type) {
    Map clientMetrics = null;
    if (type.equals("all") || type.equals("client")) {
      clientMetrics = new TreeMap();
      addOperationMetrics(clientMetrics);
      MetricsHelper.addOSMetrics(clientMetrics);
      MetricsHelper.addGCMetrics(clientMetrics);
      MetricsHelper.addMemoryMetrics(clientMetrics);
    }
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

  public Object getMetrics(String type) {
    return new ClientAndServerMetrics(getClientMetrics(type), getServerMetrics(type));
  }

  public int getMetric(String metricName) {
    System.out.println(Thread.currentThread().getName() + ": ServerMetricsProvider.getMetric metricName=" + metricName);
    return 100;
    //return this.serverMetrics.get(metricName).get();
  }
}
