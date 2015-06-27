package com.goeuro.gocity;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.goeuro.gocity.client.RequestCity;
import com.goeuro.gocity.client.RequestCityException;
import com.goeuro.gocity.client.RequestCityImpl;
import com.goeuro.gocity.data.City;
import com.goeuro.gocity.writer.CSVCityWriter;
import com.goeuro.gocity.writer.CityWriter;
import com.goeuro.gocity.writer.CityWriterException;

@Log4j
@SpringBootApplication
public class GoCityApp implements CommandLineRunner {
	
	RequestCity requestCity;
	CityWriter cityWriter;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GoCityApp.class);
		app.setShowBanner(false);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		requestCity = new RequestCityImpl();
		
		for (String key : arg0) {
			log.debug("received search key "+key);
			try {
				List<City> cityList = requestCity.request(key);
				log.debug("city list retrieved");
				cityWriter = new CSVCityWriter(); 
				cityWriter.createFile(key, cityList);
				log.debug("file created");
			} catch (RequestCityException e) {
				log.error("Problem to retrieve cities", e);
			} catch (CityWriterException e) {
				log.error("Problem to write city file", e);
			}
		}
		
	}
}
