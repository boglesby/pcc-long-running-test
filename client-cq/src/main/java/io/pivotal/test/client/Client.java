package io.pivotal.test.client;

import io.pivotal.test.client.domain.Trade;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableStatistics;

@SpringBootApplication
@EnableEntityDefinedRegions(basePackageClasses = Trade.class)
@EnableStatistics
public class Client {

  public static void main(String[] args) {
    new SpringApplicationBuilder(Client.class)
      .build()
      .run(args);
  }
}
