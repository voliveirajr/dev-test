package com.goeuro.gocity.client;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.goeuro.gocity.data.City;

@Log4j
public class RequestCityImpl implements RequestCity{
	
	@Getter
	private static String URL = "http://api.goeuro.com/api/v2/position/suggest/en/Berlin";
	@Getter@Setter
	private  RestTemplate restTemplate = new RestTemplate();
	
	public List<City> request(String city){
		ParameterizedTypeReference<List<City>> responseType = new ParameterizedTypeReference<List<City>>() {
		};
		ResponseEntity<List<City>> result = restTemplate.exchange(
				URL,
				HttpMethod.GET, null, responseType);

		List<City> cityList = result.getBody();
		log.debug(new StringBuilder("Found ").append(cityList.size()).append(" cities"));
		return cityList;		
	}
}
