package com.org.tests.datacreation;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class AddTransactionPriorities extends BaseTest {
	
	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, priorityCodeReturn;
	int maxHoldMinutes = 120;
	int maxLockedSeconds = 7200;
	
	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority")
	public void Test01_1054082(Method method) {
		test.landingPageActions.navigateToFeature("Priorities");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName", 
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode", 
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		
	}
	
	@Test(priority = 2, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority- Restock")
	public void Test02_1054082(Method method) {

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		
		priorityNameRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		priorityCodeRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.clearInputBox("Search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityNameRestock, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityNameRestock));
		
	}
	
	@Test(priority = 3, description = "VPLX: Manage Transaction priorities:[UI] - Increase max locked seconds, max hold minutes"
			+ " for Return and Cycle Count priorities")
	public void Test03_Increase_HoldMinutes_LockedSecs_forCycleCountAndReturnPriority(Method method) {
		test.siteConfigurationAction.clearInputBox("Search");
		test.siteConfigurationAction.enterSearchTermInSearchField("Cycle Count", "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		test.siteConfigurationAction.enterSearchTermInSearchField("Return", "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
	}
	
	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority")
	public void Test04_AddNewPickPriority(Method method) {
		test.landingPageActions.navigateToFeature("Priorities");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName", 
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode", 
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "120");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		TestDataPropertyReaderAndWriter.setProperty("PriorityName2", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode2", code);
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.clearInputBox("Search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		
	}
	
}
