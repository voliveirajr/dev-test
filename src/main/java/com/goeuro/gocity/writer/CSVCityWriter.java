package com.goeuro.gocity.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import com.goeuro.gocity.data.City;

@Log4j
public class CSVCityWriter implements CityWriter{
	
	@Setter
	private Writer writer = null;

	@Override
	public void createFile(List<City> cityList) throws CityWriterException {
		
		if(cityList == null){
			log.warn("Null list, nothing to print");
		}
		
		try {
			if (writer == null) {//for mock purposes
				writer = new PrintWriter("cities.csv", "UTF-8");
			}			
			for (City city : cityList) {
				log.debug("printing city:"+city.getName());
				writer.append(city.getId().toString()).append(',');
				writer.append(city.getName()).append(',');
				writer.append(city.getType());
				if(city.getGeoPosition()!= null){
					writer.append(",");
					writer.append(city.getGeoPosition().getLatitude().toString()).append(',');
					writer.append(city.getGeoPosition().getLatitude().toString());
				}				
				writer.append("\n");	
			}
			writer.close();
			
		} catch (IOException e) {
			throw new CityWriterException("Unable to write file", e);			
		}		
	}
}
