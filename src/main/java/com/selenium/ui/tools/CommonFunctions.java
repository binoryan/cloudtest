package com.selenium.ui.tools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//import org.sikuli.script.App;
//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;
//import org.openqa.selenium.IJavascriptExecutor;

public class CommonFunctions {

    int counter = 0;
    char specialCharacters[] = {'!', '@', '#', '$', '%', '^', '&', '*',
            '(', ')', '?', '/', '"', '|', '{', '[', '<', '>', ';', '`', ',',
            '_', '-'};
    private WebDriver driver;
    private WaitTool waitTool;
    private PageManager pageManager;
    private SecureRandom random = new SecureRandom();

    public CommonFunctions(WebDriver driver) {
        this.driver = driver;
        this.waitTool = new WaitTool();
        this.pageManager = new PageManager(driver);
    }

    /**
     * Retrieve popup text message.
     * <p>
     * param WebDriver
     * driver
     *
     * @return
     */
    public String getPopupMessage(final WebDriver driver) {
        String message = null;
        try {
            Alert alert = driver.switchTo().alert();

            message = alert.getText();
            //alert.accept();
        } catch (Exception e) {
            // Sometimes the text exist, but not the accept button.
            // this means the popup wasn't displayed and therefore
            // really never existed.
            //
            message = null;
        }
        System.out.println("message" + message);
        return message;
    }

    public String getPopupMessageAndAccept(final WebDriver driver) {
        String message = null;
        try {
            Alert alert = driver.switchTo().alert();

            message = alert.getText();
            alert.accept();
        } catch (Exception e) {
            // Sometimes the text exist, but not the accept button.
            // this means the popup wasn't displayed and therefore
            // really never existed.
            //
            message = null;
        }
        System.out.println("message" + message);
        return message;
    }
    
    /**
     * Get drop down options
     *
     * @param driver
     * @param //param value
     */

    public List<WebElement> selectDropdownOptions(WebDriver driver, WebElement element) {
        return new Select(element).getOptions();
    }

    public String getSelectedDropdownOption(WebDriver driver, WebElement element) {
        Select options = new Select(element);
        WebElement selectedValue = options.getFirstSelectedOption();
        return selectedValue.getText();

    }

    public String acceptPopupMessageBox(final WebDriver driver) {
        String message = null;
        //new WaitTool().waitForAlert(driver);
        try {
        	new WaitTool().waitForAlert(driver);
            Alert alert = driver.switchTo().alert();

            message = alert.getText();
            alert.accept();
        } catch (Exception e) {
            // Sometimes the text exist, but not the accept button.
            // this means the popup wasn't displayed and therefore
            // really never existed.
            //
            message = null;
        }

        return message;
    }
   

    public void selectSearchDropdown1(WebElement element, String value) {
        System.out.println("Enter Element value" + value);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public String cancelPopupMessageBox(final WebDriver driver) {
        String message = null;
        try {
            Alert alert = driver.switchTo().alert();

            message = alert.getText();
            alert.dismiss();
        } catch (Exception e) {
            // Sometimes the text exist, but not the accept button.
            // this means the popup wasn't displayed and therefore
            // really never existed.
            //
            message = null;
        }

        return message;
    }

    /**
     * Generate random string of special characters of length x
     *
     * @return
     */
    public String getRandomSpecialString(int length) {
        int len = specialCharacters.length;
        String str = "";
        Random randomGenerator = new Random();
        int index;

        for (int i = 0; i < length; i++) {
            index = randomGenerator.nextInt(len - 1);
            str = str + specialCharacters[index];
        }
        return str;
    }

    /**
     * Generate random string of length x
     *
     * @return
     */
    public String getRandomString(int length) {
        String result = new BigInteger(Long.SIZE * length, random).toString(32);
        return result.substring(0, length);
    }

    /**
     * Generate random number
     *
     * @return
     */
    public String getRandomNumber() {
        Random r;
        int random;
        r = new Random(System.currentTimeMillis());
        //random = 1000 + r.nextInt(2000);
        random = 10008 + r.nextInt(20000);
        return String.valueOf(random);
    }


    /**
     * Generate random string of length x
     *
     * @return
     */
    public void populateField(WebDriver driver, By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);

    }

    /**
     * Check hover message text
     *
     * @param driver param by
     * @return string
     */
    public String checkHoverMessage(WebDriver driver, By locator) {
        String tooltip = driver.findElement(locator).getAttribute("title");
        return tooltip;
    }


    /**
     * Mouse hover
     */
    public void mouseHover(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * Select radio button
     *
     * @param driver param by
     * @param value
     */
    public void selectRadioButton(WebDriver driver, By locator, String value) {
        List<WebElement> select = driver.findElements(locator);

        for (WebElement radio : select) {
            if (radio.getAttribute("value").equalsIgnoreCase(value)) {
                radio.click();

            }
        }
    }

    /**
     * Select multiple check boxes
     *
     * @param driver
     * @param value
     */
    public void selectCheckboxes(WebDriver driver, By locator, String value) {

        List<WebElement> abc = driver.findElements(locator);
        List<String> list = new ArrayList<String>(Arrays.asList(value.split(",")));

        for (String check : list) {
            for (WebElement chk : abc) {
                if (chk.getAttribute("value").equalsIgnoreCase(check)) {
                    chk.click();
                }
            }
        }
    }

    public boolean radioBtnIsDisplayed(WebElement element) {
        waitTool.waitForElement(driver, element);
        scrolltoViewElement(element, driver);
        return element.isDisplayed();
    }

    public boolean radioBtnIsSelected(WebElement element) {
        scrolltoViewElement(element, driver);
        waitTool.waitForElement(driver, element);
        pageManager.captureScreenShot().TakeScreenShot("rb.png");
        return element.isSelected();
    }


    /**
     * Select drop down
     *
     * @param driver
     * @param value
     */
    public void selectDropdown(WebDriver driver, By locator, String value) {
        new Select(driver.findElement(locator)).selectByVisibleText(value);
    }

    public void selectDropdown(WebDriver driver, WebElement ele, String value) {
        new Select(ele).selectByVisibleText(value);
    }
    public List<WebElement> getDropdownOptions(WebDriver driver, WebElement element) {
        return new Select(element).getOptions();
    }

    public void selectByVisibleTextFromDropdown(WebElement element, String value) {
        System.out.println("Element value" + value);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void selectFirstValueFromDropdown(WebElement element) {
        Select select = new Select(element);
        select.selectByIndex(1);
    }
    
    public String selectFirstValueAndGetTextFromDropdown(WebElement element) throws InterruptedException {
        Select select = new Select(element);
        select.selectByIndex(1);
        waitTool.waitForJStoLoad(driver);
        return select.getFirstSelectedOption().getText();
    }
    
    public String selectValueAndGetTextFromDropdown(WebElement element, int i) throws InterruptedException {
    	waitTool.waitForElement(driver, element);
        Select select = new Select(element);
        select.selectByIndex(i);
        waitTool.waitForJStoLoad(driver);
        return select.getFirstSelectedOption().getText();
    }
    
    

    /**
     * Select auto-suggest search drop down
     */
    public void selectSearchDropdown(WebElement element, String value) {
        System.out.println("Enter Element value" + value);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }


    /**
     * Upload file
     *
     * @param driver
     * @param value
     */
    public void uploadFile(WebDriver driver, By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }


    /**
     * Takes controls on new tab
     *
     * @param driver
     */
    public void handleNewTab(WebDriver driver) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        String window0 = (String) allWindowHandles.toArray()[1];
        driver.switchTo().window(window0);
    }

    public void handleOldTab(WebDriver driver) {
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
    }

    public void moveToNewWindow(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            System.out.println("Move to the new window" + windowHandle);
        }

    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }
    
    public String getUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void closecurrentBrowser(WebDriver driver) {
        driver.close();
        System.out.println("Close current browser(driver) method");
    }

    public void doubleClick(WebDriver driver, WebElement element){
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }
    
    public boolean isClickable(WebDriver driver,WebElement webe)      
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webe));
            webe.click();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /**
     * Helper method: looks through a list of WebElements, to find the first WebElement with matching text
     *
     * @param elements
     * @param text
     * @return WebElement or null
     */
    public WebElement findElementByText(List<WebElement> elements, String text) {
        WebElement result = null;
        for (WebElement element : elements) {
            element.getText().trim();
            if (text.equalsIgnoreCase(element.getText().trim())) {
                result = element;
                break;
            }
        }
        return result;
    }


    /**
     * Compact way to verify if an element is on the page
     *
     * @param driver
     * @param by
     * @return
     */
    public boolean isElementPresent(final WebDriver driver, By by) {


        try {
            driver.findElement(by);

            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            System.out.println(element.isDisplayed());
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Downloads a file from the defined url, and saves it into the
     * OutputDatafolder, using the filename defined
     *
     * @param href
     * @param fileName
     */
    public void downloadFile(String href, String fileName) throws Exception {

        PropertyConfigurator.configure("config/log4j.properties");

        URL url = null;
        URLConnection con = null;
        int i;

        url = new URL(href);

        con = url.openConnection();
        File file = new File(".//OutputData//" + fileName);
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

        @SuppressWarnings("resource")
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(file));
        while ((i = bis.read()) != -1) {
            bos.write(i);
        }
        bos.flush();
        bis.close();
    }


    /**
     * Writes content to the excel sheet
     *
     * @param text
     * @param fileName
     */
    public void writeExcel(String text, String fileName) throws Exception {
        FileOutputStream file = new FileOutputStream(".//OutputData//" + fileName + ".csv", true);
        WritableWorkbook book = Workbook.createWorkbook(file);
        WritableSheet sheet = book.createSheet("output", 0);
        Label l = new Label(0, 0, text);
        sheet.addCell(l);
        book.write();
        book.close();
    }

    public void scrolltoViewElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrolltoClickElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrolltoViewAndClickElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollDown(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

    }
    
    //scroll a little because of chat option , unable to click on apply button for source code
    public void scrollDown50(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)");

    }

    public void scrollDowntillEnd(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,4000)");

    }

    public void scrollDowntillFooterLinks(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2800)");

    }

    public void scrollUp(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-800)");

    }
    
    public void scrollUptillTop(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-4000)");

    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Verify if table sorted by column
     *
     * @param tableColumnHeader
     * @param columnDataList
     * @return True if table sorted by column otherwise False
     */
    public boolean isTableSortedByColumn(WebElement tableColumnHeader, List<WebElement> columnDataList) {
        tableColumnHeader.click();

        ArrayList<String> obtainedList = new ArrayList<>();
        for (WebElement we : columnDataList) {
            obtainedList.add(we.getText());
        }
        System.out.println("Obtained List" + obtainedList);

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : obtainedList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        System.out.println("Sorted List" + sortedList);
        if (sortedList.equals(obtainedList)) {
            return true;
        } else {
            return false;
        }
    }

    public int counterIncrease() {
        counter++;
        return counter;
    }

    public void pressEscape() throws AWTException {
       Actions action = new Actions(driver);
       action.sendKeys(Keys.ESCAPE).build().perform();
    }

    /**
     * Mouse hover
     */


    public void mouseHoverAndClick(WebDriver driver, WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }

    public void acceptPopupIfDisplayed(final WebDriver driver) {
        try
        {
            Alert alert = driver.switchTo().alert();
            driver.switchTo().alert();
            alert.accept();
        }
        catch (Exception e) {

            System.out.println("no alert displayed");
        }
    }

    public boolean comparePartsAdded(List<String> list1, List<String> list2){
        System.out.println("list1 Before Sort:" + list1);
        System.out.println("list2 Before Sort:" + list2);

        list1 = new ArrayList<String>(list1);
        list2 = new ArrayList<String>(list2);

        Collections.sort(list1);
        Collections.sort(list2);

        System.out.println("list1 After Sort:" + list1);
        System.out.println("list2 After Sort:" + list2);

        return list1.equals(list2);
    }
    
    public List<String> getListOfValues(List<WebElement> element)
	{
    	List<String> listOfVaues = new ArrayList<>();
		for(int i=0; i<element.size(); i++){
			listOfVaues.add(element.get(i).getText().trim());
		}
		return listOfVaues;
	}
    
    public List<String> getAllPartNumbers(List<WebElement> element) {
		List<String> all_partNum=new ArrayList<>();
		for(int i=0; i<element.size(); i++){
			all_partNum.add(element.get(i).getText().trim().substring(5));
			System.out.println(element.get(i).getText().trim().substring(5));

		}
		return all_partNum;
	}
    
    public List<String> getAllQuantity(List<WebElement> element) {
		List<String> all_qty=new ArrayList<>();
		for(int i=0; i<element.size(); i++){
			all_qty.add(element.get(i).getAttribute("value"));
			System.out.println(element.get(i).getAttribute("value"));

		}
		return all_qty;
	}
    
    public boolean isDisplayedAddedPartsList(List<String> list1, List<String> list2){
        System.out.println("list1 Before Sort:" + list1);
        System.out.println("list2 Before Sort:" + list2);

        list1 = new ArrayList<String>(list1);
        list2 = new ArrayList<String>(list2);

        Collections.sort(list1);
        Collections.sort(list2);

        System.out.println("list1 After Sort:" + list1);
        System.out.println("list2 After Sort:" + list2);

        return list1.containsAll(list2);
    }
    
    public void saveStringInTextFile(WebDriver driver,String orderNumber) throws InterruptedException
    {
        try
        {
            orderNumber=orderNumber.substring(1);
            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/test/resources/orderNumberRecord.txt"));
            writer.write(orderNumber);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("waiting for 10 seconds");

    }

    public String moveToNewWindowAndGetHandle(WebDriver driver)
    {
        String lastHandle="";
        Set<String> windowHandles = driver.getWindowHandles();
        for(String windowHandle :windowHandles)
        {
            driver.switchTo().window(windowHandle);
            lastHandle=windowHandle;
            System.out.println("Move to the new window" + windowHandle);
        }
        return lastHandle;

    }
    
    public boolean isWebElementDisplayed(WebElement ele) 
	{
    	boolean flag;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

    
    public String moveToNewWindowAndGetTitle(WebDriver driver)
    {
    	String newTitle="";
        String base = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(base);
        for(String windowHandle :windowHandles)
        {
            driver.switchTo().window(windowHandle);
            String title = pageManager.common().getTitle(driver);
            newTitle=title;
            System.out.println("new window title" + title);
        }
        driver.switchTo().window(base);
        return newTitle;

    }
    
    public String moveToNewWindowAndGetUrl(WebDriver driver)
    {
    	String newUrl="";
        String base = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(base);
        for(String windowHandle :windowHandles)
        {
            driver.switchTo().window(windowHandle);
            String url = pageManager.common().getUrl(driver);
            newUrl=url;
            System.out.println("new window url" + url);
        }
        driver.switchTo().window(base);
        return newUrl;

    }

    /**
     * Select auto-suggest search drop down
     *
     */
    public void selectByIndex(WebElement element, int index){
        System.out.println("Enter Element value"+index);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void clickOnTabButton(WebDriver driver) throws AWTException{
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
    }

    public void clickOnEnterButton(WebDriver driver) throws AWTException{
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
    }

	public String getCurrentDate()
	{
		Date currentDate = new Date();  
        String dateToStr = DateFormat.getDateInstance().format(currentDate);  
	    System.out.println("Todays Date: "+dateToStr);  
		return dateToStr;
	}
	
	/*
    @author by: Aniket Choubey

    FMQAECOMM-2250
    */
	
	/*
    @Fixed by: Bino Jayasingh

    FMQAECOMM-2355
    */
	public String getCurrentDateIn_MMMDDYYY() {
		
		 Format formatter = new SimpleDateFormat("MMMM dd, yyyy"); 
		 System.out.println(formatter.format(new Date()));
		 String date=formatter.format(new Date());
		 for(int i=0;i<date.length()-1;i++) { 
			String e=String.valueOf(date.charAt(i));
				if(e.equals(" ")) {
					if(String.valueOf(date.charAt(i+1)).equals("0")) {
						//date=String.valueOf(date.charAt(i+1)).replace("0", "");
						//break;
						date=date.substring(0, i+1) + date.substring(i+1 + 1);
					}
					break;
				}
				else {
					//date1=formatter.format(new Date());
					continue;
				}
			}
			return date;
	}
	
	public String getCurrentDateIn_MMDDYYYYFormat()
	{
		Date date = new Date();
 	    String DATE_FORMAT = "MM/dd/yyyy";
 	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);  
	}
	
	
	public ResultSet connectAndExecuteQureyInOracleDB(String hostName,String port,String SID,String user,String password, String SQL) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");// Open a connection
		conn = DriverManager.getConnection("jdbc:oracle:thin:@//" + hostName + ":" + port +"/" + SID,user, password);
		stmt = conn.createStatement();
	    resultSet = stmt.executeQuery(SQL);
	    return resultSet;
	}

	
	public void handleAlert(){
        if(isAlertPresent()) {
             driver.switchTo().alert().accept();
            //return new LoginPage(driver);
        }
    }

    public  boolean isAlertPresent(){
       try {
           driver.switchTo().alert();
           return true;
       }catch (NoAlertPresentException ex)
       {
           return false;
       }
    }
}
