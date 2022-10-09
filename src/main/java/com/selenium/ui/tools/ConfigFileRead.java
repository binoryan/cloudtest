package com.selenium.ui.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileRead {

    public Properties prop;

    public ConfigFileRead() {
        prop = new Properties();
        try {

            File file = new File("src/main/resources/config/Config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            prop.load(fileInput);

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    public String getEvenListenerFlag(){
        String eventListenerFlag = prop.getProperty("eventListenerFlag");
        if(eventListenerFlag!= null) return eventListenerFlag;
        else throw new RuntimeException("eventListenerFlag is not specified in the Configuration.properties file.");
    }
    
    public String getTestEnv(){
        String testEnv = prop.getProperty("testEnv");
        if(testEnv!= null) return testEnv;
        else throw new RuntimeException("testEnv is not specified in the Configuration.properties file.");
    }
    
    public String getTestTunnel(){
        String testTunnel = prop.getProperty("testTunnel");
        if(testTunnel!= null) return testTunnel;
        else throw new RuntimeException("testTunnel is not specified in the Configuration.properties file.");
    }


    public String getBrowser(){
        String browser = prop.getProperty("browser");
        if(browser!= null) return browser;
        else throw new RuntimeException("browser is not specified in the Configuration.properties file.");
    }

    public String getUrl(){
        String url = prop.getProperty("url");
        if(url!= null) return url;
        else throw new RuntimeException("url is not specified in the Configuration.properties file.");
    }

    public String getUserName() {
        String username = prop.getProperty("username");
        if(username != null) return username;
        else throw new RuntimeException("username not specified in the Configuration.properties file.");
    }

    public String getPassword() {
        String password = prop.getProperty("password");
        if(password != null) return password;
        else throw new RuntimeException("password not specified in the Configuration.properties file.");
    }
    public String getPassword2() {
        String password2 = prop.getProperty("password2");
        if(password2 != null) return password2;
        else throw new RuntimeException("password2 not specified in the Configuration.properties file.");
    }

    public String getEnv() {
        String env = prop.getProperty("env");
        if(env != null) return env;
        else throw new RuntimeException("env not specified in the Configuration.properties file.");
    }

    
    public String readPropertyOrEnv(String key, String defaultValue) {
        String v = System.getProperty(key);
        if (v == null)
            v = System.getenv(key);
        if (v == null)
            v = defaultValue;
        return v;
    }
}
