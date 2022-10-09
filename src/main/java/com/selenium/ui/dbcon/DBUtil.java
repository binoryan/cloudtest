package com.selenium.ui.dbcon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.selenium.ui.baseclass.BaseClassV8_ParallelGrid;
import com.selenium.ui.tools.PageManager;


/*  Designed by: Prasad Koti 
 *  Reads the property file db.properties for connection entries and sql quries
 * 
 */

public class DBUtil extends BaseClassV8_ParallelGrid {
	
	/*
	static String proc = "create or replace procedure update_Pass \r\n" + 
			" is\r\n" + 
			"declare\r\n" + 
			"v_logonid userreg.logonid%type := 'prddevops1';\r\n" + 
			"v_salt userreg.salt%type := 'xihvxtdrx6bv';\r\n" + 
			"v_logonpassword userreg.logonpassword%type := hextoraw('486E4E7174706E6C4D726F55515632714A563576316C425275592B74384377503830527737447659726C493D202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020');\r\n" + 
			"begin\r\n" + 
			"update userreg\r\n" + 
			"set salt = v_salt, logonpassword = v_logonpassword, STATUS = 1\r\n" + 
			"where logonid = v_logonid;\r\n" + 
			"update userreg\r\n" + 
			"set passwordretries = null, passwordinvalid = null, passwordexpired = 0,passwordcreation = null, status=1\r\n" + 
			"where logonid = v_logonid;\r\n" + 
			"commit;\r\n" + 
			"end;\r\n" + 
			"";
	static String runSP = "{ call update_Pass }";*/
	private static Connection con = null;
	public static Properties prop = null;
	

	// call this method to get the data base connection
	public static Connection getConnection(String env)throws Exception {
		File file = null;
		FileInputStream fileInput = null;
		String url = null;
		String user = null;
		String pwd = null;
			file = new File("src/main/resources/config/db.properties");
			fileInput = new FileInputStream(file);
			prop = new Properties();
			prop.load(fileInput);
		// read value from java.util.Properties
			if(env.equals("qa2")){
				url = prop.getProperty("qa2.dburl");
				user = prop.getProperty("qa2.dbuser");
				pwd = prop.getProperty("qa2.dbpwd");
	          }
			else if(env.equals("stg")){
				url = prop.getProperty("stg.dburl");
				user = prop.getProperty("stg.dbuser");
				pwd = prop.getProperty("stg.dbpwd");

			}
			
	else 	if(env.equals("qa1")){
		url = prop.getProperty("qa1.dburl");
		user = prop.getProperty("qa1.dbuser");
		pwd = prop.getProperty("qa1.dbpwd");
}

			
			else if(env.equals("dev1")){
				url = prop.getProperty("dev1.dburl");
				user = prop.getProperty("dev1.dbuser");
				pwd = prop.getProperty("dev1.dbpwd");
			}
			else if(env.equals("qa1")){
				url = prop.getProperty("qa1.dburl");
				user = prop.getProperty("qa1.dbuser");
				pwd = prop.getProperty("qa1.dbpwd");
			}
			else if(env.equals("gcpqa1")){
				url = prop.getProperty("gcpqa1.dburl");
				user = prop.getProperty("gcpqa1.dbuser");
				pwd = prop.getProperty("gcpqa1.dbpwd");
			}
			else if(env.equals("ps1")){
				url = prop.getProperty("ps1.dburl");
				user = prop.getProperty("ps1.dbuser");
				pwd = prop.getProperty("ps1.dbpwd");
		}else if(env.equals("gcpdev2")){
			url = prop.getProperty("gcpdev2.dburl");
			user = prop.getProperty("gcpdev2.dbuser");
			pwd = prop.getProperty("gcpdev2.dbpwd");
	}else if(env.equals("gcpprd")){
		url = prop.getProperty("gcpprd.dburl");
		user = prop.getProperty("gcpprd.dbuser");
		pwd = prop.getProperty("gcpprd.dbpwd");
}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// call this method to execute AV Modal pop up query
   public static void connectAndExecuteAVModalQurey(String env,String accountVal) throws Exception {
		//public static void connectAndExecuteAVModalQurey(String env) throws Exception {
		Statement stmt = null;
		stmt = getConnection(env).createStatement();
		String query = prop.getProperty("avModal.sql");
		query = query.replace("***", accountVal);
		System.out.println(query);
		int x = stmt.executeUpdate(query);
		System.out.println(x);
		getConnection(env).commit();

	}

   public static void connectAndExecuteAVModalQurey(String env) throws Exception {
		//public static void connectAndExecuteAVModalQurey(String env) throws Exception {
		Statement stmt = null;
		stmt = getConnection(env).createStatement();
		String query = prop.getProperty("avModal.sql1");
		System.out.println(query);
		int x = stmt.executeUpdate(query);
		System.out.println(x);
		getConnection(env).commit();

	}
   
	public static ResultSet connectAndExecuteSelectQurey(String env, String query) throws Exception {
		System.out.println(query);
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		try {
			// TODO: handle exception
			stmt = null;
			con = getConnection(env);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
		} finally {
			/*stmt.close();
			con.close();*/
		}
		return rs;
	}

   /* Execute this reRunRegressionSqlScripts before executing regression test groups ( ) */
   @Test(dataProvider = "browsers")
	public void runRegressionSqlScripts(String browser, String version, String os, Method method) throws Exception {
		this.createDriver(browser, version, os, method.getName());
		String dbScriptFileOne ="src/main/java/com/selenium/ui/dbcon/sqlRegScripts_1.sql";
		String dbScriptFileTwo ="src/main/java/com/selenium/ui/dbcon/sqlRegScripts_2.sql";
		WebDriver driver = this.getWebDriver();
		String env = getEnvValue();
		DBUtil.executeSqlFile(env,dbScriptFileOne);
		PageManager pageManager = new PageManager(driver);
		pageManager.commonLogin().LoginToHDS("prddevops1", "password1");
		if(pageManager.homePage().getAccountNumber()!=null)
		{
			
		DBUtil.executeSqlFile(env,dbScriptFileTwo);
		}
	}
   
   
	// This is to execute sql file ( need to plan)
	public static void executeSqlFile(String env, String filePath) throws Exception {
		// check the prdevops1 
		Connection con = getConnection(env);
		ScriptRunner sr = new ScriptRunner(con);
		Reader reader = new BufferedReader(new FileReader(filePath));
		sr.runScript(reader);
		
	}

	// To test the connection
	/*public static void main(String[] args) throws Exception {
		
		   ResultSet rs = DBUtil.connectAndExecuteSelectQurey("stg","select * from selfservice.xpiquoteh where sfdc_quote_no='Q-87677'");

		Connection con = getConnection("qa2");
		Statement stmt  = con.createStatement();
		System.out.println(proc);
		CallableStatement calStmt = con.prepareCall(runSP);
		stmt.execute(proc);
		//calStmt.execute();
		System.out.println(con.getMetaData().getDatabaseProductVersion());
		executeSqlFile("qa2","");
	}*/
}
