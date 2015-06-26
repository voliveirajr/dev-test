package com.goeuro.gocity;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.goeuro.gocity.data.City;

@Log4j
@SpringBootApplication
public class GoCityApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GoCityApp.class);
		app.setShowBanner(false);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
