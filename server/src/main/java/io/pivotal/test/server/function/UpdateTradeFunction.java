package io.pivotal.test.server.function;

import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.pdx.PdxInstance;
import org.apache.geode.pdx.WritablePdxInstance;

import java.math.BigDecimal;

public class UpdateTradeFunction implements Function {

  @Override
  public void execute(FunctionContext context) {
    RegionFunctionContext rfc = (RegionFunctionContext) context;
    Object key = rfc.getFilter().iterator().next();
    Object[] arguments = (Object[]) context.getArguments();
    int shares = (Integer) arguments[0];
    BigDecimal price = (BigDecimal) arguments[1];
    PdxInstance tradePdx = (PdxInstance) rfc.getDataSet().get(key);
    if (tradePdx != null) {
      WritablePdxInstance tradeWritablePdx = tradePdx.createWriter();
      tradeWritablePdx.setField("shares", shares);
      tradeWritablePdx.setField("price", price);
      rfc.getDataSet().put(key, tradeWritablePdx);
    }
    context.getResultSender().lastResult(true);
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
