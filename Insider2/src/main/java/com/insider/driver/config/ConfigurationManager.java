package com.insider.driver.config;

import static org.aeonbits.owner.ConfigCache.getOrCreate;

public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration configuration() {
    	
    	
        return getOrCreate(Configuration.class);
    }
}