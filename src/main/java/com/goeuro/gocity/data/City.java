package com.goeuro.gocity.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	
	@JsonProperty("_id")
	private Integer id;
	private String name;
	private String type;//TODO:this could be an Enum if I know each possible value
	@JsonProperty("geo_position")
	private Position geoPosition;

}
