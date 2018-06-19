package DriverTesting.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;


import DriverTesting.Constants.AssertionConstants;
import DriverTesting.Constants.Constants;
import DriverTesting.util.AssertionUtil;
import DriverTesting.util.TextBoxUtils;

public class BaseClass {
	private static final Logger LOGGER = Logger.getLogger(BaseClass.class);

	protected WebDriver driver;
	private Properties projectConstants;
	private TextBoxUtils textboxUtils;
	private AssertionUtil assertionUtils;

	
	/**
	 * This method is to create the instance for the TextBoxUtil class
	 *
	 * @return textBoxUtils
	 */
	public TextBoxUtils getTextBoxUtils() {
		if (textboxUtils == null) {
			textboxUtils = new TextBoxUtils(this);
		}
		return textboxUtils;
	}
	/**
	 * This method is to create the instance for the AssertionUtil class
	 *
	 * @return assertionUtils
	 */
	public AssertionUtil getAssertionUtils() {
		if (assertionUtils == null) {
			assertionUtils = new AssertionUtil(this, driver);
		}
		return assertionUtils;
	}

	
	/**
	 * This method creates new driver for the respective browser given and also
	 * sets the value for the user agent and also opens the given site url
	 *
	 * @param browser
	 *            This parameter provides the browser name
	 * @param userAgent
	 *            This parameter provides the user agent value
	 * @throws IOException
	 */
	
	@BeforeMethod
	@Parameters({ Constants.BROWSER_NAME_KEY })
	public void preConditions(@Optional(Constants.BROWSER_NAME_CHROME) String browser) throws IOException {
		try {
			projectConstants = new Properties();
			projectConstants.load(getClass().getClassLoader().getResourceAsStream(Constants.PROJECT_CONSTANTS_FILE));
		} catch (NullPointerException npe) {
			Assert.fail("Please create the database and Email Report properties and " + Constants.PROJECT_CONSTANTS_FILE
					+ " before running the suite");
		}
		
		if (projectConstants.getProperty(Constants.BROWSER_NAME) != null) {
			browser = projectConstants.getProperty(Constants.BROWSER_NAME);
		}
		
		if (browser.equalsIgnoreCase(Constants.BROWSER_NAME_FIREFOX)) {
			FirefoxProfile profile = new FirefoxProfile();
			driver = new FirefoxDriver(profile);
		} else if (browser.equalsIgnoreCase(Constants.BROWSER_NAME_CHROME)) {
			chromeBrowser();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(Constants.NUMBER_OF_MINUTES, TimeUnit.MINUTES);

		driver.get(AssertionConstants.MAIN_SITE_URL);
		
		// Calling wait Load Method
		implicitlyWait(Constants.DEFAULT_IMPLICIT_WAIT_TIME_MILLISECONDS);
	}
	
	
	/*
	 * This method is to close the browser completely and makes the instance
	 * objects to null and takes the screenshots of failed testcases when suite
	 * is executed
	 */
	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
		driver.close();
		}
	}

	
	/**
	 * This method sets the driver to google chrome
	 *
	 */
	private void chromeBrowser() {
		/*Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 320);
		deviceMetrics.put("height", 640);
		deviceMetrics.put("pixelRatio", 4.0);
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent",
				"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);*/
		System.setProperty(Constants.CHROME_DRIVER_SET_PROPERTY, Constants.CHROME_DRIVER_PATH);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);
	}
	
	/**
	 * This method is used to wait the page to be loaded
	 *
	 * @param milliSeconds
	 *            This parameter provides the time in milliseconds
	 */
	public void implicitlyWait(int milliSeconds) {
		driver.manage().timeouts().implicitlyWait(milliSeconds, TimeUnit.MILLISECONDS);
	}
	
	
	/**
	 * This method is used to wait the execution till the element is found in
	 * the HTML
	 *
	 * @param seconds
	 *            This parameter provides the number of seconds
	 * @param xPath
	 *            This parameter provides the xpath of the element
	 */
	public void explicitlyWait(int seconds, String xPath) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	}
	
	/**
	 * This method is used to click the element and it returns the text of the
	 * element if present or returns empty string if no text present
	 *
	 * @param xPath
	 *            This parameter provides the xpath of the element
	 * @return text of the element which is being clicked
	 */
	public String inspectAndClickOnElement(String xPath) {
		WebElement element = inspectElement(xPath);
		return inspectAndClickOnElement(element);
	}
	
	/**
	 * This method is used to click the element and it returns the text of the
	 * element if present or returns empty string if no text present
	 *
	 * @param element
	 *            This parameter provides the webelement
	 * @return text of the element which is being clicked
	 */	
	public String inspectAndClickOnElement(WebElement element) {
		return inspectAndClickOnElement(element, Constants.EMPTY_STRING);
	}
	
	/**
	 * This method is to find the element using the xpath and returns the
	 * webelement
	 *
	 * @param xPath
	 *            This parameter provides the xpath
	 * @return webelement of the xPath
	 */
	public WebElement inspectElement(String xPath) {
		return driver.findElement(By.xpath(xPath));
	}
	
	
	/**
	 * This method is used to click on the element and returns attribute key
	 * value if attribute type is given
	 *
	 * @param element
	 *            This parameter provides the webelement
	 *
	 * @param attributeKeyType
	 *            This parameter provides the type of attribute to be taken
	 * @return text of the element which is being clicked
	 */
	public String inspectAndClickOnElement(WebElement element, String attributeKeyType) {
		String elementText;
		if (attributeKeyType == null || attributeKeyType.equals(Constants.EMPTY_STRING)) {
			elementText = element.getText();
		} else {
			elementText = element.getAttribute(attributeKeyType);
		}
		element.click();
		if (elementText != null && !elementText.equals(Constants.EMPTY_STRING)) {
			LOGGER.info("Clicked on " + elementText);
		}
		return elementText;
	}
	
	
}
