package com.example.desserts;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.example.desserts.*"})
@ComponentScan(basePackages = { "com.example.desserts.*" })
public class DessertsMicroserviceApplication {

	private final static Logger log=LoggerFactory.getLogger(DessertsMicroserviceApplication.class);
	public static void main(String[] args) {
		log.info("START");
		SpringApplication.run(DessertsMicroserviceApplication.class, args);
		log.info("END");
	}
}
