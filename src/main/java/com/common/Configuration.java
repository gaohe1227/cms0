package com.common;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public class Configuration {
	
    private static final Logger log = Logger.getLogger(Configuration.class);
    final static String file = "power";

    static ResourceBundle rb = null;

    static {
        try {
            rb = ResourceBundle.getBundle(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String result = null;
        try {
            result = rb.getString(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
    public static ResourceBundle getResourceBundle() {
        return rb;
    }

}
