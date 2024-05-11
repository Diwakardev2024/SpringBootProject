package com.nexgenvision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.nextgenvision.ui.controller.UserController;

@SpringBootApplication
@ComponentScan(basePackages={"com.nexgenvision","com.nextgenvision"})
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}
	

}
