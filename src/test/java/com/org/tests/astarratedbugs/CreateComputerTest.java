package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class CreateComputerTest extends BaseTest {

	@Test(priority = 2, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test02_Add_Computer1(Method method) {
		String IPAddress1 = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress1);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectCheckboxForCarouselISA();
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName1", computerName1);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress1", IPAddress1);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName1);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName1", computerName1);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress1", IPAddress1);
	}

	@Test(priority = 3, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test03_Add_Computer2(Method method) {
		String IPAddress2 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress2);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectCheckboxForCarouselISA();
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName2);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress2);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName2);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName2);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress2);
	}
	
	@Test(priority = 4, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test04_Add_Computer3(Method method) {
		String IPAddress3 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName3 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress3);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName3", computerName3);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress3", IPAddress3);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName3);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName3", computerName3);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress3", IPAddress3);
	}
	
	@Test(priority = 5, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test05_Add_Computer4(Method method) {
		String IPAddress4 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFieldsNew();
		String computerName4 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress4);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName4", computerName4);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress4", IPAddress4);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName4);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName4", computerName4);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress4", IPAddress4);
	}

}
