package com.wec;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class WecSpringApplication {

	public static void main(String[] args) {
		val app = new SpringApplication(WecSpringApplication.class);
		val properties = new Properties();
		properties.setProperty("spring.resources.static-locations", "classpath:/static/");
		app.setDefaultProperties(properties);
		app.run(args);
	}
}
