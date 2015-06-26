package com.goeuro.gocity.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.goeuro.gocity.data.City;

public class ResquestCityImplTest {

	@Test
	public void testListWithRestTemplate() throws Exception {

		String json = getSampleJsonData();

		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer
				.createServer(restTemplate);
		mockServer.expect(requestTo(RequestCityImpl.getURL())).andRespond(
				withSuccess(json, MediaType.APPLICATION_JSON));

		RequestCityImpl requestCityImpl = new RequestCityImpl();
		requestCityImpl.setRestTemplate(restTemplate);
		List<City> cities = requestCityImpl.request("Foo");

		Assert.assertTrue(cities.size() == 2);
	}

	private String getSampleJsonData() {
		return "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":448103,\"key\":null,\"name\":\"Berlingo\",\"fullName\":\"Berlingo, Italy\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Italy\",\"geo_position\":{\"latitude\":45.50298,\"longitude\":10.04366},\"locationId\":147721,\"inEurope\":true,\"countryCode\":\"IT\",\"coreCountry\":true,\"distance\":null}]";
	}

}
