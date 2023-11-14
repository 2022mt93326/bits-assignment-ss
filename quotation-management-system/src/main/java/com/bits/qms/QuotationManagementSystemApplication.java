package com.bits.qms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuotationManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementSystemApplication.class, args);
	}

}
