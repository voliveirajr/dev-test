package com.goeuro.gocity.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.goeuro.gocity.data.City;

public class ResquestCityImplTest {

	@Test
	public void testListWithRestTemplate() throws Exception {

		String json = getSampleJsonData();

		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(RequestCityImpl.getURL().concat("Foo"))).andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

		RequestCityImpl requestCityImpl = new RequestCityImpl();
		requestCityImpl.setRestTemplate(restTemplate);
		List<City> cities = requestCityImpl.request("Foo");

		Assert.assertTrue(cities.size() == 2);
		//{\"_id\":376217,\"key\":xxx,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":AAA,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}
		for (City city : cities) {
			Assert.assertEquals(0, new Integer(376217).compareTo(city.getId()));
			Assert.assertEquals("Berlin", city.getName());
			Assert.assertEquals("location", city.getType());
			Assert.assertNotNull(city.getGeoPosition());
			Assert.assertEquals(0, new Double(52.52437d).compareTo(city.getGeoPosition().getLatitude()));
			Assert.assertEquals(0, new Double(13.41053d).compareTo(city.getGeoPosition().getLongitude()));
		}
	}
	
	@Test(expected = RequestCityException.class)
	public void test400() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(RequestCityImpl.getURL().concat("Foo"))).andRespond(withStatus(HttpStatus.FORBIDDEN));

		RequestCityImpl requestCityImpl = new RequestCityImpl();
		requestCityImpl.setRestTemplate(restTemplate);
		requestCityImpl.request("Foo");
	}
	
	@Test(expected = RequestCityException.class)
	public void testWithError() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(RequestCityImpl.getURL().concat("Foo"))).andRespond(withServerError());

		RequestCityImpl requestCityImpl = new RequestCityImpl();
		requestCityImpl.setRestTemplate(restTemplate);
		requestCityImpl.request("Foo");
	}
	
	@Test
	public void testNoContent() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(RequestCityImpl.getURL().concat("Foo"))).andRespond(withNoContent());

		RequestCityImpl requestCityImpl = new RequestCityImpl();
		requestCityImpl.setRestTemplate(restTemplate);
		List<City> cities = requestCityImpl.request("Foo");
		
		Assert.assertTrue(cities.isEmpty());
	}

	private String getSampleJsonData() {
		return "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";
	}

}
