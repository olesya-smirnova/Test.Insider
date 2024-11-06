package com.insider.driver.factory.manager;

import com.insider.driver.factory.Factory;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager implements Factory {


    @Override
    public WebDriver createDriver() {
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions cOptions = new ChromeOptions();
		cOptions.addArguments("--incognito");
		cOptions.setAcceptInsecureCerts(true);
		cOptions.addArguments("start-maximized");
		cOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, cOptions);
		cOptions.merge(capabilities);
	
        return new ChromeDriver(cOptions);
    }
}