package DriverTesting.DriverTesting;

import org.testng.annotations.Test;

import DriverTesting.Base.BaseClass;
import DriverTesting.Constants.AssertionConstants;
import DriverTesting.Constants.XpathConstants;
import org.openqa.selenium.By;

public class HomePageLoginTest  extends BaseClass{
	

	@Test
	public void homePageLogin() {
		
		//inspectAndClickOnElement(XpathConstants.USER_ID_XPATH);

		//driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).click();
		driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).clear();
		driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).sendKeys(AssertionConstants.USER_ID);

		driver.findElement(By.xpath(XpathConstants.USER_PASSWORD_XPATH)).clear();
		driver.findElement(By.xpath(XpathConstants.USER_PASSWORD_XPATH)).sendKeys(AssertionConstants.USER_PASSWORD);

		inspectAndClickOnElement(XpathConstants.LOGIN_SUBMIT_XPATH);

		//driver.findElement(By.xpath(XpathConstants.LOGIN_SUBMIT_XPATH)).click();
	}

}
