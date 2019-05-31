package com.wedul.wedul_timeline.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class WedulTimelineBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedulTimelineBatchApplication.class, args);
	}

}
