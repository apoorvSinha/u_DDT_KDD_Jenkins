package com.apoorv.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException {
		
		Properties config = new Properties();
		Properties OR = new Properties();
		
		//load configurations
		FileInputStream fis = new FileInputStream(".//src/test/resources/properties/config.propeties");
		config.load(fis);
		
		//load Object repository
		fis = new FileInputStream(".//src/test/resources/properties/OR.properties");
		OR.load(fis);
		
		config.getProperty("browser");
	}
}
