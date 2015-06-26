package com.goeuro.gocity.client;

import java.util.List;

import com.goeuro.gocity.data.City;

public interface RequestCity {
	public List<City> request(String city) throws RequestCityException;
}
