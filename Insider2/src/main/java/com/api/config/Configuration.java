package com.api.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"})
public interface Configuration extends Config{
	
	  	@Key("url.base")
	    String url();
	  	
		@Key("path")
	    String path();
		
		@Key("pathnegativecase")
	    String pathnegativecase();

		@Key("petid")
	    String petid();
		
		@Key("fakeptid")
	    String fakeptid();
		
		@Key("petnewname")
	    String petnewname();
		
		@Key("petstatus")
	    String petstatus();

}
