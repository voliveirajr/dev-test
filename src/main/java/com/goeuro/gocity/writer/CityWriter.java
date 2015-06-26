package com.goeuro.gocity.writer;

import java.util.List;

import com.goeuro.gocity.data.City;

public interface CityWriter {
	
	/**
	 * Create a file using city as name to store all returned cities from list
	 * @param city City used as a key to search cities
	 * @param cityList Result of cities
	 * @throws CityWriterException
	 */
	public void createFile(String city, List<City> cityList) throws CityWriterException;

}
