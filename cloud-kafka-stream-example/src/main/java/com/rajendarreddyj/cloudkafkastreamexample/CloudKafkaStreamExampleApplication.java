package com.rajendarreddyj.cloudkafkastreamexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
public class CloudKafkaStreamExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudKafkaStreamExampleApplication.class, args);
	}
}
