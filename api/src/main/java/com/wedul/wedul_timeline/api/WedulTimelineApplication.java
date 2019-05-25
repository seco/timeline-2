package com.wedul.wedul_timeline.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.wedul.wedul_timeline.core")
public class WedulTimelineApplication {

  public static void main(String[] args) {
    SpringApplication.run(WedulTimelineApplication.class, args);
  }

}
