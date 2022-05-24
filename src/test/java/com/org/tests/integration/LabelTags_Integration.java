package com.org.tests.integration;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class LabelTags_Integration extends BaseTest {
	String dataEnteredCode, dataEnteredCode1, dataEnteredCode2;
	
	@Test(priority = 1, description = "VPLX: Label Tags [UI]: After clicking on the save button, "
			+ "the Label Tag is saved and can be selected from the Labels Tags drop down "
			+ "in the Add/Edit Item Management screen")
	public void Test01_1110226(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Label Tags [UI]: After clicking on the save button, the Label Tag is saved "
				+ "and can be selected from the Labels Tags drop down in the Add/Edit Item Management screen");
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tags");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", "2");
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode);
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.verifyValueIsPresentInLabelTagDropDown(dataEnteredCode, dataEnteredCode);
	}
	
	
	@Test(priority = 2, description = "VPLX: Label Tags [UI]: The user must be able to edit the Code field "
			+ "and the update is visible in the Label Tags drop down in the Item Management Add/Edit screen"
			+ "\n&\n"
			+ "VPLX: Label Tags [UI]: A warning appears when trying to delete a label tag "
			+ "that is associated to an item")
	public void Test02_1110357_1130511(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Label Tags [UI]: The user must be able to edit the Code field and the update is visible in the Label Tags drop down in the Item Management Add/Edit screen"
				+ "\n&\n"
				+ "VPLX: Label Tags [UI]: A warning appears when trying to delete a label tag that is associated to an item");
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredCode, "searchFilter");
		test.supportDataActions.clickButton("Edit");
		dataEnteredCode1 = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", "3");
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "4");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode1);
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.verifyValueIsPresentInLabelTagDropDown(dataEnteredCode1, dataEnteredCode1);
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredCode1, "searchFilter");
		test.supportDataActions.clickButton("Delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifyErrorMessageRoutingRule(
				"This Label Tag is associated with an Item. You cannot modify this Label Tag.");
	}
	
	
	@Test(priority = 3, description = "VPLX: Label Tags [UI]: After deleting a Label Tag it is no longer visible in Item Management Add/Edit Items Label Tags drop down")
	public void Test03_1110243(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Label Tags [UI]: After deleting a Label Tag it is no longer visible in Item Management Add/Edit Items Label Tags drop down");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tags");
		dataEnteredCode2 = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", "2");
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode2);
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredCode2, "searchFilter");
		test.supportDataActions.clickButton("Delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueIsNotPresentInLabelTagDropDown(dataEnteredCode2));
	}
}
