package com.selenium.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitTool {



	public  long PAGE_LOAD_TIMEOUT = 20; //200;
	public  long IMPLICIT_WAIT = 20; //70;
	public  final int DEFAULT_WAIT_4_ELEMENT = 150;
	public  final int WAIT_4_ELEMENT = 10;
	private  int defaultWait4Element = 30;
	public  final int DEFAULT_WAIT_4_PAGE = 120; //increased the page load time to cope up with the release 1.31.1 banner load 
	/*private  int defaultWait4Page = 30;
	public  final long DEFAULT_POLLING_4_ELEMENT = 500L;
	private  long defaultPolling4Element = 500L;*/

	public  void setDefaultWait4Element(Integer timeoutInSeconds) {

		defaultWait4Element = timeoutInSeconds == null ? 7 : timeoutInSeconds.intValue();
	}

	public  int getDefaultWait4Element() {
		return defaultWait4Element;
	}


	public  void waitFor(WebDriver driver, boolean pageloadDone) {

		new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}

	public  void nullifyImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
	}

	public  void setImplicitWait(WebDriver driver, int waitTime_InSeconds) {
		driver.manage().timeouts().implicitlyWait((long) waitTime_InSeconds, TimeUnit.SECONDS);
	}



	public  void resetImplicitWait(WebDriver driver, int newWaittime_InSeconds) {

		driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
		driver.manage().timeouts()
				.implicitlyWait((long) newWaittime_InSeconds, TimeUnit.SECONDS);
	}

	public  boolean pageLoadDone(WebDriver driver) {
		new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		return true;
	}

	public  void waitForElementByLocator(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public  void waitElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public  void waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element + "is visible now....");
	}
	

	public  void waitForElment(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element + "is visible now....");
	}
	
	public  void waitForAlert(WebDriver driver) {
		//WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println( "alertIsPresent is visible now....");
	}


	public  void waitForText(WebDriver driver, WebElement element,
								   String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public  void hardWait() throws Exception {
		Thread.sleep(3000);

	}

	public  void waitVisibility(WebDriver driver, WebElement element,
									  int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public  void ElementToBeClickable(WebDriver driver, WebElement element,
											int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public  void ElementPresence(WebDriver driver, WebElement element,
									   int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}

	public  void waitVisibilityOfElementLocated(WebDriver driver,
													  WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}

	public  void setImplicity(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void waitForFrameToBeAvailiableAndSwithTo(WebDriver driver,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver,DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	public void waitForInvisibilityOfElement(WebDriver driver, WebElement element){
		WebDriverWait wait=new WebDriverWait(driver,DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public boolean waitForJStoLoad(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

					return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");

			}
		};
		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver)
						.executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public void waitForAjax(WebDriver driver) throws InterruptedException{
		while (true)
		{
			Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
			if (ajaxIsComplete){
				System.out.println("Ajax Call completed. ");
				break;
			}
			Thread.sleep(150);
		}
	}

}
