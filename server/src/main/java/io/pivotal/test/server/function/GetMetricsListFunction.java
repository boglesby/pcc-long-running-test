package io.pivotal.test.server.function;

import io.pivotal.test.metrics.Metric;
import io.pivotal.test.metrics.MetricsHelper;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.util.ArrayList;
import java.util.List;

public class GetMetricsListFunction implements Function {

  @Override
  public void execute(FunctionContext context) {
    List<Metric> allMetrics = new ArrayList<>();
    MetricsHelper.addStatisticsMetrics(allMetrics);
    MetricsHelper.addGCMetrics(allMetrics);
    MetricsHelper.addMemoryMetrics(allMetrics);
    context.getResultSender().lastResult(allMetrics);
  }

  @Override
  public String getId() {
    return getClass().getSimpleName();
  }

  @Override
  public boolean optimizeForWrite() {
    return true;
  }
}
