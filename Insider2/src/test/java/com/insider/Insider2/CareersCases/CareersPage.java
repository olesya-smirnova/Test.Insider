package com.insider.Insider2.CareersCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.assertj.core.api.Assertions.assertThat;


public class CareersPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public By companyMenu = By.linkText("Company");
	public By careersMenu = By.linkText("Careers");
	public By locationSlider = By.id("location-slider");
	private By teamsBlock = By.id("career-find-our-calling");
	private By lifeAtInsiderBlock = By.xpath(
			"//*[contains(@class, 'elementor-heading-title elementor-size-default') and contains(text(), 'Life at Insider')]");
	private By qajobs = By
			.xpath("//a[@href='https://useinsider.com/careers/open-positions/?department=qualityassurance']");
	private By dropdown = By.id("select2-filter-by-location-container");
	private By dropdown1 = (By.xpath("//span[@aria-labelledby='select2-filter-by-location-container']"));
	// private By dropdownOption=By.xpath("//li[contains(@id,
	// 'select2-filter-by-location-result') and contains(text(), 'Istanbul,
	// Turkey')]"))
	private By positionListContainer = By.className("position-list");
	// private By positionTitles= (By.className("position-title");

	public CareersPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	  public void clickCompanyMenu() {
	  wait.until(ExpectedConditions.visibilityOfElementLocated(companyMenu));
	  driver.findElement(companyMenu).click();
	       
	    }

	public void clickCareersMenu() {
	 wait.until(ExpectedConditions.visibilityOfElementLocated(careersMenu));
	 driver.findElement(careersMenu).click();
	}

	public WebElement getLocationSlider() {
		WebElement locationSliderElement =
		wait.until(ExpectedConditions.visibilityOfElementLocated(locationSlider));
		assertThat(locationSliderElement).isNotNull();
		return driver.findElement(locationSlider);
	}

	public WebElement getTeamsBlock() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(teamsBlock));
		 assertThat(teamsBlock).isNotNull();
		 return driver.findElement(teamsBlock);
	}

	public WebElement getLifeAtInsiderBlock() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lifeAtInsiderBlock));
		assertThat(lifeAtInsiderBlock).isNotNull();
		return driver.findElement(lifeAtInsiderBlock);
	}

	public WebElement waitbuttonQA() {
		return driver.findElement(qajobs);
	}

	public WebElement waitdepartmentQA() {
		return driver.findElement(dropdown);
	}

	public WebElement selectcountryfilter() {
		return driver.findElement(dropdown1);
	}

	// public WebElement selectcountry() {
//        return driver.findElement(dropdownOption);
//    }

}
