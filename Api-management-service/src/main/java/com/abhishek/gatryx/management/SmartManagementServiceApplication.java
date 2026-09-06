package com.abhishek.gatryx.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class SmartManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartManagementServiceApplication.class, args);
	}

}
