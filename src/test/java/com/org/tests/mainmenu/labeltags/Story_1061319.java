package com.org.tests.mainmenu.labeltags;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061319 extends BaseTest {

	String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	ArrayList<String> previous_data, after_data;
	String dataEnteredCode2, dataEnteredDescription2;
	
	@Test(priority = 1, description = "Test Case 1110284:VPLX: Label Tags [UI]: When selecting multiple Label Tags, "
			+ "the delete link for each label tag will be disabled")
	public void Test01_1110284(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Test Case 1110284:VPLX: Label Tags [UI]: When selecting multiple Label Tags, "
				+ "the delete link for each label tag will be disabled");
		
		test.landingPageActions.navigateToFeature("Label Tags");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", "UI_NewLabel_Tag1_" 
				+ String.valueOf(System.currentTimeMillis()).substring(10));
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription",
				"UI_NewLabel_Tag_" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_" 
				+ String.valueOf(System.currentTimeMillis()).substring(10));
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Label Tag");
		dataEnteredCode2 = test.supportDataActions.enterValueOnAddDistributorPage("labelTagCode", "UI_NewLabel_Tag2_" 
				+ String.valueOf(System.currentTimeMillis()).substring(10));
		dataEnteredDescription2 = test.supportDataActions.enterValueOnDescriptionLabelTagPage("labelTagDescription",
				"UI_NewLabel_Tag_" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("labelTagNotes", "UI_LabelTag_" 
				+ String.valueOf(System.currentTimeMillis()).substring(10));
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("labelTagSortOrder", "3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(dataEnteredCode2);
		
		
		test.siteConfigurationAction.enterSearchTermInSearchField("UI_NewLabel_Tag", "searchFilter");
		
		Assert.assertEquals(test.supportDataActions.selectCheckboxOfRecord(dataEnteredCode, true), true);
		Assert.assertEquals(test.supportDataActions.selectCheckboxOfRecord(dataEnteredCode2, true), true);
		
		Assert.assertFalse(test.supportDataActions.isActionButtonEnabled(dataEnteredCode, "Delete"),
				"[ASSERTION FAILED]: Delete button is not disbled for label tag '" + dataEnteredCode + "'");
		Assert.assertFalse(test.supportDataActions.isActionButtonEnabled(dataEnteredCode, "Delete"),
				"[ASSERTION FAILED]: Delete button is not disbled for label tag '" + dataEnteredCode + "'");
	}
	
	
	@Test(priority = 2, description = "Label Tags [UI]: When deleting multiple Label Tags, a confirmation pop-up"
			+ " with details of the selected label tags is displayed")
	public void Test02_1110330(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Label Tags [UI]: When deleting multiple Label Tags, a confirmation pop-up\"\r\n" 
				+ "with details of the selected label tags is displayed");
		
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Tags"));
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Delete Selected");
		
		Assert.assertTrue(test.supportDataActions.verifyListItemOnPoup(dataEnteredCode),
				"[ASSERTION FAILED]: Label tag: '" + dataEnteredCode + "' is not there on delete popup");
		Assert.assertTrue(test.supportDataActions.verifyListItemOnPoup(dataEnteredCode2),
				"[ASSERTION FAILED]: Label tag: '" + dataEnteredCode2 + "' is not there on delete popup");
		
		test.supportDataActions.clickButton("primary");
		
		test.supportDataActions.resetSearch();
		
		Assert.assertFalse(test.supportDataActions.isButtonWithTextPresent(dataEnteredCode));
		Assert.assertFalse(test.supportDataActions.isButtonWithTextPresent(dataEnteredCode2));
	}

}
