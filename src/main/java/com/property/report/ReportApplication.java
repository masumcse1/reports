package com.property.report;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(
		title = "Property report API",
		version = "1.0"
)
)
@Slf4j
@EnableScheduling
public class ReportApplication {

	public static void main(String[] args) {

		long maxMemory = Runtime.getRuntime().maxMemory();
		log.info("Max Memory: " + maxMemory / (1024 * 1024) + " MB");

		SpringApplication.run(ReportApplication.class, args);
	}

}
