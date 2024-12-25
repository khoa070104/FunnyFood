package com.khoana.funnyfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FunnyfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunnyfoodApplication.class, args);
	}

}
