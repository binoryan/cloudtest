package com.selenium.ui.extentreportlistener;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReporterNG {
	private static ExtentReports extent;
	
	   public synchronized static ExtentReports getReporter(){
	        if(extent == null){
	            //Set HTML reporting file location
	            String workingDir = System.getProperty("user.dir");
	            extent = new ExtentReports(workingDir+"/test-output/HDS-Selenium-Ebiz-ExtendHTMLReport.html", true);
	        }
	        return extent;
	    }
}
