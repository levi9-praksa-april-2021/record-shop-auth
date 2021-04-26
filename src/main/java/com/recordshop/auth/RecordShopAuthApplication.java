package com.recordshop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RecordShopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopAuthApplication.class, args);
	}

}
