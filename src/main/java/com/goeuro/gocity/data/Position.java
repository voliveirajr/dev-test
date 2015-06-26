package com.goeuro.gocity.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {
	
	private Double latitude;
	private Double longitude;	

}
