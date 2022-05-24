package com.org.tests.tq.multipartbatchpicks;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntTransactionPriorityTest extends BaseTest {

	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, priorityCodeReturn;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority")
	public void Test01_AddTP(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "2");
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "2400");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
	}

	@Test(priority = 2, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority- Restock")
	public void Test02_AddTP(Method method) {

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityNameRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		priorityCodeRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "2");
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "2400");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.clearInputBox("Search");
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityNameRestock, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityNameRestock));
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
	}
}
