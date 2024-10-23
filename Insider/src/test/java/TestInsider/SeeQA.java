package TestInsider;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class SeeQA {
	
	@Parameters({"browser"})
	@Test
	
	public void sampleOne(String browserName) {
		
		// Initialize the WebDriver 
		WebDriver driver=null;
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		
		}else if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("--incognito");
			//cOptions.addArguments("--disable-cookies");
			cOptions.setAcceptInsecureCerts(true);
			cOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, cOptions);
			cOptions.merge(capabilities);
			driver = new ChromeDriver(cOptions);
		}
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.get("https://useinsider.com/careers/quality-assurance");
	        
	        
	        // Assert that the URL contains 'quality-assurance'
	        Reporter.log("Case 1: Verifying the Page title...", true);
	        Assert.assertTrue(driver.getTitle().contains("Insider quality assurance job opportunities"), "Page title mismatch");
	        Reporter.log("Page title is verified successfully.", true);
	        
	     // Click on the button 'See all QA jobs'
	        
	        WebDriverWait waitB = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement qajobs = waitB.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://useinsider.com/careers/open-positions/?department=qualityassurance']")));
	        qajobs.click();
	       
	        Reporter.log("Case 2: Verifying the URL contains 'department=qualityassurance'...", true);
	        Assert.assertTrue(driver.getCurrentUrl().contains("department=qualityassurance"), "URL doesn't contain 'department=qualityassurance'");;
	        Reporter.log("The new page oppened", true);
	        
	        driver.manage().window().maximize();
	        
	        
	        // Verify the filter department 
	        Reporter.log("Case 3: Verify the filter of department set in Quality Assurance'...", true);
	        WebDriverWait waitf = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement dropdown = waitf.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='select2-filter-by-department-container' and @title='Quality Assurance']"));
	        Reporter.log("Quality Assurance selected.", true);
	        
	        
	        // Click on the filler
	        Reporter.log("Case 4: Select in filter 'Istanbul, Turkey'...", true);
	        WebDriverWait waitl = new WebDriverWait(driver, Duration.ofSeconds(30));
	       
	        
	        WebElement dropdown1 = waitl.until(ExpectedConditions.elementToBeClickable(By.id("select2-filter-by-location-container")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown1);

	        WebElement dropdownOption = waitf.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#html.no-js.wf-figtree-n4-active.wf-active body.page-template.page-template-layouts.page-template-layout-careers-open.page-template-layoutslayout-careers-open-php.page.page-id-13315.page-child.parent-pageid-22610.wp-embed-responsive.career-page.inner-page.elementor-default.elementor-kit-960.elementor-page.elementor-page-13315.scrolled.nav-active section#career-position-filter.bg-light div.container.pb-0 div.row div#top-filter.filter-menu-wrapper.col-12.p-0.is-lever div#filter-menu.collapse.multi-collapse.d-lg-block form#top-filter-form.w-100.d-flex.flex-wrap div.col-12.col-lg-6.form-group.d-flex.flex-column.mb-0 span.select2.select2-container.select2-container--default.select2-container--below.select2-container--focus.select2-container--open span.selection span.select2-selection.select2-selection--single")));;
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownOption);
	        	
	  
	        //  Verify all jobs Position contains “Quality Assurance”
	        
	         WebDriverWait waitq = new WebDriverWait(driver, Duration.ofSeconds(20));
	         WebElement jobslocation = driver.findElement(
	        By.xpath("//p[contains(@class, 'position-title') and contains(@class, 'font-weight-bold') and contains(text(), 'Quality Assurance') and @data-location='istanbul-turkey']"));
            Assert.assertTrue(jobslocation.isDisplayed(), "Quality Assurance in Istanbul, Turkey is displayed");
	        Reporter.log("Quality Assurance in Istanbul, Turkey.", true);
		        
	      
	        
	        
	        
	        
	    
	
			
	   } catch (Exception e) {
           e.printStackTrace();
       } finally {
           driver.quit();
       }

	}
}	
    
