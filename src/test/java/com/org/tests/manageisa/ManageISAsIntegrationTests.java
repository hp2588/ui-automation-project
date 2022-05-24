package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ManageISAsIntegrationTests extends BaseTest {

	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem;
	String app_url;
	String facilityCode, facilityName, facilityID,userID,baseURL,roleID,IPAddress;
	String rxLicenseId, deaLicenseId, faxNumber;
	String serverPrinterName, printerName;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime,ISAName;
	String genericname,itemID;
	String updateISAName = "UpdatedISAName";


	/* Integration Tests - needs update */
	
	@Test(priority = 13, description = "VPLX:Manage ISAs:[Integration][UI]: When 'Restrict the control of this ISA to the computers listed below' is checked by user "
			+ "then computer can take control of that ISA")
	public void Test13_1117215_part1(Method method) throws Throwable {
		
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");	
        test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "3");
		Thread.sleep(3000);
        test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);

		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);

	}
	
	
	@Test(priority = 14, description = "VPLX: Manage ISAs: [UI]: When ISA is made inactive, it is not displayed on Select ISA screen when user logs in from approved computer against which the ISA has been made inactive")
	public void Test14_1106899(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: When ISA is made inactive, it is not displayed on Select ISA screen when user logs in from approved computer against which the ISA has been made inactive");
			
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");
     	test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "3");
		Thread.sleep(3000);		
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
		test.storageAreaAction.clickSaveButton();		
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		test.siteConfigurationAction.clickEditLinkCorrespondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.verifyToggleIsActive("activeFlag");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");
        test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
        test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
        test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.supportDataActions.verifyInactiveISAIsNotPresentOnWFAScreen(ISAName);

	}
	
	@Test(priority = 15, description = "VPLX: Manage ISAs: [UI]: [Integration]: User is able to mark the ISA as inactive and inactive ISA is not visible in ISA list during assignation.")
	public void Test15_1106899() throws Throwable {
 		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		
		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());		
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinISADropDownDoesNotExist("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName")), "[ASSERTION FAILED]: Value exist in dropdown"); 		
 		}
	

	@Test(priority = 16, description = "VPLX: Manage ISAs: [UI]: [Integration]: User is able to mark the ISA as inactive and inactive ISA is not visible in ISA list during assignation.")
	public void Test16_1106898_1117215_part2(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: When a new ISA is added/updated for an approved computer,"
				+ " the ISA is displayed on Select ISA screen when user logs in from the approved computer"
				
				+ "VPLX:Manage ISAs:[Integration][UI]: When 'Restrict the control of this ISA to the computers listed below' is checked by user "
			+ "then computer can take control of that ISA");
				// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");		

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");	

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");


		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "3");
		Thread.sleep(3000);
		// test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		
		// test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);

		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);

		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");		
		//test.siteConfigurationAction.verifyEditLinkCorrespondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickEditLinkCorrespondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		TestDataPropertyReaderAndWriter.setProperty("updateIsaName", "UpdatedISA");
		test.siteConfigurationAction.editISAName(updateISAName);
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.supportDataActions.verifyAddedISAonWFAScreen(updateISAName);
		}

}
