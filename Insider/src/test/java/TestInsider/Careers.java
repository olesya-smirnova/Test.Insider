package TestInsider;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Careers {

	@Parameters({ "browser" })
	@Test

	public void mainpage(String browserName) {

		// Initialize the WebDriver
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("--incognito");
			// cOptions.addArguments("--disable-cookies");
			cOptions.setAcceptInsecureCerts(true);
			cOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, cOptions);
			cOptions.merge(capabilities);
			driver = new ChromeDriver(cOptions);
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get("https://useinsider.com");

			Reporter.log("Case 1: Verifying the page title...", true);
			Assert.assertTrue(driver.getTitle().contains("Leader in Individualized"), "Page title mismatch");
			Reporter.log("Page title is verified successfully.", true);

			// Click on 'Company' menu and then 'Careers'
			Reporter.log("Case 2: Move to the Careers page ...", true);
			WebElement companyMenu = driver.findElement(By.linkText("Company"));
			companyMenu.click();

			WebElement careersMenu = driver.findElement(By.linkText("Careers"));
			careersMenu.click();

			// Assert that the URL contains 'careers'
			Assert.assertTrue(driver.getCurrentUrl().contains("careers"), "URL doesn't contain 'careers'");
			Reporter.log("the Careers verified successfully.", true);

			// Wait for the 'Our Locations' section to be visible
			Reporter.log("Case 3: Verifying the 'Our Locations' section ...", true);
			WebElement locationsLink = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("location-slider")));
			Reporter.log("Found Locations link: " + locationsLink.getText(), true);

			// Find the 'Teams block element'

			WebDriverWait waitBd = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				Reporter.log("Case 4: Verifying the 'Teams block' ...", true);
				WebElement teamsBlock = waitBd
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("career-find-our-calling")));
				Reporter.log("Teams block element was found: " + teamsBlock.getText(), true);

			} catch (NoSuchElementException e) {
				Assert.fail("Teams block element was not found: " + e.getMessage());
			}

			// Wait for 'Life at Insider' block

			Reporter.log("Case 5: Verifying the 'Life at Insider' block ...", true);
			WebElement lifeAtInsiderBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[contains(@class, 'elementor-heading-title elementor-size-default') and contains(text(), 'Life at Insider')]")));
			Assert.assertTrue(lifeAtInsiderBlock.isDisplayed(), "Life at Insider block is not displayed");
			Reporter.log("Life at Insider block is displayed.", true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
