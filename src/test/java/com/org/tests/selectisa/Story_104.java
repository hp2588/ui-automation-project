package com.org.tests.selectisa;

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

public class Story_104 extends BaseTest {

	/*
	 * Computer one without control carousel flag checked
	 */
	@Test(priority = 1, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer1(Method method) {
		test.landingPageActions.navigateToFeature("Computers");
		
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		
		test.siteConfigurationAction.clickRadioComputerButton();
		String computerName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		String IPAddress1 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress1);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName1);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress1);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName1);
		
	}
	
	/*
	 * Computer two control with carousel flag checked
	 */
	
	@Test(priority = 2, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer2(Method method) {
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		
		String computerName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		String IPAddress2 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress2);
		
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectControlCarouselCheckboxForComputer();
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName2);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress2);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName2);
		
	}
	
	/*
	 * Computer three control with carousel flag checked
	 */
	
	@Test(priority = 3, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer3(Method method) {
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		
		String computerName3 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		String IPAddress3 = DateUtil.getRandomIPAddress();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress3);
		
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectControlCarouselCheckboxForComputer();
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName3", computerName3);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress3", IPAddress3);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName3);
		
	}
}
