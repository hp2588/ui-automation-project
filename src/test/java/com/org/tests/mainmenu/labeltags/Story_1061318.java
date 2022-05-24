package com.org.tests.mainmenu.labeltags;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061318 extends BaseTest {

	String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System, dataEnteredCode1;
	int lenCode, lenSort;

	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags"
			+ " : [UI] : On main home page link is present of 'Label TAg'")
	public void Test01_Test02_Test03_1110213_1110217_1110215(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags : [UI] : On main home page link is present of 'Label TAg'");
		
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		dataEnteredCode1 = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldLabelTag("labelTagCode", 
				DateUtil.getAlphaNumericString(21),20);
		lenCode = test.supportDataActions.verifyMaxLengthOfAnSearchField("labelTagNotes");
		Assert.assertEquals(lenCode, 20);
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription",
				"UI_LabelTag_");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelTagNotes", 
				DateUtil.getAlphaNumericString(21),20);
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelTagSortOrder", 
				DateUtil.generateRandomDigits(5),4);
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode);
		//test.supportDataActions.clickEditButtonOnDistributor(dataEnteredCode);
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("Delete");
	}

	@Test(priority = 2, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags"
			+ " : [UI] : On Clicking add Label TAg 4 columns are present Code , Description , Sort order and label notes")
	public void Test04_1110216(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags : [UI] : On Clicking add Label TAg 4 columns are present Code , Description , Sort order and label notes");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		test.supportDataActions.verifyLabelTagsTextbox("labelTagCode");
		test.supportDataActions.verifyLabelTagsTextbox("labelTagNotes");
		test.supportDataActions.verifyLabelTagsTextbox("labelTagSortOrder");
	}

	@Test(priority = 3, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags "
			+ ": [UI] :  User is able to Save Label tag form  with \"Save\" button")
	public void Test05_Test06_1110224_1110225(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label Tags : [UI] :  User is able to Save Label tag form  with \"Save\" button");
		
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", 
				"2" + System.currentTimeMillis());
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription",
				"UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("saveAdd");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("save");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode);
	}

	@Test(priority = 4, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags "
			+ ": [UI] : Code will accept only Unique Entries no duplicate allowed")
	public void Test07_Test08_Test09_1110218_1110219_1110220(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags : [UI] : Code will accept only Unique Entries no duplicate allowed");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		test.supportDataActions.enterDuplicateValueOnMedicationPage("labelTagCode", dataEnteredCode);
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		lenCode = test.supportDataActions.verifyMaxLengthOfAnSearchField("labelTagCode");
		Assert.assertEquals(lenCode, 20);
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.siteConfigurationAction
				.verifyErrorMessageRoutingRule("This Code already exists. Please provide a unique Code .");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
	}

	@Test(priority = 5, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags "
			+ ": [UI] : On edit if user try to save an form with Code name same as already present "
			+ "an warning must appears \" This Hazardous Waste C Code already exists. "
			+ "Please provide a unique Therapeut")
	public void Test10_Test11_Test12_1110380_1110221_1110892(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags : [UI] : On edit if user try to save an form with Code name same as already present an warning must appears \" This Hazardous Waste C Code already exists. Please provide a unique Therapeut");
		test.supportDataActions.clickEditButtonOnDistributor(dataEnteredCode);
		test.supportDataActions.enterDuplicateValueOnMedicationPage("labelTagCode", dataEnteredCode1);
		test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		lenSort = test.supportDataActions.verifyMaxLengthOfAnSearchField("labelTagSortOrder");
		Assert.assertEquals(lenSort, 4);
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.siteConfigurationAction
				.verifyErrorMessageRoutingRule("This Code already exists. Please provide a unique Code .");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
	}
	
	@Test(priority = 6, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags : [UI] : \"Save and add another class\" button is present and when user click on save and add another. System saves the label and open add label page.")
	public void Test13_1110223_(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Label tags : [UI] : \"Save and add another class\" button is present and when user click on save and add another. System saves the label and open add label page.");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", DateUtil.getAlphaNumericString(21));
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription",
				"UI_LabelTag_");
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("saveAdd");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Add Label Tag"));	
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		test.supportDataActions.verifyNewlyAddedRecordNameInListLabelTags(dataEnteredCode);
	}
}
