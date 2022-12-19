package com.ethfin.artrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class ArtrepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtrepoApplication.class, args);
	}

}
