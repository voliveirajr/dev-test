package com.goeuro.gocity.writer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.goeuro.gocity.data.City;
import com.goeuro.gocity.data.Position;

@RunWith(PowerMockRunner.class)
public class CSVCityWriterTest {
	
	@Test
	public void testEmptyList() throws Exception {
		
		PrintWriter printWriter = PowerMockito.mock(PrintWriter.class);
		CSVCityWriter csvCityWriter = new CSVCityWriter();
		csvCityWriter.setWriter(printWriter);		
		csvCityWriter.createFile("Foo", new ArrayList<City>());
		
		Assert.assertTrue(true);		
	}
	
	@Test
	public void testList() throws Exception {
		
		City city = new City();
		city.setId(1);
		city.setName("a");
		city.setType("t");
		Position p =new Position();
		p.setLatitude(1d);
		p.setLongitude(1d);
		city.setGeoPosition(p);
		
		PrintWriter printWriter = PowerMockito.mock(PrintWriter.class);
		Mockito.when(printWriter.append(Mockito.anyString())).thenReturn(printWriter);
		
		CSVCityWriter csvCityWriter = new CSVCityWriter();
		csvCityWriter.setWriter(printWriter);		
		csvCityWriter.createFile("Foo", Arrays.asList(city));
		
		Assert.assertTrue(true);		
	}

}
