package com.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.catalog")
@SpringBootApplication
@EnableEurekaClient
public class ProductcatalogmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogmsApplication.class, args);
	}

}
