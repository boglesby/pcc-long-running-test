package io.pivotal.test.metrics;

import java.io.Serializable;

public class Metric implements Serializable {

  private String name;

  private Number value;

  private boolean isCounter;

  private static final long serialVersionUID = 1L;

  public Metric(String name, Number value, boolean isCounter) {
    this.name = name;
    this.value = value;
    this.isCounter = isCounter;
  }

  public String getName() {
    return this.name;
  }

  public Number getValue() {
    return this.value;
  }

  public boolean isCounter() {
    return this.isCounter;
  }
}
