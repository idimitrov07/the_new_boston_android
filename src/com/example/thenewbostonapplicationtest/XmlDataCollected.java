package com.example.thenewbostonapplicationtest;

public class XmlDataCollected {

	int temperature = 0;
	String city = null;

	public void setCity(String c) {
		city = c;
	}

	public void setTemp(double t) {
		temperature = (int)t;
	}

	public String dataToString() {
		return "In " + city + " the current temperature in C is " + temperature
				+ " degrees";
	}
}
