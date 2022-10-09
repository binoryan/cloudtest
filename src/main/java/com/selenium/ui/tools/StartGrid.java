package com.selenium.ui.tools;

import java.io.IOException;

import org.testng.annotations.Test;

public class StartGrid {
	
	protected String currentDir = System.getProperty("user.dir");
	protected String serverPath = currentDir + "\\Grid\\selenium-server-standalone-3.141.59.jar";
	protected String driverPath = currentDir + "\\src\\main\\resources\\drivers\\chromedriver.exe";
	protected String batchPath =  currentDir + "\\src\\main\\resources\\start.bat";
	
	@Test	
	public  void startGrid() throws IOException {
	
		Runtime.getRuntime().exec(new String[]{batchPath, serverPath, driverPath});

		//Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start C:\\Users\\bj079921\\Documents\\commerce_selenium_v8\\v8-hds-selenium\\src\\test\\resources\\start.bat"});
		//Runtime.getRuntime().exec(new String[]{"C:\\Users\\bj079921\\Documents\\commerce_selenium_v8\\v8-hds-selenium\\src\\test\\resources\\start.bat"});
	}
	

}

