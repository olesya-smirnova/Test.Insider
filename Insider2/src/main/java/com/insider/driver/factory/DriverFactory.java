package com.insider.driver.factory;

import com.insider.driver.factory.manager.ChromeDriverManager;
import com.insider.driver.factory.manager.FirefoxDriverManager;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	  public WebDriver createInstance(String browser) {
	        WebDriver driver;
	        BrowserList browserType = BrowserList.valueOf(browser.toUpperCase());

	        driver = switch (browserType) {
	            case CHROME -> new ChromeDriverManager().createDriver();
	            case FIREFOX -> new FirefoxDriverManager().createDriver();
            
        };
        return driver;
    }
}