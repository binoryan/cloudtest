package com.selenium.ui.baseclass;

import com.selenium.ui.tools.ConfigFileRead;
import com.selenium.ui.tools.PageManager;
import com.selenium.ui.tools.PropertyLoader;
import com.selenium.ui.tools.WaitTool;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseClassV8_ParallelGrid {
	protected String environmentValue;
	protected String baseUrl;
	protected PropertyLoader propertyRead;
	protected ConfigFileRead configread;
	protected SoftAssert softAssert;
	public static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	protected String currentDir = System.getProperty("user.dir") + "/src/main/resources/drivers/";
	public Logger Log = Logger.getLogger(BaseClassV8_ParallelGrid.class.getName());
	public String buildTag = System.getenv("BUILD_TAG");
	public String testName;

	/**
	 * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
	 */
	private ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<>();


	/**
	 * ThreadLocal variable which contains the Sauce Job Id.
	 */
	protected ThreadLocal<String> sessionId = new ThreadLocal<String>();
	protected WaitTool waitTool;

	//@BeforeMethod

	@DataProvider(name = "browsers",parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][]{

		//	new Object[]{"internet explorer", "11.0", "Windows 7"},
			new Object[]{"chrome", "94.0", "Windows 10"}
		};
	}


	/**
	 * @return the {@link WebDriver} for the current thread
	 */
	public RemoteWebDriver getWebDriver() {
		return webDriver.get();
	}

	/**
	 *
	 * @return the Sauce Job id for the current thread
	 */
	public String getSessionId() {
		return sessionId.get();
	}



	protected void createDriver(String browser, String version, String os, String methodName) throws MalformedURLException,Exception {
		configread = new ConfigFileRead();

		if (configread.getTestEnv().equalsIgnoreCase("remote")) {
			Log.info("Welcome to Remote Execution in Saucelab");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
			ChromeOptions options  = new ChromeOptions();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("profile.default_content_setting.popups", 2);
			map.put("profile.default_content_setting_values.notifications", 2);
			map.put("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			options.setExperimentalOption("prefs", map);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability(CapabilityType.BROWSER_NAME, browser);
			if (version != null) {
				caps.setCapability(CapabilityType.VERSION, version);
			}
			caps.setCapability(CapabilityType.PLATFORM, os);

			caps.setCapability("name", methodName + "__" + browser + "_" + version + "__" + os);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability("screenResolution", "1920x1080");
			if (configread.getTestTunnel().equalsIgnoreCase("remote")) {
				caps.setCapability("tunnelIdentifier", "hdsupply");
			}else if(configread.getTestTunnel().equalsIgnoreCase("local")){
				caps.setCapability("tunnelIdentifier", System.getenv("Tunnel_Identifier"));
			}
			caps.setCapability("extendedDebugging", true);

			if (buildTag != null) {
				caps.setCapability("build", buildTag);
			}
			// caps.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));

			// Launch remote browser and set it as the current thread
			webDriver.set(new RemoteWebDriver(
					new URL("https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub"),
					caps));

			// set current sessionId
			String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
			sessionId.set(id);
			Log.info("Session id is: " + sessionId + "  **** WebDriver.get ***  " + getWebDriver());
			testName = methodName + "__" + browser + "_" + version + "__" + os;
			String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", id, methodName,browser,os,version);
			Log.info("SauceOnDemandSessionID ___" + message);


		}
		else if (configread.getTestEnv().equalsIgnoreCase("local")) {
			Log.info("Welcome to Execution in Local Grid");
			
				DesiredCapabilities caps = new DesiredCapabilities();
				URL url = new URL("http://localhost:4444/wd/hub");
				caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
				ChromeOptions options  = new ChromeOptions();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("profile.default_content_setting.popups", 2);
				map.put("profile.default_content_setting_values.notifications", 2);
				map.put("excludeSwitches", Arrays.asList("disable-popup-blocking"));
				options.setExperimentalOption("prefs", map);
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				caps.setCapability(CapabilityType.BROWSER_NAME, browser);
				caps.setCapability(CapabilityType.VERSION, version);
				// Launch remote browser and set it as the current thread
				webDriver.set(new RemoteWebDriver(url,caps));			
		}
		else if(configread.getTestEnv().equalsIgnoreCase("gcp")){
			Log.info("Welcome to Remote Execution in GCP");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("name", methodName);
			caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
			caps.setCapability("build", 1.2);
			caps.setCapability("recordVideo", true);
			caps.setCapability("idleTimeout", 150);
			caps.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			
			webDriver.set(new RemoteWebDriver(
					new URL("http://" + "bino" + ":" + "Zalenium2022" + "@" + "104.154.99.71" + "/wd/hub"),
					caps));

			// set current sessionId
			//String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
			//sessionId.set(id);
			//Log.info("Session id is: " + sessionId + "  **** WebDriver.get ***  " + getWebDriver());
			testName = methodName + "__" + browser + "_" + version + "__" + os;
		}


		Log.info("Created the driver");
//		propertyRead = new PropertyLoader();
//		baseUrl = propertyRead.getProperty("site.url");
		webDriver.get().manage().deleteAllCookies();
		Log.info("Delete cookies");
//		getURL();
//		getEnvValue();
	//	softAssert = new SoftAssert();
//		webDriver.get().manage().window().maximize();
//		Log.info("Window maximize");

	}

	


	public void getURL(String	baseUrl ) throws InterruptedException{
		webDriver.get().get(baseUrl);
		Log.info("Getting the url" + baseUrl);
		webDriver.get().manage().window().maximize();
		Log.info("Window maximize");
	}

	protected void printSessionId() {
		String testname = String.format(testName);

		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
				getWebDriver().getSessionId(), "some job name");

		Log.info("printSessionId:" + message + testname);

	}

	// Method that gets invoked after method.

	@AfterMethod
	public void getReport(ITestResult result) {
//		if (configread.getTestEnv().equalsIgnoreCase("remote"))
//		{
//			String txt = "sauce:job-result=" + (result.isSuccess() ? "passed" : "failed");
//			getWebDriver().executeScript(txt);
//			printSessionId();
//			Log.info("Create ITest Result");
//
//		}else if(configread.getTestEnv().equalsIgnoreCase("gcp")){
		if (result.isSuccess()) {
				Cookie cookie = new Cookie("zaleniumTestPassed", "true");
				webDriver.get().manage().addCookie(cookie);
				
		} else {
				Cookie cookie = new Cookie("zaleniumTestPassed", "false");
				webDriver.get().manage().addCookie(cookie);

		}
			//Cookie cookie = new Cookie("zaleniumTestPassed", "true");
			//webDriver.get().manage().addCookie(cookie);
			
		webDriver.get().quit();
		Log.info("Driver quit");

	}

}
