package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_112 extends BaseTest{
	
	String itemID;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;
	

	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test01_1060153(Method method) {
		/*
		 * test.landingPageActions.navigateToFeature("Item Management");
		 * test.siteConfigurationAction.enterRandomValueInRichInputField(
		 * TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		 * test.siteConfigurationAction.clickActionbutton("Actions");
		 * test.siteConfigurationAction.clickActionbutton("Add New Item");
		 * //test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		 * test.siteConfigurationAction.enterDataInInputField("genericName",
		 * "Systemlevelfacilityx" + System.currentTimeMillis()); itemID=
		 * test.siteConfigurationAction.enterDataInInputField("itemId",
		 * "SystemlevelItem77x"+System.currentTimeMillis());
		 * 
		 * test.siteConfigurationAction.selectValueForDropDown("dispensingFormKey",
		 * TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		 * test.siteConfigurationAction.selectValueForDropDown("dispensingUnitKey",
		 * TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		 * 
		 * test.siteConfigurationAction.selectValueForDropDown("medicationClassKey",
		 * TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		 * test.siteConfigurationAction.clickCheckboxfacilityitemlevel(
		 * TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		 * test.siteConfigurationAction.clickButton("save");
		 */
		/*
		 * test.landingPageActions.navigateToFeature("Main Menu");
		 * 
		 * 
		 * 
		 * test.landingPageActions.navigateToFeature("Inventory Storage Areas(ISAs)");
		 * test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		 * test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		 * test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		 * test.siteConfigurationAction.selectValueForDropDown("facilitykey",
		 * "SunFacility"); ISAName =
		 * test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup
		 * ("name", "Name" + System.currentTimeMillis()); shortName =
		 * test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup
		 * ("input", "shortName" + System.currentTimeMillis());
		 * 
		 * test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
		 * test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);
		 * 
		 * test.siteConfigurationAction.clickTab("Carousel Settings"); type =
		 * test.siteConfigurationAction.selectValueForDropDown("carouselKey",
		 * "Test_Carousel");
		 * 
		 * deviceNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "devicenumber", "123456");
		 * 
		 * ipAddress =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "ipAddressValue", "10.11.22.34");
		 * 
		 * portNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "portNumber", "46345");
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "connectionResetMinutes", "5");
		 * test.siteConfigurationAction.clickTab("ISA Configuration");
		 * test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		 * 
		 * test.siteConfigurationAction.clickTab("Display Settings");
		 * test.siteConfigurationAction.clickTab("Approved Computers");
		 * test.siteConfigurationAction.clickSaveButton();
		 * 
		 * 
		 * 
		 * 
		 */
		 test.landingPageActions.navigateToFeature("Main Menu");
		 
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		}
	

}
