package com.selenium.ui.listener;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EbizEventListener implements WebDriverEventListener {
	

    public Logger Log = Logger.getLogger(this.getClass().getSimpleName());

	public void beforeClickOn(WebElement arg0, WebDriver driver) {
		Log.info("Before Clicking On " + arg0.toString());
	}

	public void afterClickOn(WebElement arg0, WebDriver driver) {
		Log.info("After Clicking On Element" + arg0.toString() );
	}

	
	public void afterChangeValueOf(WebElement arg0, WebDriver driver, CharSequence[] ch) {
		Log.info(
				"Element: \"" + arg0.getAttribute("placeholder") + "\" was updated to: " + arg0.getAttribute("value"));
	}

		
	public void beforeNavigateTo(String url, WebDriver driver) {
		Log.info("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		Log.info("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		Log.info("Value of the:" + element.toString()
				+ " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		Log.info("Element value changed to: " + element.toString());
	}

	
	public void beforeNavigateBack(WebDriver driver) {
		Log.info("Navigating back to previous page "+  driver.getCurrentUrl());
	}

	public void afterNavigateBack(WebDriver driver) {
		Log.info("Navigated back to previous page " + driver.getCurrentUrl());
	}

	public void beforeNavigateForward(WebDriver driver) {
		Log.info("Navigating forward to next page " + driver.getCurrentUrl());
	}

	public void afterNavigateForward(WebDriver driver) {
		Log.info("Navigated forward to next page "+driver.getCurrentUrl());
	}

	public void onException(Throwable error, WebDriver driver) {
		error.printStackTrace();
		Log.error(error);
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		Log.info("Trying to find Element By : " + by.toString()   );
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		Log.info("Found Element By : " + by.toString());
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Implement this Method
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Implement this Method
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Implement this Method
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Implement this Method
	}
	

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}


	
	
}