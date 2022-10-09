package com.selenium.ui.tools;

import com.selenium.ui.dbcon.DBCon;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private WebDriver driver;


    public PageManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public DBCon dBCon(){
        return new DBCon();
    }

    public CaptureScreenShot captureScreenShot(){return new CaptureScreenShot(driver);}

    public CommonFunctions common(){
        return new CommonFunctions(driver);
    }



    public SwitchTool switchTool() {
        return new SwitchTool();
    }

   

    
    
}


