package com.selenium.ui.account_management.login;

import com.selenium.ui.baseclass.BaseClassV8_ParallelGrid;
import com.selenium.ui.beans.UsersListBean;
import com.selenium.ui.tools.PageManager;
import com.selenium.ui.tools.RandomAccountSelect;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class HomePageAuthenticatedUser extends BaseClassV8_ParallelGrid
{

	
	String facebook = "Facebook";
	String twitter = "Twitter";
	String google = "Google";
	String linkedin = "LinkedIn";
	String youtube = "YouTube";


	

	@Test(dataProvider = "browsers")
	public void login_verifyFacebookLink(String browser, String version, String os, Method method) throws Exception 
	{
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();
		getURL("https://www.facebook.com");
		PageManager pageManager = new PageManager(driver);
		String title = pageManager.common().getTitle(driver);
		Thread.sleep(3000);
		Assert.assertTrue(title.contains(facebook));
	}
	
	@Test(dataProvider = "browsers")
	public void login_verifyTwitterLink(String browser, String version, String os, Method method) throws Exception 
	{
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();
		getURL("https://twitter.com");
		PageManager pageManager = new PageManager(driver);
		String title = pageManager.common().getTitle(driver);
		Thread.sleep(3000);
		Assert.assertTrue(title.contains(twitter));
	}

	@Test(dataProvider = "browsers")
	public void login_verifyLinkedInLink(String browser, String version, String os, Method method) throws Exception 
	{
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();
		getURL("https://www.linkedin.com");
		PageManager pageManager = new PageManager(driver);
		String title = pageManager.common().getTitle(driver);
		Thread.sleep(3000);
		Assert.assertTrue(title.contains(linkedin));
	}
	
	@Test(dataProvider = "browsers")
	public void login_verifyGoogleLink(String browser, String version, String os, Method method) throws Exception 
	{
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();
		getURL("https://www.google.com");
		PageManager pageManager = new PageManager(driver);
		String title = pageManager.common().getTitle(driver);
		Thread.sleep(3000);
		Assert.assertTrue(title.contains(google));
	}
	
	
}
