package com.insider.Insider2;

import com.insider.Insider2.CareersCases.CareersActions;
import com.insider.driver.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


import static com.insider.driver.config.ConfigurationManager.configuration;

public class BaseWeb {

	protected WebDriver driver;

	protected CareersActions careersActions;


	@BeforeEach

	public void preCondition() {
		String browserToUse = configuration().browser();
		driver = new DriverFactory().createInstance(browserToUse);
		driver.get(configuration().url());

		// Initialize CareersActions after driver has been created
		careersActions = new CareersActions(driver);
	}

	 @AfterEach

	public void postCondition() {
		if (driver != null) {
			driver.quit();
		}
	}
}