package com.org.tests.integration;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class MedicationClass_Integration extends BaseTest {
	String dataEnteredCode, dataEnteredDescription, dataEnteredSort;
	
	@Test(priority = 1, description = "VPLX : [Integration]: Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : Enter sort order and saved medication is represented in item managemnt according to sort order")
	public void Test01_1115062(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : [Integration]: Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : Enter sort order and saved medication is represented in item managemnt according to sort order");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.supportDataActions.verifyMedicationClassSortOrder("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedClassCode1"), TestDataPropertyReaderAndWriter.getProperty("MedClassCode"));	
	}
	
	@Test(priority = 2, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : On Selecting delete the selected Medication Classesis deleted with a confirmation Pop-up deleted Med class is not displayed in drop-down on Add/Edit Item")
	public void Test02_1130473(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : On Selecting delete the selected Medication Classesis deleted with a confirmation Pop-up deleted Med class is not displayed in drop-down on Add/Edit Item");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("MedClassDescription"), "searchFilter");
		test.supportDataActions.clickButton("Delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifyErrorMessageRoutingRule(
				"This Medication Class is associated with an Item. You cannot modify this Medication Class .");
	}
	
	@Test(priority = 3, description = "VPLX : [Integration]:Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is able to edit any of the fields Code , Description or Sort Number and gets reflected in Add/Edit Item screen")
	public void Test03_1115105(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : [Integration]:Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is able to edit any of the fields Code , Description or Sort Number and gets reflected in Add/Edit Item screen");
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.supportDataActions.clickButton("edit");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "3");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "2");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription", dataEnteredDescription);
	}
	
	
	@Test(priority = 4, description = "VPLX:[Integration]:Item Setup - ES Parity (Formulary Reference Data) Medication Classes: [UI]:On Selecting delete Medication Classes(Not linked to item) is deleted with a confirmation Pop-up deleted Med class is not displayed in drop-down on Add/Edit Item")
	public void Test04_1115076(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Integration]:Item Setup - ES Parity (Formulary Reference Data) Medication Classes: [UI]:On Selecting delete Medication Classes(Not linked to item) is deleted with a confirmation Pop-up deleted Med class is not displayed in drop-down on Add/Edit Item");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickCheckboxFieldOnMedicationClass("checkboxundefined");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredDescription, "searchFilter");
		test.supportDataActions.clickButton("Delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode2", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription2", dataEnteredDescription);
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode2")));

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim());
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode2")));
		
	}

}
