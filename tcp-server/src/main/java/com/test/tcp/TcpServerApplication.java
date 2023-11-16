package com.test.tcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpServerApplication.class, args);
	}

}
