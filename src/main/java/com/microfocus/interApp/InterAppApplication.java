package com.microfocus.interApp;

import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InterAppApplication extends SpringBootServletInitializer {

 	public static Logger logger;

 	static  {
		logger = LoggerFactory.getLogger(InterAppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(InterAppApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(InterAppApplication.class);
	}
}
