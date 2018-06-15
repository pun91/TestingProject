package DriverTesting.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import DriverTesting.Base.BaseClass;

public class TextBoxUtils {

	private static final Logger LOGGER = Logger.getLogger(TextBoxUtils.class);
	BaseClass baseClass;
	WebDriver driver;
	AssertionUtil assertionUtils;

	public TextBoxUtils(BaseClass baseClass) {
		this.baseClass = baseClass;
		assertionUtils = baseClass.getAssertionUtils();
	}
	
	public void enteringValuesUsingSendkeys(WebElement element , String inputValue) {
		element.clear();
		element.sendKeys(inputValue);
	}

}
