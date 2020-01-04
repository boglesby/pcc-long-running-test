package io.pivotal.test.client.cq;

import org.apache.geode.cache.query.CqEvent;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;
import org.springframework.data.gemfire.listener.annotation.ContinuousQuery;
import org.springframework.stereotype.Component;

@Component
public class TradeProcessor {

  private static final Logger logger = LogService.getLogger();

  @ContinuousQuery(name = "TradeProcessor", query = "SELECT * FROM /Trades WHERE cusip = 'AAPL'")
  public void processTrade(CqEvent event) {
    logger.info("TradeProcessor.processTrade processing event=" + event);
  }
}
