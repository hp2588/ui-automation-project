package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class StandardLabels_Feature extends BaseTest{
	String systemLabelName;
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:[UI]:To verify that "
			+ "Enabled add button is present on standard label screen.")
	public void Test01_1046901(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify enabled add button present on sytem label screen.");
		
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.verifyButtonIsEnabled("Add");
	}
	
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:[UI]: To verify that "
			+ "Name, stock, facility and cateogry are mandatory fields.")
	public void Test02_1046933(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify Name ,stock and cateogry are mandatory field");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		test.siteConfigurationAction.verifyFieldIsMandatory("name");
		test.siteConfigurationAction.verifyFieldIsMandatory("facility");
		test.siteConfigurationAction.verifyFieldIsMandatory("stock");
		test.siteConfigurationAction.verifyFieldIsMandatory("category");
	}


	@Test(priority = 3, description = "VPLX:Configure Labels- General:[UI]: To verify that "
			+ "on clicking on add button, assign priority come as a drop down which is non-mandatory")
	public void Test17_1068242(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on clicking on add button assign priority come as a drop down which is non-mandatory");
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("priority");
		test.siteConfigurationAction.verifyFieldIsNotMandatory("priority");
	}
	
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:[UI]:To verify user can edit Standard Label.")
	public void Test07_1048472(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify edit link present in Sytem Label");
		test.siteConfigurationAction.verifyEditLinkAgainstSystemLabels("Edit");
	}
	
	
	@Test(priority = 6, description = "VPLX:Configure Labels- General:[UI]: To verify that user able to "
			+ "change the label from Inactive to Active via toggle button.")
	public void Test06_1070173(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user change the label from Inactive to Active via toogle button");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		
		  systemLabelName=test.siteConfigurationAction.enterDataInInputField(
		  "labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
			test.siteConfigurationAction.selectValueFromDropDownByIndex("category", 1);
		  test.siteConfigurationAction.clickSaveButton();
		 // test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		  test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
	}
	
	
	@Test(priority = 7 ,description = "VPLX:Configure Labels- General:[UI]: To verify that user is able to "
			+ "edit the labels to append using toggle button which is the optional field")
	public void Test07_1070582(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the labels to append using toggle button which is the optional field");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName, "Edit Standard Label", "Edit");
		test.siteConfigurationAction.clickTab("Appended Labels");
		String trans_priority= test.siteConfigurationAction.getColumnFirstData("1");
		int before_assignedPriority = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, true);
		int after_assignedPriority = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_assignedPriority+1, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not assigned");	
		test.siteConfigurationAction.clickSaveButton();
	}
	
	
	@Test(priority = 8, description = "VPLX:Configure Labels- General:[UI]: To verify that "
			+ "Duplicate label name is getting displayed with name(copy)")
	public void Test04_1069410(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify in duplicate label name displayed with name(copy)");
	
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		  systemLabelName=test.siteConfigurationAction.enterDataInInputField(
		  "labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
			test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		  test.siteConfigurationAction.clickSaveButton();
		 // test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		  test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
		  test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName,"Duplicate Label","Duplicate");
		  Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"),systemLabelName+" - Copy");
		  test.supportDataActions.verifyButtons("save");
	}
	
	
	@Test(priority = 9, description = "VPLX:Configure Labels- General:[UI]: To verify that "
			+ "Prefilled values is getting populated when pop up open after clicking Duplicated link.")
	public void Test03_1069403(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify pre-filled entries present in the duplicate label pop-up");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		  systemLabelName=test.siteConfigurationAction.enterDataInInputField(
		  "labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
			test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		  test.siteConfigurationAction.clickSaveButton();
		 // test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		  test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
		  test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName,"Duplicate Label","Duplicate");
		Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"),systemLabelName+" - Copy");
		Assert.assertTrue(!test.siteConfigurationAction.verifyDefaultValueinSystemLabel("stock").isEmpty());
		Assert.assertTrue(!test.siteConfigurationAction.verifyDefaultValueinSystemLabel("facility").isEmpty());
		 
		test.supportDataActions.verifyButtons("save");
	}
	
	
	@Test(priority = 10, description = "VPLX:Configure Labels- General:[UI]:To verify "
			+ "Assigned appended label for duplicate label")
	public void Test07_1073961(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:Assigned appended label for duplicate label");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		  systemLabelName=test.siteConfigurationAction.enterDataInInputField(
		  "labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
			test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		  test.siteConfigurationAction.clickSaveButton();
		 // test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		  test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
		  test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName,"Duplicate Label","Duplicate");
		  Assert.assertEquals(test.siteConfigurationAction.verifyInputFieldIsAutopopulated("labelName"),systemLabelName+" - Copy");
		  test.supportDataActions.verifyButtons("save");
			test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName, "Edit Standard Label", "Edit");
			test.siteConfigurationAction.clickTab("Appended Labels");
			String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
			int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
			test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, true);
			int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
			System.out.println("priorityyy"+ before_appendedLabels+"afterrrrr"+after_appendedLabels);
			 test.supportDataActions.verifyButtons("save");
			test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName,"Duplicate Label","Duplicate");
			Assert.assertEquals(before_appendedLabels+1, after_appendedLabels);
			test.supportDataActions.verifyButtons("save");
	}
	
	
	@Test(priority = 11, description = "VPLX:Configure Labels- General:[UI]: To verify that user is able to "
			+ "Unassign appended label for duplicate label.")
	public void Test08_1073962(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]: To verify that user is able to Unassign appended label for duplicate label.");
		
		test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName, "Edit Standard Label", "Edit");
		test.siteConfigurationAction.clickTab("Appended Labels");
		
		String appended_labels= test.siteConfigurationAction.getColumnFirstData("1");
		int before_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(appended_labels, false);
		int after_appendedLabels = test.siteConfigurationAction.getTabCount("Appended Labels");
		Assert.assertEquals(before_appendedLabels, after_appendedLabels-1,
					"[ASSERTION FAILED] : Appended Label is not unassigned");	
		 test.supportDataActions.verifyButtons("save");
	}
	
	
	@Test(priority = 12, description = "VPLX:Configure Labels- General:[UI]: To verify that Duplicate label "
			+ "name,stock,category is present as mandatory field while assign priority as non mandatory field")
	public void Test05_1069421(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify in duplicate label name,stock,category present as mandatory field while assign priority as non mandatory field");
		test.siteConfigurationAction.verifyFieldIsMandatory("name");
		test.siteConfigurationAction.verifyFieldIsMandatory("stock");
		test.siteConfigurationAction.verifyFieldIsMandatory("category");
		test.siteConfigurationAction.verifyFieldIsNotMandatory("priority");	
	}
	
	
	@Test(priority = 13 ,description = "VPLX:Configure Labels- General:[UI]: To verify that user is able to "
			+ "edit the priority assignment which is the optional field.")
	public void Test06_1070581(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the priority assignment which is the optional field");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName, "Edit Standard Label", "Edit");
		String trans_priority= test.siteConfigurationAction.getColumnFirstData("1");
		int before_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		test.siteConfigurationAction.AssignOrUnAssignTransactionPriority(trans_priority, true);
		int after_assignedPriority = test.siteConfigurationAction.getTabCount("Assigned Priorities");
		Assert.assertEquals(before_assignedPriority+1, after_assignedPriority,
				"[ASSERTION FAILED] : Priority is not assigned");		
		test.siteConfigurationAction.clickSaveButton();

	}
	
	
	@Test(priority = 14, description = "VPLX:Configure Labels- General:[UI]: To verify that user is able to edit the label name.")
	public void Test04_1070576(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user edit the label name");
		systemLabelName	=test.siteConfigurationAction.enterDataInInputField("labelName",systemLabelName+"Edited-Label");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
	}
	
	
	@Test(priority = 15, description = "VPLX:Configure Labels- General:[UI]: To verify that user is unable to "
			+ "edit Facility Name on Edit Standard Label screen.")
	public void Test02_1070573(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify edit link present on system label screen");
		
		test.siteConfigurationAction.verifyEditLinkAgainstSystemLabels("Edit");
	}
	
}
