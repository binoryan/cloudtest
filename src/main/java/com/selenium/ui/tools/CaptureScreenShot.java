/*
 TakeScreenShot method to capture the screen shot of the any page.
 Developer: Bhavani
*/
package com.selenium.ui.tools;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class CaptureScreenShot {
    private WebDriver driver;

    public void TakeScreenShot(String captureName) {

        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File(captureName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public CaptureScreenShot(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
