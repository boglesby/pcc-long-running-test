package io.pivotal.test.client.controller;

import io.pivotal.test.client.metrics.MetricsProvider;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static io.pivotal.test.client.Constants.METRICS_PATH;

@RestController
public class AdminController {

  @Autowired
  MetricsProvider provider;

  @GetMapping("/pools")
  @ResponseStatus(HttpStatus.OK)
  public String getPools() {
    StringBuilder builder = new StringBuilder();
    Collection<Pool> pools = PoolManager.getAll().values();
    builder
      .append("The client defines the following ")
      .append(pools.size())
      .append(" pools:");
    for (Pool pool : pools) {
      builder
        .append("\n\tname=")
        .append(pool.getName())
        .append("; locators=")
        .append(pool.getLocators());
    }
    return builder.toString();
  }

  @GetMapping(METRICS_PATH)
  @ResponseStatus(HttpStatus.OK)
  public Object getMetrics() {
    return this.provider.getMetrics();
  }
}
