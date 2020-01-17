package io.pivotal.test.client.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServerMeterBuilder implements MeterBinder {

  @Autowired
  private MetricsProvider provider;

  private static final Logger logger = LoggerFactory.getLogger(ServerMeterBuilder.class);

  @Override
  public void bindTo(MeterRegistry registry) {
    MetricsHandlerFunction function = (serverName, metric) -> {
      Tags tags = Tags.of("member", serverName);
      if (metric.getType() != null) {
        tags = tags.and("type", metric.getType());
      }
      Gauge.Builder builder = Gauge
        .builder(metric.getName(), this.provider, provider -> provider.getServerMetric(serverName, metric))
        .tags(tags);
      if (metric.isMemoryType()) {
        builder.baseUnit(BaseUnits.BYTES);
      }
      builder.register(registry);
      Number metricCounter;
      if (metric.getValue() instanceof Integer) {
        metricCounter = new AtomicInteger((Integer) metric.getValue());
        this.provider.addServerMetric(serverName, metric, metricCounter);
      } else if (metric.getValue() instanceof Long) {
        metricCounter = new AtomicLong((Long) metric.getValue());
        this.provider.addServerMetric(serverName, metric, metricCounter);
      } else if (metric.getValue() instanceof Double) {
        long metricValueL = Double.doubleToLongBits((Double) metric.getValue());
        metricCounter = new AtomicLong(metricValueL);
        this.provider.addServerMetric(serverName, metric, metricCounter);
      }
      logger.info("Bound gauge server={}; metric={}", serverName, metric);
    };
    this.provider.processServerMetrics(function);
  }
}
