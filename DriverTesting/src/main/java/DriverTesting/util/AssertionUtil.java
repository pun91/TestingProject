package DriverTesting.util;

import org.openqa.selenium.WebDriver;

import DriverTesting.Base.BaseClass;

public class AssertionUtil {
	BaseClass baseClass;
	WebDriver driver;

	public AssertionUtil(BaseClass baseClass, WebDriver driver) {
		this.baseClass = baseClass;
		this.driver = driver;
	}

}
