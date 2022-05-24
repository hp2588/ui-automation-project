package com.org.tests.mainmenu.medicationclasses;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1087091 extends BaseTest {

	String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	ArrayList<String> previous_data, after_data;
	
	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : On Selecting multiple Medication Classes delete button should be enabled in the action menu")
	public void Test01_1115080(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : On Selecting multiple Medication Classes delete button should be enabled in the action menu");
		test.landingPageActions.navigateToFeature("Medication Classes");
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
		test.siteConfigurationAction.clickPickRoutingRuleButton("saveAdd");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickCheckboxFieldOnMedicationClass("checkboxundefined");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.selectAllCheckboxes();
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verfyDeleteLinkMedicationClass();		
	}
	
	@Test(priority = 2, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : When user deletes multiple Medication Classes the selected one should be deleted with an confirmation pop-up with details of that Classes.")
	public void Test02_1115104(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : When user deletes multiple Medication Classes the selected one should be deleted with an confirmation pop-up with details of that Classes.");
		test.supportDataActions.verifyAndClickContactTab("Delete Selected");
		Assert.assertTrue(test.supportDataActions.getDeleteMedicationCodeDEtails());
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");	
	}

}
