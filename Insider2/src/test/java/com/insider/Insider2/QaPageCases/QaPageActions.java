package com.insider.Insider2.QaPageCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.insider.Insider2.BaseWeb;

import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

public class QaPageActions {

	private WebDriver driver;
	private QaPage qaPage;
	private WebDriverWait wait;

	public QaPageActions(WebDriver driver) {
		this.driver = driver;
		this.qaPage = new QaPage(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	}

	public void navigateToQaPage() {
		qaPage.waitbuttonQA();
		assertThat(driver.getCurrentUrl()).contains("department=qualityassurance");
		}

	public void validFilterQa() {
		qaPage.waitdepartmentQA();
	}
	
	public void validFilterCountry() {
		qaPage.selectcountryfilter();
	}
	
	public void validPosition() {
		qaPage.waitdepartmentQA();
	    qaPage.selectcountryfilter();
	    WebElement position = qaPage.selectPosition();
	    assertThat(position).isNotNull();
	  	qaPage.selectPosition();
	}
	
	
	public void validRolepage() {
		qaPage.selectcountryfilter();
		qaPage.waitbuttonRole();
		Assert.assertTrue(driver.getCurrentUrl().contains("jobs.lever.co"), "URL doesn't contain 'jobs.lever.co'");
	  }
		
	
	
	
}