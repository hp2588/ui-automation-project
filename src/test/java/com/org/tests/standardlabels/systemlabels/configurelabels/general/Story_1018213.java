package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.actions.Site_Configuration_Page_Actions;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018213 extends BaseTest{
	String systemLabelName;
	String addStandardLabelTitle = "Add Standard Label";
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state")
	public void Test01_1070171(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state");
		
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category", 1);
		test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(  "SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName); 
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Active");
		
		//test.siteConfigurationAction.clickEditSystemLabelLink("LabelName1585567661961", "Edit Standard Label", "Edit");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		//test.siteConfigurationAction.clickEditSystemLabelLink(systemLabelName, "Edit Standard Label", "Edit");
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("isActive"));
		
	}
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify on landing "
			+ "Edit screen Active/Inactive toogle button present")
	public void Test02_1070170(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify on landing Edit screen Active/Inactive toogle button present");
		
		test.siteConfigurationAction.verifyToggleOption("Active");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
	
	}
	
	@Test(priority = 3, description = "VPLX:Configure Labels:UI:Verify user have option to edit "
			+ "active/inactive for labels on edit system screen via toggle button")
	public void Test03_1048537(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify user have option to edit active/inactive for labels on edit system screen via toggle button");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		//test.siteConfigurationAction.selectDropdown("facility", getData("SystemLabel.FacilityName"));
		//test.siteConfigurationAction.selectDropdown("stock", getData("SystemLabel.StockName"));
		//test.siteConfigurationAction.selectDropdown("category", getData("SystemLabel.CategoryName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
	//	test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.supportDataActions.verifyButtons("cancel");
		test.supportDataActions.verifyButtons("save");
		test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("isActive");
		
		test.siteConfigurationAction.clickToggleButton("false", "isActive");
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Inactive");
		
	}
	
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:UI:Verify user change the label "
			+ "from Active to Inactive via toogle button")
	public void Test04_1070172(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user change the label from Active to Inactive via toogle button");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Active");
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("isActive");
		test.siteConfigurationAction.clickToggleButton("false", "isActive");
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Inactive");
		
	}
	
	
	@Test(priority = 5, description = "VPLX:Configure Labels- General:UI:Verify user change the label "
			+ "from Inactive to Active via toogle button")
	public void Test05_1070173(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user change the label from Inactive to Active via toogle button");
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.siteConfigurationAction.clickToggleButton("true", "isActive");
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Active");
		
	}

}
