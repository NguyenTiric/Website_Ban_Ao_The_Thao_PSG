package com.example.website_ban_ao_the_thao_psg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

@SpringBootApplication
@Configuration
@EnableScheduling
public class WebsiteBanAoTheThaoPsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteBanAoTheThaoPsgApplication.class, args);
	}

}
