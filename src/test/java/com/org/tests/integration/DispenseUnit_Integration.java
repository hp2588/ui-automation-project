package com.org.tests.integration;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class DispenseUnit_Integration extends BaseTest {

	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

	@Test(priority = 1, description = "VPLX: Dispense Unit: [UI]: [Integration]: When a Dispense Unit is updated, the updated value gets populated in drop-down on Add/Edit Item screen")
	public void Test01_1107006(Method method) {
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		/*
		 * test.supportDataActions.clickOnEditLinkCorresspondingToDosageForm(
		 * TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"),
		 * "Edit Dispense Unit");
		 */
		String DispenseUnitCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode1", DispenseUnitCode1);

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.supportDataActions.verifyDropdownElementsDefaultRule("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim());
		test.supportDataActions.verifyDropdownElementsDefaultRule("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));
	}

	@Test(priority = 2, description = "VPLX: Dispense Unit: [UI]: [Integration]: When a Dispense Unit,which is not mapped to any item is made inactive, then the inactive Dispense Unit is not displayed in dropdown on Add/Edit Item")
	public void Test02_1107007(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		/*
		 * test.supportDataActions.clickOnEditLinkCorresspondingToDosageForm(
		 * TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"),
		 * "Edit Dispense Unit");
		 */
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		test.supportDataActions.clickButton("save");

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1")));

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim());
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1")));
	}

}
