package com.wedul.wedul_timeline.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
@ComponentScan("com.wedul.wedul_timeline.core")
@EntityScan("com.wedul.wedul_timeline.core.entity")
@EnableJpaRepositories(basePackages = "com.wedul.wedul_timeline.core")
public class WedulTimeLineBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedulTimeLineBatchApplication.class, args);
	}

}
