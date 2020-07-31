package net.mobon.externalbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableBatchProcessing
@SpringBootApplication
public class ExternalBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExternalBatchApplication.class, args);
  }

}
