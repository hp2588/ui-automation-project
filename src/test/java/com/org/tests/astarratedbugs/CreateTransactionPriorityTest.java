package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class CreateTransactionPriorityTest extends BaseTest {

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
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "600");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
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
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "600");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
		test.siteConfigurationAction.clearInputBox("Search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityNameRestock, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityNameRestock));
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
	}
	
	
	/*
	 * @Test(priority = 3, description =
	 * "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority- Restock"
	 * ) public void Test03_1054082_1120778_1120777(Method method) {
	 * 
	 * test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
	 * TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
	 * test.siteConfigurationAction.
	 * verifyAndClickEditLinkTransactionPriority("Package Share");
	 * test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority(
	 * "forManualPickFlag"); test.siteConfigurationAction.clickButton("save");
	 * 
	 * test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
	 * TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
	 * test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(
	 * "Receiving");
	 * test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority(
	 * "forManualRestockFlag"); test.siteConfigurationAction.clickButton("save"); }
	 */

}
