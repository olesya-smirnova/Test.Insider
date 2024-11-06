package com.insider.Insider2.QaPageCases;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By qajobs = By
			.xpath("//a[@href='https://useinsider.com/careers/open-positions/?department=qualityassurance']");
	private By dropdown = (By
			.xpath("//*[@id='select2-filter-by-department-container' and @title='Quality Assurance']"));
	private By dropdowncountry = (By.id("select2-filter-by-location-container"));
	private By filtercountry = (By.xpath("//span[@aria-labelledby='select2-filter-by-location-container']"));
	private By filterTurkey = (By.xpath(
			"//li[contains(@id, 'select2-filter-by-location-result') and contains(text(), 'Istanbul, Turkey')]"));
	
	private By selectTurkey = By.xpath("//*[@id='select2-filter-by-location-container' and @title='Istanbul, Turkey']");
	private By positionListContainer = (By.className("position-list"));
	private By positionItemsLocator = By.cssSelector(".position-list-item-wrapper.bg-light");
	private By locationElement = (By.className("position-list"));
	private By titleElement = (By.className("position-title"));
	private By buttonviewrole = (By.xpath("//a[contains(text(),'View Role')]"));

	public QaPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public void waitbuttonQA() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(qajobs));
		driver.findElement(qajobs).click();
	}

	public WebElement waitdepartmentQA() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
		return driver.findElement(dropdown);
	}

	public void selectcountryfilter() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
		assertThat(wait.until(ExpectedConditions.elementToBeClickable(dropdowncountry)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(filtercountry));
		driver.findElement(filtercountry).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(filterTurkey));
		driver.findElement(filterTurkey).click();
		WebElement turkeyElement = driver.findElement(selectTurkey);
		assertThat(turkeyElement.getAttribute("title")).contains("Istanbul, Turkey");
	}

	public WebElement selectPosition() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		wait.until(ExpectedConditions.visibilityOfElementLocated(positionListContainer));

		// Locate the position list container
		WebElement positionList = driver.findElement(positionListContainer);

		// Find all position items within the container using the locator
		List<WebElement> positionItems = positionList.findElements(positionItemsLocator);

		// Iterate through each position item to find the match
		for (WebElement positionItem : positionItems) {
			WebElement titleElement = positionItem.findElement(By.className("position-title"));
			String titleText = titleElement.getText();
			System.out.println("Found title: " + titleText);

			WebElement locationElement = positionItem.findElement(By.className("position-location"));
			String locationText = locationElement.getText();
			System.out.println("Found location: " + locationText);

			// Check if both title and location match the desired values
			if (titleText.equals("Senior Software Quality Assurance Engineer")
					&& locationText.equals("Istanbul, Turkey")) {
				System.out.println("Found position: " + titleText + " in " + locationText);
				return positionItem; // Return the matching position item
			}
		}

		// If no matching element is found, return null or throw an exception
		System.out.println("No matching position found.");
		return null;
	}
	
	
	
	

	 public void waitbuttonRole() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(positionListContainer));
	        WebElement positionList = driver.findElement(positionListContainer);
	        List<WebElement> positionItems = positionList.findElements(positionItemsLocator);

	        for (WebElement positionItem : positionItems) {
	            
	                WebElement titleElement = positionItem.findElement(By.className("position-title"));
	                String titleText = titleElement.getText();
	                WebElement locationElement = positionItem.findElement(By.className("position-location"));
	                String locationText = locationElement.getText();

	                if (titleText.equals("Senior Software Quality Assurance Engineer") && locationText.equals("Istanbul, Turkey")) {
	                    Actions actions = new Actions(driver);
	                    actions.moveToElement(titleElement).perform();
	                    WebElement viewRoleButton = wait.until(ExpectedConditions.elementToBeClickable(buttonviewrole));
	                    viewRoleButton.click();
	                   
	                }
	                System.out.println("No matching position found.");
	    	      
	            } 
	        }
	        
 }

	

