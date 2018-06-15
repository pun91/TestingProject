package DriverTesting.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;
import DriverTesting.Constants.AssertionConstants;
import DriverTesting.util.AssertionUtil;
import DriverTesting.util.TextBoxUtils;

public class BaseClass {
	private static final Logger LOGGER = Logger.getLogger(BaseClass.class);

	protected WebDriver driver;
	private TextBoxUtils textboxUtils;
	private AssertionUtil assertionUtils;

	public TextBoxUtils getTextBoxUtils() {
		if (textboxUtils == null) {
			textboxUtils = new TextBoxUtils(this);
		}
		return textboxUtils;
	}

	public AssertionUtil getAssertionUtils() {
		if (assertionUtils == null) {
			assertionUtils = new AssertionUtil(this, driver);
		}
		return assertionUtils;
	}

	public String inspectAndClickOnElement(WebElement element) {
		return inspectAndClickOnElement(element, AssertionConstants.EMPTY_STRING);
	}

	public String inspectAndClickOnElement(String xPath) {
		WebElement element = inspectElement(xPath);
		return inspectAndClickOnElement(element);
	}

	public WebElement inspectElement(String xPath) {
		return driver.findElement(By.xpath(xPath));
	}

	public String inspectAndClickOnElement(WebElement element, String attributeKeyType) {
		String elementText;
		if (attributeKeyType == null || attributeKeyType.equals(AssertionConstants.EMPTY_STRING)) {
			elementText = element.getText();
		} else {
			elementText = element.getAttribute(attributeKeyType);
		}
		element.click();
		if (elementText != null && !elementText.equals(AssertionConstants.EMPTY_STRING)) {
			LOGGER.info("Clicked on " + elementText);
		}
		return elementText;
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get(AssertionConstants.MAIN_SITE_URL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
