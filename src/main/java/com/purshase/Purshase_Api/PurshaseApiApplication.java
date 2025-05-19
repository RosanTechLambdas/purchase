package com.purshase.Purshase_Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoAuditing(auditorAwareRef = "auditorAware")
public class PurshaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurshaseApiApplication.class, args);
	}

}
