package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018223 extends BaseTest{
	String systemLabelName;	
	String addStandardLabelTitle = "Add Standard Label";
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:[UI]:Assigned appended label for duplicate label")
	public void Test01_1073961(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:Assigned appended label for duplicate label");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.clickSaveButton();
		 // test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
		test.siteConfigurationAction.clickStandardLabelLink(systemLabelName, "Duplicate Label", "Duplicate");
		Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"), systemLabelName + " - Copy");
		systemLabelName = systemLabelName + " - Copy";
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.clickTab("Appended Labels");
		String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, true);
		
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		System.out.println("before: " + before_appendedLabels+";  after: " + after_appendedLabels);
		Assert.assertEquals(after_appendedLabels, before_appendedLabels+1);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(after_appendedLabels, before_appendedLabels+1);
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		
	}
	
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:[UI]:User is able to Unassign appended label for duplicate label")
	public void Test02_1073962(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:User is able to Unassigned appended label for duplicate label");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.clickTab("Appended Labels");
		
		String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, false);
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_appendedLabels-1, after_appendedLabels,
				"[ASSERTION FAILED] : Appended Label is not unassigned");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_appendedLabels-1, after_appendedLabels);
		
	}
	
	
	@Test(priority = 3, description = "VPLX:Configure Labels- General:[UI]:Use is able to "
			+ "assigned appended label and priority for new label")
	public void Test03_1073963(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:Use is able to assigned appended label and priority for new label");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		//Adding second system label
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		String systemLabelName_2 = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName_2);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName_2);
		test.siteConfigurationAction.clickTab("Appended Labels");
		String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
		
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, true);
		
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		System.out.println("before assigning appended label: " + before_appendedLabels +""
				+ "\nafter assigning appended label: " + after_appendedLabels);
		Assert.assertEquals(before_appendedLabels + 1 , after_appendedLabels);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName_2);
		test.siteConfigurationAction.clickTab("Appended Labels");
		after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_appendedLabels + 1, after_appendedLabels);
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		
	}

	@Test(priority = 4, description = "VPLX:Configure Labels- General:[UI]:User able to change "
			+ "the label from Inactive to Active via toggle button")
	public void Test04_1070173(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:User able to change the label "
				+ "from Inactive to Active via toogle button");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("isActive");
		test.siteConfigurationAction.clickToggleButton("false", "isActive");
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Inactive");
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		
	}
	
	@Test(priority = 5, description = "VPLX:Configure Labels- General:[UI]User is able to unassigned appended label and unassigned-priority for new label")
	public void Test05_Test06_1073964_1073991(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]User is able to unassigned appended label and unassigned-priority for new label");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		String systemLabelName_1 = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName_1);
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		String systemLabelName_2 = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName_2);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName_2);
		test.siteConfigurationAction.clickTab("Appended Labels");
		String appended_labels = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.selectPriorityInLabelEdit(appended_labels, true);
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		
		test.siteConfigurationAction.selectPriorityInLabelEdit(appended_labels, false);
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_appendedLabels-1, after_appendedLabels,
				"[ASSERTION FAILED] : Appended Label is not unassigned");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName_2);
		test.siteConfigurationAction.clickTab("Appended Labels");
		int actual_appended_labels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(actual_appended_labels, after_appendedLabels,
				"[ASSERTION FAILED] : Appended Label is not unassigned");
		/*
		 * test.landingPageActions.navigateToMenu("Main Menu");
		 * test.landingPageActions.navigateToFeature("Standard Labels");
		 * test.siteConfigurationAction.
		 * clickAddButtonToAddNewSystemLabel("Add a new Standard Label"); String
		 * systemLabelName_11=test.siteConfigurationAction.enterDataInInputField(
		 * "labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		 * test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		 * test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		 * test.siteConfigurationAction.selectValueFromDropDown(
		 * "category","Auto Dispensing Cabinet");
		 * test.siteConfigurationAction.selectPriority("STAT Order", true);
		 * test.siteConfigurationAction.clickSaveButton();
		 * test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName_11,
		 * "Edit Standard Label", "Edit");
		 * test.siteConfigurationAction.clickTab("Assigned Priorities"); String
		 * trans_priority= test.siteConfigurationAction.getColumnFirstData("1"); int
		 * before_assignedPriority =
		 * test.siteConfigurationAction.getTabCount("Assigned Priorities");
		 * test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(
		 * trans_priority, false); int after_assignedPriority =
		 * test.siteConfigurationAction.getTabCount("Assigned Priorities");
		 * Assert.assertEquals(before_assignedPriority-1, after_assignedPriority,
		 * "[ASSERTION FAILED] : Priority is not assigned");
		 * test.siteConfigurationAction.clickSaveButton();
		 * 
		 * test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName_11,
		 * "Edit Standard Label", "Edit"); int priority_new=
		 * test.siteConfigurationAction.getTabCount("Assigned Priorities");
		 * Assert.assertEquals(priority_new, after_assignedPriority,
		 * "[ASSERTION FAILED] : Priority is not unassigned");
		 */
		
	}
	
	
}
