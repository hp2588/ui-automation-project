package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_Computer_Pkg_Shr_Data extends BaseTest {

	@Test(priority = 1, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer3(Method method) {
		String IPAddress1 = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress1);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName1", computerName1);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress1", IPAddress1);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName1);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName1", computerName1);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress1", IPAddress1);
	}
	
	@Test(priority = 2, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test02_Add_Computer4(Method method) {
		String IPAddress2 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress2);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName2);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress2);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName2);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName2);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress2);
	}
}
