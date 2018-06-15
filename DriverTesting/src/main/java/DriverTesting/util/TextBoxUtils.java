package DriverTesting.util;

import org.openqa.selenium.WebDriver;
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

}
