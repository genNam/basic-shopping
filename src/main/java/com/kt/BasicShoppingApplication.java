package com.kt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BasicShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicShoppingApplication.class, args);
	}

}
