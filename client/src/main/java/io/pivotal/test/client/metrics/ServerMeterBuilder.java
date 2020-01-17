package io.pivotal.test.client.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerMetricsBuilder implements MeterBinder {

  @Autowired
  private MetricsProvider provider;

  @Override
  public void bindTo(MeterRegistry registry) {
    MetricsHandlerFunction function = (serverName, metric) -> {
      //Gauge gauge = Gauge.builder(metricName, provider, provider -> provider.getServerMetric(metricName)).register(registry);
      System.out.println(Thread.currentThread().getName() + ": XXX ServerMetricsBuilder.bindTo serverName=" + serverName + "; metric=" + metric);
    };
    this.provider.processServerMetrics(function);
  }
}
