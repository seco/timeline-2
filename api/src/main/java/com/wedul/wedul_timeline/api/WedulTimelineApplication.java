package com.wedul.wedul_timeline.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.wedul.wedul_timeline")
@EntityScan("com.wedul.wedul_timeline.core.entity")
@EnableJpaRepositories(basePackages = "com.wedul.wedul_timeline.core")
public class WedulTimelineApplication {

  public static void main(String[] args) {
    SpringApplication.run(WedulTimelineApplication.class, args);
  }

}
