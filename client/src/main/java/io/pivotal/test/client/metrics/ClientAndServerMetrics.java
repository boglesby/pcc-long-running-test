package io.pivotal.test.client.metrics;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class ClientAndServerMetrics {

  private Map clientMetrics;

  private Map serverMetrics;

  public ClientAndServerMetrics(Map clientMetrics, Map serverMetrics) {
    this.clientMetrics = clientMetrics;
    this.serverMetrics = serverMetrics;
  }
}
