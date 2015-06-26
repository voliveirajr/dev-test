package com.goeuro.gocity.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	
	//private int id;//TODO:those ids are always the same?
	private Integer locationId;
	private String name;
	private String fullName;
	private String airport;
	private String type;//TODO:this could be an Enum if I know each possible value
	private String country;
	private Boolean inEurope;
	private String countryCode;
	private Boolean coreCountry;
	private String distance;
	private Position geoPosition;

}
