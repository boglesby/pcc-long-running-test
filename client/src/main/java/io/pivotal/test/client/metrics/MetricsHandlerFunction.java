package io.pivotal.test.client.metrics;

import io.pivotal.test.metrics.Metric;

@FunctionalInterface
public interface MetricsHandlerFunction {

  void process(String serverName, Metric metric);
}
