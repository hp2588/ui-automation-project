package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061212 extends BaseTest{
	String systemLabelName;
	String addStandardLabelTitle = "Add Standard Label";
	
	@Test(priority = 1 , description = "VPLX:Configure Labels- General:UI:Verify user can not edit the category which is the required field")
	public void Test01_1046901_1070579(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user can not edit the category which is the required field");
		test.landingPageActions.navigateToFeature("Standard Labels");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		Assert.assertEquals(test.siteConfigurationAction.verifyFieldIsNotEditable("Category","Category"),"label");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		
	}
	
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify edit link present on system label screen")
	public void Test02_1070573(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify edit link present on system label screen");
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}

	@Test(priority = 3, description = "VPLX:Configure Labels- General:UI:Verify on clicking edit link page landed on Edit system label")
	public void Test03_1070575(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking edit link page landed on Edit system label");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.enterDataInInputField("labelName", systemLabelName+"Edited-Label");
		
	}
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:UI:Verify user edit the label name")
	public void Test04_1070576(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the label name");
		
		systemLabelName	=test.siteConfigurationAction.enterDataInInputField("labelName",systemLabelName+"Edited-Label");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}

	@Test(priority = 5, description = "VPLX:Configure Labels- General:UI:Verify user edit the stock which is the required field")
	public void Test05_1070578(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the stock which is the required field");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",2);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}
	

	@Test(priority = 6 ,description = "VPLX:Configure Labels- General:UI:Verify user edit the priority assignment which is the optional field")
	public void Test06_1070581(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the priority assignment which is the optional field");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		//test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		String trans_priority= test.siteConfigurationAction.getColumnFirstData("1");
		int before_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, true);
		int after_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		Assert.assertEquals(before_assignedPriority + 1, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not assigned");		
		test.siteConfigurationAction.clickSaveButton();
		
	}
	
	// TODO - Yugal - need to check
	@Test(priority = 7 ,description = "VPLX:Configure Labels- General:UI:Verify user edit the labels "
			+ "to append using toggle button which is the optional field")
	public void Test07_1070582(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the labels to append using toggle button which is the optional field");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.clickTab("Appended Labels");
		String trans_priority= test.siteConfigurationAction.getColumnFirstData("1");
		int before_assignedPriority = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, true);
		int after_assignedPriority = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_assignedPriority + 1, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not assigned");	
		test.siteConfigurationAction.clickSaveButton();
		
	}
	
	@Test(priority = 8 ,description = "VPLX:Configure Labels- General:UI:Verify checkbox for Pick quantity determines number of labels printed displayed on edit label page")
	public void Test08_1070591(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify checkbox for Pick quantity determines number of labels printed displayed on edit label page");
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.verifyCheckboxForPickQuantityOnSystemLabel("isPrintPickQuantityCopies");
		
	}
	
	
}
