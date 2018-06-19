package DriverTesting.DriverTesting;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DriverTesting.Base.BaseClass;
import DriverTesting.Constants.AssertionConstants;
import DriverTesting.Constants.Constants;
import DriverTesting.Constants.XpathConstants;
import DriverTesting.util.FileReadUtil;
import DriverTesting.util.TextBoxUtils;

public class HomePageLoginTest  extends BaseClass{
	
	private final static String DIR = System.getProperty("user.dir") + "/CsvFiles";
	@Test(dataProvider = "getLogInDataProvider")
	public void homePageLogin(String userId, String password) throws InterruptedException {
		
		TextBoxUtils textBoxUtils=getTextBoxUtils();
		textBoxUtils.enteringValuesUsingSendkeys(inspectElement(XpathConstants.USER_ID_XPATH),userId);
		Thread.sleep(1000);
		textBoxUtils.enteringValuesUsingSendkeys(inspectElement(XpathConstants.USER_PASSWORD_XPATH), password);
        Thread.sleep(1000);
		inspectAndClickOnElement(XpathConstants.LOGIN_SUBMIT_XPATH);
		Thread.sleep(2000);

	}
	
	
	@DataProvider
	private Object[][] getLogInDataProvider() {
	final File harDir = new File(DIR);
	String filePath = Constants.EMPTY_STRING;
	//filePath = harDir.toString() + Constants.FORWARD_SLASH + "loginCredentials.csv";
	//final Object[][] data = FileReadUtil.CSVReader(filePath);
	filePath = harDir.toString() + Constants.FORWARD_SLASH + "dataFile.xlsx";
	final Object[][] data = FileReadUtil.readingExcelFile(filePath, "loginData");
	return data;
	}

}
