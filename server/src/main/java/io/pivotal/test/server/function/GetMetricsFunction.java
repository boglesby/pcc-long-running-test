package io.pivotal.test.server.function;

import io.pivotal.test.server.metrics.MetricsHelper;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.util.Map;
import java.util.TreeMap;

public class GetMetricsFunction implements Function {

  @Override
  public void execute(FunctionContext context) {
    Map allMetrics = new TreeMap();
    MetricsHelper.addOSMetrics(allMetrics);
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
