package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975823 extends BaseTest{
	
	String itemID,theraputic_code,itemName;
	
	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to click on Add item under the Actions tab and Add screen gets displayed.")
	public void Test01_1062862(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to click on Add item under the Actions tab and Add screen gets displayed.");

		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.landingPageActions.navigateToFeature("Main Menu"); 
		test.landingPageActions.navigateToFeature("Facilities");
		
		String External = test.siteConfigurationAction.getExternalSystemMappedToFacility(facilityOnWFAScreen);
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.verifyAndClickProductID("Add Therapeutic Class");
		
		theraputic_code=test.siteConfigurationAction.enterRandomValueInInputField("therapeuticClassCode", "Code"+System.currentTimeMillis());
		
		test.siteConfigurationAction.clickButton("save");
		
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");*/
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		}
	
	
	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add the Strength and Strength UOM Fields.")
	public void Test02_1064113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add the Strength and Strength UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("strengthAmount","112");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("strengthUnitOfMessureKey", 2);
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("strengthAmount"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("strengthUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is mandatory");
		
		
	}
	
	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Concentration Volume and Concentration Volume UOM Fields.")
	public void Test03_1064120(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Concentration Volume and Concentration Volume UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("concentrationVolumeAmount","110");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("concentrationVolumeAmount"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("concentrationVolumeUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is mandatory");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("concentrationVolumeUnitOfMessureKey", 2);
	}
	
	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Total Volume and Total Volume UOM Fields.")
	public void Test4_1064145(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Total Volume and Total Volume UOM Fields.");
		test.siteConfigurationAction.enterDataInInputField("totalVolumeAmount","112");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("totalVolumeAmount"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("totalVolumeUnitOfMessureKey"),
				"[ASSERTION FAILED]: input field is mandatory");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("totalVolumeUnitOfMessureKey", 2);
	}
	
	@Test(priority = 5, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit generic name for an item at Pharmacy Item")
	public void Test05_1062876(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add/Edit generic name for an item at Pharmacy Item");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityaa");
		test.siteConfigurationAction.clearInputBox("genericName");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("genericName"), 50,
				"[ASSERTION FAILED]: Max Length for input field Generic Name is not 50");
		}
	
	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add/Edit Item ID for an item at Pharmacy Item")
	public void Test06_1062881(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add/Edit Item ID for an item at Pharmacy Item");
		test.siteConfigurationAction.enterDataInInputField("itemId","item1");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("itemId"), 50,
				"[ASSERTION FAILED]: Max Length for input field itemId is not 50");
		}
	
	@Test(priority = 7, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit Brand name for an item at Pharmacy Item")
	public void Test07_1062878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit Brand name for an item at Pharmacy Item");
		test.siteConfigurationAction.enterDataInInputField("brandName","brand1");
		test.siteConfigurationAction.clearInputBox("brandName");
		test.siteConfigurationAction.enterDataInInputField("brandName","brand88");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("brandName"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("brandName"), 50,
				"[ASSERTION FAILED]: Max Length for input field Generic Name is not 50");
		}
	
	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI]: User checks Dosage Form and Dispense Unit as mandatory fields.")
	public void Test08_1064107(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User checks Dosage Form and Dispense Unit as mandatory fields.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueForDropDown("dispensingUnitKey","Select");
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.verifybuttonisDisabled("save");
       }
	
	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit custom fields")
	public void Test09_1064174(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add/edit custom fields");
		test.siteConfigurationAction.enterDataInInputField("customField1Text","custom11");
		test.siteConfigurationAction.enterDataInInputField("customField2Text","custom12");
		test.siteConfigurationAction.enterDataInInputField("customField3Text","custom13");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("customField1Text"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("customField2Text"),
				"[ASSERTION FAILED]: input field is mandatory");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("customField3Text"),
				"[ASSERTION FAILED]: input field is  mandatory");
	}
	
	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Mediaction class values from dropdown.")
	public void Test10_1064154(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Mediaction class values from dropdown.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
	}
	
	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to add Theraupetic class values from dropdown.")
	public void Test11_1129694(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to add Theraupetic class values from dropdown.");
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		//test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(theraputic_code);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("therapeuticClass", 1);
	}
	
	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at Pharmacy Item level .")
	public void Test12_1062865(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at Pharmacy Item level .");
		itemName=test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityx"+System.currentTimeMillis());
		itemID=test.siteConfigurationAction.enterDataInInputField("itemId","SystemlevelItem77x"+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
	}
	
	@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at Pharmacy Item level and maps it at facility level.")
	public void Test13_1062866(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: User is able to Add item at Pharmacy Item level and maps it at facility level.");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(itemName,itemName);
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		}
		
	}