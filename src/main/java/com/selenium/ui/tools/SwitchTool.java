package com.selenium.ui.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SwitchTool{

	public  void switchWindowByTitle(WebDriver driver, String title) {
		String mainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (driver.switchTo().window(ChildWindow).getTitle().trim().equalsIgnoreCase(title))
				break;
		}
		driver.switchTo().window(mainWindow);
	}

	public  void switchWindow(WebDriver driver, String windowehandle) {
		driver.switchTo().window(windowehandle);
	}

	public  void switchframe(WebDriver driver, String elementName, WebElement iframe) {
		if (elementName != null) {
		//	ToolKitLogger.logAction(driver, "Switch to frame " + elementName);
		}
		driver.switchTo().frame(iframe);
	}

	public  void switchDefaultframe(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public  void switchParentframe(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

}

