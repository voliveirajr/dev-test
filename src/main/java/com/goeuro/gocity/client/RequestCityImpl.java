package com.goeuro.gocity.client;

import java.util.ArrayList;
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
	private static String URL = "http://api.goeuro.com/api/v2/position/suggest/en/";//TODO:This should be placed on properties file.
	@Getter@Setter
	private  RestTemplate restTemplate = new RestTemplate();
	
	public List<City> request(String city) throws RequestCityException {
		try {
			ParameterizedTypeReference<List<City>> responseType = new ParameterizedTypeReference<List<City>>() {
			};
			ResponseEntity<List<City>> result = restTemplate.exchange(
					new StringBuilder(URL).append(city).toString(),
					HttpMethod.GET, null, responseType);

			List<City> cityList = result.getBody();
			if (cityList == null) {
				log.warn("No city found: " + city);
				return new ArrayList<City>();
			} else {
				log.debug("Found: " + cityList.size() + " cities");
				return cityList;
			}

		} catch (Exception e) {
			throw new RequestCityException("API request not succeded", e);
		}

	}
}
