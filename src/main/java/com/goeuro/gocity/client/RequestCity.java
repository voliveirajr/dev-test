package com.goeuro.gocity.client;

import java.util.List;

import com.goeuro.gocity.data.City;

public interface RequestCity {
	/**
	 * Retrives a list of cities that match with the key informed
	 * @param city key to search city list
	 * @return
	 * @throws RequestCityException
	 */
	public List<City> request(String city) throws RequestCityException;
}
