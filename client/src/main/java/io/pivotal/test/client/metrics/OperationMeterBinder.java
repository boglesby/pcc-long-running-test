package io.pivotal.test.client.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.pivotal.test.client.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;

public class OperationMeterBinder implements MeterBinder {

  @Autowired
  private TradeService service;

  @Override
  public void bindTo(MeterRegistry registry) {
    Gauge
      .builder("client.puts", service, TradeService::getPutOperations)
      .description("Number of put operations")
      .register(registry);

    Gauge
      .builder("client.gets", service, TradeService::getGetOperations)
      .description("Number of get operations")
      .register(registry);

    Gauge
      .builder("client.destroys", service, TradeService::getDestroyOperations)
      .description("Number of destroy operations")
      .register(registry);

    Gauge
      .builder("client.queries.by.cusip", service, TradeService::getQueryByCusipOperations)
      .description("Number of queries by cusip operations")
      .register(registry);

    Gauge
      .builder("client.function.updates", service, TradeService::getFunctionUpdateOperations)
      .description("Number of function update operations")
      .register(registry);
  }
}
