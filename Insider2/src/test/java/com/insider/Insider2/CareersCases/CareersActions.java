package com.insider.Insider2.CareersCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insider.Insider2.BaseWeb;

import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

public class CareersActions {

	private WebDriver driver;
	private CareersPage careersPage;
	private WebDriverWait wait;

	public CareersActions(WebDriver driver) {
		this.driver = driver;
		this.careersPage = new CareersPage(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	}

	public void navigateToCareersPage() {
		careersPage.clickCompanyMenu();
		careersPage.clickCareersMenu();
		assertThat(driver.getCurrentUrl()).contains("careers");
	}

	public void verifyLocationSlider() {
		wait.until(ExpectedConditions.visibilityOf(careersPage.getLocationSlider()));
	}

	public void verifyTeamsBlock() {
		wait.until(ExpectedConditions.visibilityOf(careersPage.getTeamsBlock()));
	}

	public void verifyLifeAtInsiderBlock() {
		wait.until(ExpectedConditions.visibilityOf(careersPage.getLifeAtInsiderBlock()));
	}

	public void clickQabutton() {
		wait.until(ExpectedConditions.elementToBeClickable(careersPage.waitbuttonQA()));
	}

}