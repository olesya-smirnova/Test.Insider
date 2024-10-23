package TestInsider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class HRRole {
	@Parameters({ "browser" })
	@Test

	public void viewrulebutton(String browserName) {

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
			driver.get("https://useinsider.com/careers/open-positions/?department=qualityassurance");
			driver.manage().window().maximize();

			// Wait for 2 seconds to ensure that the page has loaded

			WebDriverWait waitel = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Locate the element that should be hovered over
			WebElement listofjobs = driver.findElement(By.id("jobs-list"));

			WebDriverWait waitele = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Locate the element that should be hovered over
			Reporter.log("Case 1:Senior Software Quality Assurance Engineer job'...", true);
			WebElement positionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
			"//p[@class='position-title font-weight-bold' and text()='Senior Software Quality Assurance Engineer']")));

			// Create an Actions object to perform the hover action
			Actions actions = new Actions(driver);
			actions.moveToElement(positionTitle).perform();

			WebElement buttonToClick = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'View Role')]")));
			buttonToClick.click();

			// Assert that the URL contains 'quality-assurance'
			Reporter.log("Case 2: Verifying the URL contains 'jobs.lever.co'...", true);
			Assert.assertTrue(driver.getCurrentUrl().contains("jobs.lever.co"), "URL doesn't contain 'jobs.lever.co'");
			Reporter.log("Page is verified successfully.", true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}
}
