package DriverTesting.util;

import org.openqa.selenium.WebDriver;

import DriverTesting.Base.BaseClass;

public class AssertionUtil {
	BaseClass baseClass;
	WebDriver driver;

	
	/**
	 * This constructor creates the instances for {@link BaseClass} and
	 * {@link WebDriver}
	 * 
	 * @param baseClass
	 *            This parameter provides the {@link BaseClass} object
	 * @param driver
	 *            This parameter provides the driver object
	 */
	public AssertionUtil(BaseClass baseClass, WebDriver driver) {
		this.baseClass = baseClass;
		this.driver = driver;
	}

}
