package com.api.config;

import static org.aeonbits.owner.ConfigCache.getOrCreate;
public class ConfigurationRequest {
	
	 private ConfigurationRequest() {
	    }

	    public static Configuration configuration() {
	    	
	    	
	        return getOrCreate(Configuration.class);
	    }

}
