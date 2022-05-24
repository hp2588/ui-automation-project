package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018218 extends BaseTest{
	String systemLabelName;
	String addStandardLabelTitle = "Add Standard Label";
	
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:UI:Verify enabled add button present on sytem label screen.")
	public void Test01_1046901(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify enabled add button present on sytem label screen.");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.verifyButtonIsEnabled("Add");
	}
	
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify Name ,stock and cateogry are mandatory field")
	public void Test02_1046933(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify Name ,stock and cateogry are mandatory field");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		test.siteConfigurationAction.verifyFieldIsMandatory("name");
		test.siteConfigurationAction.verifyFieldIsMandatory("facility");
		test.siteConfigurationAction.verifyFieldIsMandatory("stock");
		test.siteConfigurationAction.verifyFieldIsMandatory("category");
	}
	
	
	@Test(priority = 3, description = "VPLX:Configure Labels- General:[UI]:Cancel,import label and design button is present in add Standard label pop-up.")
	public void Test03_1046936(Method method) {
		test.siteConfigurationAction.verifyButtonIsPresent("Cancel");
		test.siteConfigurationAction.verifyButtonIsPresent("New Design");
		test.siteConfigurationAction.verifyInputWithTypeIsPresent("file");
	}
	
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:UI:Verify on completion of addition of levels user able to get successfull message.")
	public void Test04_1046936(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on completion of addition of levels user able to get successfull message.");
		
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButtonWithoutWait();
		test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
	}
	
	
	@Test(priority = 5, description = "VPLX:Configure Labels- General:UI:Verify by default adding system labels be in active state")
	public void Test05_1047867(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify by default adding system labels be in active state");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);

		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName,"Active");
	}
	
	
	// TODO - Yugal - Need to check
	@Test(priority = 06, description = "VPLX:Configure Labels:UI:Verify user assign priorities for system labels  while adding system labels")
	public void Test06_1047875(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify user assign priorities for system labels  while adding system labels");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Active");
	}
	
	
	@Test(priority = 7, description = "VPLX:Configure Labels- General:UI:Verify edit link present in Sytem Label")
	public void Test07_1048472(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify edit link present in Sytem Label");
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
	}
	
	
	@Test(priority = 8, description = "VPLX:Configure Labels:UI:Verify pre-populated value comes for label when click on edit link")
	public void Test08_1048477(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify pre-populated value comes for label when click on edit link");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"), systemLabelName);	
	}
	
	
	@Test(priority = 9, description = "VPLX:Configure Labels:UI:Verify cancel and save button present on edit system label screen")
	public void Test09_1048492(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify cancel and save button present on edit system label screen");
		
		test.supportDataActions.verifyButtons("cancel");
		test.supportDataActions.verifyButtons("save");
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");
	}
	
	
	

	@Test(priority = 10, description = "VPLX:Configure Labels:UI:Verify appended level from assigned to unassigned and unassigned to assigned edit functionality present for user via toggle button")
	public void Test10_1048596(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify appended level from assigned to unassigned and unassigned to "
				+ "assigned edit functionality present for user via toggle button");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		
		test.siteConfigurationAction.clickTab("Appended Labels");
		String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, true);
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		System.out.println("before: "+ before_appendedLabels+"; after:  "+after_appendedLabels);
		Assert.assertEquals(before_appendedLabels+1, after_appendedLabels,
				"[ASSERTION FAILED] :  Appended Label is not assigned");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, false);
		Assert.assertEquals(before_appendedLabels, after_appendedLabels-1,
				"[ASSERTION FAILED] : Appended Label is not unassigned");
	}
	
	
	@Test(priority = 11, description = "VPLX:Configure Labels:UI:Verify cancel pop-up come when user click on cancel button")
	public void Test11_1048662(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify cancel pop-up come when user click on cancel button");
		
		test.supportDataActions.verifyButtons("cancel");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.verifyPopupMessageSystemLabel(getData("SystemLabel.popUpMessage"));
		test.siteConfigurationAction.clickButton("primary");
	}
	
	
	@Test(priority = 12, description = "VPLX:Configure Labels:UI:Verify check/uncheck present for pick quantity for number of labels printed in Add system level screen")
	public void Test12_1048675(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify check/uncheck present for pick quantity for number of labels printed in Add system level screen");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.verifyCheckboxForPickQuantityOnSystemLabel("isPrintPickQuantityCopies");
	}
	
	
	@Test(priority = 13, description = "VPLX:Configure Labels- General:UI:Verify on clicking on add button name come as a text field which is mandatory")
	public void Test13_1068227(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking on add button name come as a text field which is mandatory");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		test.siteConfigurationAction.verifyFieldIsMandatory("name");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("labelName"), 50 
				,"[ASSERTION FAILED] :System Label is not accepting 50 characters");
	}
	
	
	@Test(priority = 14, description = "VPLX:Configure Labels- General:UI:Verify on clicking on add button stock come as a drop down which is mandatory")
	public void Test14_1068229(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking on add button name come as a text field which is mandatory");

		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("stock");
		test.siteConfigurationAction.verifyFieldIsMandatory("stock");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
	}
	
	
	@Test(priority = 15, description = "VPLX:Configure Labels- General:UI:Verify on clicking on add button category come as a drop down which is mandatory")
	public void Test15_1068231(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking on add button category come as a drop down which is mandatory");
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("category");
		test.siteConfigurationAction.verifyFieldIsMandatory("category");
		test.siteConfigurationAction.selectDropdown("category", getData("SystemLabel.CategoryName"));
	}
	
	
	@Test(priority = 16, description = "VPLX:Configure Labels- General:UI:Verify on clicking on add button assign priority come as a drop down which is non-mandatory")
	public void Test16_1068242(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking on add button assign priority come as a drop down which is non-mandatory");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.verifyFieldIsNotMandatory("priority");
		test.siteConfigurationAction.clickSaveButton();
	}
	
	
	@Test(priority = 17, description = "VPLX:Configure Labels:UI: User is able to edit assigned priorities using Toggle buttons")
	public void Test17_1048593(Method method) {
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		String trans_priority= test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		int before_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, true);
		
		int after_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		System.out.println("Assigned priority count - before: "+ before_assignedPriority + "; Assigned priority count - after : "+after_assignedPriority);
		Assert.assertEquals(before_assignedPriority+1, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not assigned");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, false);
		after_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		Assert.assertEquals(before_assignedPriority, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not unassigned");
		
	}
	
	
}
