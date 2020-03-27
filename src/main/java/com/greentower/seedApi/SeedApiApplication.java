package com.greentower.seedApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SeedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeedApiApplication.class, args);
	}
}
