package com.goeuro.gocity.writer;

import java.util.List;

import com.goeuro.gocity.data.City;

public interface CityWriter {
	
	public void createFile(List<City> cityList) throws CityWriterException;

}
