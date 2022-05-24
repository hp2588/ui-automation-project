package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018217 extends BaseTest{
	String systemLabelName;
	String addStandardLabelTitle = "Add Standard Label";
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:UI:Verify user have option to filter items for only active labels")
	public void Test01_1046608(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user have option to filter items for only active labels");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category", 1);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		test.siteConfigurationAction.verifyStandardLabelState(systemLabelName, "Active");
		test.supportDataActions.resetSearch();
	}
	
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify inactive radio button is present "
			+ "and user should be able to include / exclude inactive labels with the help of inactive radio button")
	public void Test02_1046621_1046608(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify inactive radio button is present and user should be able to include / exclude inactive labels with the help of inactive radio button");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("toggle"));
		Assert.assertTrue(test.siteConfigurationAction.verifySystemLabelStatus("6","Active"),"[ASSERTION FAILED] :In-Active records are also present");
		
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleIsActiveForSystemLabel("toggle"));
		Assert.assertTrue(test.siteConfigurationAction.verifySystemLabelStatus("6","Inactive"),"[ASSERTION FAILED] :In-Active records are not present");
		
	}
	
	@Test(priority = 3, description = "VPLX:Configure Labels- General:UI:Verify enabled search text box present at system Label")
	public void Test03_1047859(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify enabled search text box present at system Label");
		
		test.siteConfigurationAction.verifyInputField("scheduleSearch");
		
	}
	
	@Test(priority = 4, description = "VPLX:Configure Labels- General:UI:Verify enabled search text box present at system Label")
	public void Test04_1047862(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify enabled search text box present at system Label");
		
		test.siteConfigurationAction.verifyInputField("scheduleSearch");
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}
	
	@Test(priority = 5, description = "VPLX:Configure Labels- General:UI:Verify user have option for sorting on name,stock name,cateogry,status.")
	public void Test05_1048688(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify user have option for sorting on name,stock name,cateogry,status.");
		
		test.siteConfigurationAction.verifyInputField("scheduleSearch");
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.verifyAndClickSortIcon("Name");
		test.siteConfigurationAction.verifyAndClickSortIcon("Stock Name");
		test.siteConfigurationAction.verifyAndClickSortIcon("Category");
		test.siteConfigurationAction.verifyAndClickSortIcon("Status");
		
	}
	
	@Test(priority = 6, description = "VPLX:Configure Labels- General:UI:Verify message should be displayed "
			+ "if no items present while searching")
	public void Test06_1048692(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify message should be displayed if no items present while searching");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName + System.currentTimeMillis());
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), "No Matching Results.");
		
	}
	
	@Test(priority = 7, description = "VPLX:Configure Labels- General:UI:Verify searching system label "
			+ "on the basis of name with contains search")
	public void Test07_1068180(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify searching system label on the basis of name with contains search");
		test.supportDataActions.resetSearch();
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName", 
				getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category",1);
		test.siteConfigurationAction.clickSaveButton();
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SystemLabel.SuccessMessage"));
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		//test.siteConfigurationAction.verifyContainsSearch("Name",getData("SystemLabel.NameIndex"), systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
	}
	
	
	@Test(priority = 8, description = "VPLX:Configure Labels- General:UI:Verify searching system label on the basis of stock with contains search")
	public void Test08_Test09_1068181_1068182(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify searching system label on the basis of stock with contains search");
		test.supportDataActions.resetSearch();
		String stockName = test.siteConfigurationAction.getColumnFirstData("2");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(stockName);
		Assert.assertTrue(test.siteConfigurationAction.verifyContainsSearch("Stock Name", stockName));
		
		test.supportDataActions.resetSearch();
		String stockNameSubstring = stockName.substring(1);
		test.siteConfigurationAction.enterSearchTermInSearchField(stockNameSubstring);
		Assert.assertTrue(test.siteConfigurationAction.verifyContainsSearch("Stock Name", stockName));
	}
	
	
	@Test(priority = 10, description = "VPLX:Configure Labels- General:UI:Verify searching system label on the basis of category with contains search")
	public void Test10_1068187(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify searching system label on the basis of category with contains search");
		test.supportDataActions.resetSearch();
		
		String category = test.siteConfigurationAction.getColumnFirstData("4");
		test.siteConfigurationAction.enterSearchTermInSearchField(category);
		test.siteConfigurationAction.verifyContainsSearch("Category", category);	
	}
	
}
