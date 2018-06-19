package DriverTesting.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;


import DriverTesting.Base.BaseClass;
/**
 * This class contains the methods to enter the values in the textbox like
 * email, mobile number, pincode and text and also to verify the entered values
 * in the text box field
 */
public class TextBoxUtils {

	private static final Logger LOGGER = Logger.getLogger(TextBoxUtils.class);
	BaseClass baseClass;
	WebDriver driver;
	AssertionUtil assertionUtils;

	/**
	 * This constructor creates the instances for {@link BaseClass} and
	 * {@link AssertionUtil}
	 * 
	 * @param baseClass
	 *            This parameter provides the {@link BaseClass} object
	 */
	public TextBoxUtils(BaseClass baseClass) {
		this.baseClass = baseClass;
		assertionUtils = baseClass.getAssertionUtils();
	}
	
	
	/**
	 * This method is to enter the value using the sendkeys
	 * 
	 * @param element
	 *            This parameter provides the xPath of the text box or ajax
	 *            dropdown
	 * @param inputValue
	 *            This parameter provides the value to be entered
	 */
	public void enteringValuesUsingSendkeys(WebElement element , String inputValue) {
		element.clear();
		element.sendKeys(inputValue);
	}

}
