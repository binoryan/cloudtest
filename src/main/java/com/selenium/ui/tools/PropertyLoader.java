package com.selenium.ui.tools;

import java.io.IOException;
import java.util.Properties;
/**
 * Loads test suite configuration from resource files.
 */
public class PropertyLoader {

    private static final String DEBUG_PROPERTIES = "config/debug.properties";

    private Properties prop;


    public PropertyLoader() throws IOException {
    
          this (System.getProperty("environment.properties", DEBUG_PROPERTIES));
    }


    private  PropertyLoader(String fromResource) throws IOException {
    //	System.out.println(fromResource);
        prop = new Properties();
        prop.load(PropertyLoader.class.getResourceAsStream(fromResource));
    }


  /*  public Capabilities getCapabilities() throws IOException {
        String capabilitiesFile = prop.getProperty("capabilities");
        Properties capsProps = new Properties();
        capsProps.load(PropertyLoader.class.getResourceAsStream(capabilitiesFile));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String name : capsProps.stringPropertyNames()) {
            String value = capsProps.getProperty(name);
            if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
                capabilities.setCapability(name, Boolean.valueOf(value));
            } else if (value.startsWith("file:")) {
                capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
            } else {
                capabilities.setCapability(name, value);
            }
        }
        return capabilities;
    }
   */


    public boolean hasProperty(String name) {
        return prop.containsKey(name);
    }

    public String getProperty(String name) {
        return prop.getProperty(name);
    }


}