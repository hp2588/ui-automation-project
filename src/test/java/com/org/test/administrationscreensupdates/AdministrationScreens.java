package com.org.test.administrationscreensupdates;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class AdministrationScreens extends BaseTest {

	String[] invalidmessagefields = { "IP Address", "MAC Address" };
	List<String> sortColumns = Arrays.asList("Name", "Facilities", "Type", "Status");
	String computerName;
	
	@Test(priority = 1, description = "Test Case 1076580:VPLX: Administration Screens Updates:[UI]:To verify that "
			+ "Computer is added by clicking computers under equipment on main page"
			+ "\n&\n"
			+ "VPLX: Administration Screens Updates:[UI]:To verify that Dropdown to select facility for the computer."
			+ "\n&\n"
			+ "1076590 : VPLX: Administration Screens Updates:[UI]:Choose the facility for the computer "
			+ "from the list of all the created facilities")
	public void Test01_Test02_Test03_1076580_1076733_1076590(Method method) {
		String IPAddress = DateUtil.getRandomIPAddress();
		
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("defaultFacilityKey", 1);
		test.siteConfigurationAction.clickSaveButton();
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		
		test.siteConfigurationAction.clickRecordNameToEdit(computerName);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		
	}
	
	
	@Test(priority = 2, description = "VPLX: Administration Screens Updates:[UI]: To verify that "
			+ "\"This computer can control Carousel ISAs\" to be displayed only for type Computer")
	public void Test04_1076692(Method method) {
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.verifyMessageOnComputerPopup("controlCaraouselISA", "This computer can control Carousel ISAs");
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 3, description = "VPLX: Administration Screens Updates:[UI]: To verify that "
			+ "Type of the computer is visible on computers screen.")
	public void Test05_1076764(Method method) {
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaders(sortColumns));
	}
	
	
	@Test(priority = 4, description = "VPLX: Administration Screens Updates:[UI]:To verify that "
			+ "user is Able to edit computers")
	public void Test06_1076820(Method method) {
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", "My Facilities");
		String firstrowdata = test.siteConfigurationAction.getColumnFirstData("1");
		
		test.siteConfigurationAction.clickRecordNameToEdit(firstrowdata);
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);	
	}
	
	
	@Test(priority = 5, description = "VPLX: Administration Screens Updates:[UI]: To verify that "
			+ "State of the computer can be made active or inactive")
	public void Test07_1077786(Method method) {
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		
		test.siteConfigurationAction.clickRadioComputerButton();
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", DateUtil.getRandomIPAddress());
		
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActiveComputerToggle();
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(computerName);
		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Inactive");
		
		test.siteConfigurationAction.clickRecordNameToEdit(computerName);
		test.siteConfigurationAction.clickActiveComputerToggle();
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedHoldReasonStatus(computerName, "Active");
		
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
	}
	
	
	@Test(priority = 6, description = "VPLX: Administration Screens Updates:[UI]:To verify that "
			+ "\"This computer can control Carousel ISAs\" option should not be visible "
			+ "for the Mobile Device type")
	public void Test08_1077801(Method method) {
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.selectCheckboxComputers("isMobileFlag");
		test.siteConfigurationAction.verifyMessageOnComputerPopupNotDisplayed("controlCaraouselISA", "This computer can control Carousel ISAs");
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 7, description = "VPLX: Administration Screens Updates:[UI]: To verify that "
			+ "Rename ADM to ADC under general tab for add destination.")
	public void Test09_1080278(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.clickButton("edit-");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyADCFlags("Ignore ADC Critical Low"));
		Assert.assertTrue(test.siteConfigurationAction.verifyADCFlags("Enable ADC Quantity Rounding"));
		Assert.assertTrue(test.siteConfigurationAction.verifyADCFlags("Ignore ADC Stock Out"));
	}
	
	
	@Test(priority = 8, description = "VPLX: Administration Screens Updates:[UI]:To verify that "
			+ "Change “RX License” to \"Pharmacy License\"")
	public void Test10_1080280(Method method) {	
		Assert.assertTrue(test.siteConfigurationAction.verifyADCFlags("Pharmacy License"));
	}
	
}
