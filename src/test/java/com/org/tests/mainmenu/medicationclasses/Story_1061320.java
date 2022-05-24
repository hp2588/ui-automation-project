package com.org.tests.mainmenu.medicationclasses;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061320 extends BaseTest {

	String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System,dataEnteredCode1;
	int lenCode, lenSort;

	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is allowed to enter a unique Medication class .")
	public void Test01_1115042_1114616_1114627_1115027_1115049_1115057_1115064_1115066_1115070_1115071_1115073_1129511(
			Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is allowed to enter a unique Medication class .");
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		// test.siteConfigurationAction.selectDropdownDispenseExternal(external_System,
		// "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		test.supportDataActions.verifyAllDropdownElementsMedicatonClass("medicationClassOrderInternalCode");
		lenCode = test.supportDataActions.verifyMaxLengthOfAnSearchField("medicationClassCode");
		Assert.assertEquals(lenCode, 20);
		lenSort = test.supportDataActions.verifyMaxLengthOfAnSearchField("medicationClassSortOrder");
		Assert.assertEquals(lenSort, 4);
		dataEnteredCode = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldLabelTag("medicationClassCode", DateUtil.getAlphaNumericString(21),20);
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("medicationClassSortOrder", DateUtil.generateRandomDigits(5),4);
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("saveAdd");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("save");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode1 = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
	}
	
	@Test(priority = 2, description = "VPLX:Item Setup-ES Parity (Formulary Reference Data) Medication Classe :UI: user tries to save a form with Code name same as already present then warning message should be displayed.")
	public void Test02_1115106_1125309(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Item Setup-ES Parity (Formulary Reference Data) Medication Classe :UI: user tries to save a form with Code name same as already present then warning message should be displayed.");
		test.siteConfigurationAction.VerifyAndSearchTextMedicationClass(dataEnteredCode);
		test.supportDataActions.clickButton("Edit");
		test.supportDataActions.enterDuplicateValueOnMedicationPage("medicationClassCode", dataEnteredCode1);
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickCheckboxFieldOnMedicationClass("checkboxundefined");
		//Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabledMEdicationClass());
		test.siteConfigurationAction.clickCheckboxFieldOnMedicationClass("checkboxundefined");
		//Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabledMEdicationClass());
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.siteConfigurationAction.verifyErrorMessageRoutingRule(
				"This Medication Class Code already exists. Please provide a unique Medication Class Code .");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");

	}

	@Test(priority = 3, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is able to edit any of the fields Code , Description or Sort Number and gets reflected in Add/Edit Item screen")
	public void Test03_1115033(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User is able to edit any of the fields Code , Description or Sort Number and gets reflected in Add/Edit Item screen");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("Edit");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("Delete");
		test.supportDataActions.clickButton("Edit");
		test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "2");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
	}
	
	@Test(priority = 4, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User have an option to create a controlled med class with checkbox")
	public void Test04_1129513_1125089(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : User have an option to create a controlled med class with checkbox");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"UI_Medication_Desc_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.siteConfigurationAction.clickCheckboxFieldOnMedicationClass("checkboxundefined");
		//Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabledMEdicationClass());
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
	}

}
