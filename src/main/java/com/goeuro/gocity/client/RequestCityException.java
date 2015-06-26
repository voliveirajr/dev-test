package com.goeuro.gocity.client;

public class RequestCityException extends Exception{
	
	private static final long serialVersionUID = -5128698032477986583L;

	public RequestCityException(String message, Throwable e){
		super(message, e);
	}
	
}
