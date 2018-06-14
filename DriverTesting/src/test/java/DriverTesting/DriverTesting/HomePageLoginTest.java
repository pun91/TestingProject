package DriverTesting.DriverTesting;

import org.testng.annotations.Test;

import DriverTesting.Constants.AssertionConstants;
import DriverTesting.Constants.XpathConstants;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class HomePageLoginTest {
	WebDriver driver;

	@Test
	public void homePageLogin() {
		driver.findElement(By.xpath("//input[@id='pass-login']")).click();

		driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).click();
		driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).clear();
		driver.findElement(By.xpath(XpathConstants.USER_ID_XPATH)).sendKeys(AssertionConstants.USER_ID);

		driver.findElement(By.xpath(XpathConstants.USER_PASSWORD_XPATH)).clear();
		driver.findElement(By.xpath(XpathConstants.USER_PASSWORD_XPATH)).sendKeys(AssertionConstants.USER_PASSWORD);

		driver.findElement(By.xpath(XpathConstants.LOGIN_SUBMIT_XPATH)).click();
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
