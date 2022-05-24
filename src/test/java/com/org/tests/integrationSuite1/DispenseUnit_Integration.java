package com.org.tests.integrationSuite1;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class DispenseUnit_Integration extends BaseTest {

	@Test(priority = 1, description = "VPLX: Dispense Unit: [UI]: [Integration]: When a Dispense Unit is updated, the updated value gets populated in drop-down on Add/Edit Item screen")
	public void Test01_1107006_1047750(Method method) {
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));
		String DispenseUnitCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode1", DispenseUnitCode1);

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.supportDataActions.verifyDropdownElementsDefaultRule("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.supportDataActions.verifyDropdownElementsDefaultRule("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));
	}
	
	@Test(priority = 2, description = "description = \"VPLX: Dispense Unit [UI] [Integration]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editing Dispense Unit if it is being used elsewhere in the system\"")
	public void Test02_1047849(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI] [Integration]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editing Dispense Unit if it is being used elsewhere in the system");
	
	test.landingPageActions.navigateToFeature("Dispense Units");	
	test.siteConfigurationAction.selectValueForDropDown("externalSystems", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode"), "search");
	test.supportDataActions.clickAddButtonOnDistributor("edit");
	
	//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim(), "Edit Dispense Unit");
	Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
	Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));
	test.supportDataActions.clickButton("save");
	test.supportDataActions.verifyErrorMessageonDispenseUnitAlert(getData("ErrorMessage.AssociatedDispenseUnit"), "alert");
	
	}

	@Test(priority = 3, description = "VPLX: Dispense Unit: [UI]: [Integration]: When a Dispense Unit,which is not mapped to any item is made inactive, then the inactive Dispense Unit is not displayed in dropdown on Add/Edit Item")
	public void Test03_1107007(Method method) {
		
	//	test.landingPageActions.navigateToFeature("Dispense Units");
	//	test.siteConfigurationAction.selectValueFromDropDown("externalSystems", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		test.siteConfigurationAction.verifyAddDispenseUnitPopupGetsClosedOnClickingCancelButton();
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		test.supportDataActions.clickButton("save");

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1")));

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode1")));
	}
	
	

}
