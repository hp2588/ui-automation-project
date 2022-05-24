package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Floor_Stock_Transaction_Priorty extends BaseTest {

	String priorityName, code;

	@Test(priority = 1, description = " VPLX - Add Floor Stock Transactions.: Authrorized user can add floor stock transactions to the transaction queue.")
	public void Test01_1114330(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX - Add Floor Stock Transactions.: Authrorized user can add floor stock transactions to the transaction queue.");
		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"FStock" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);

	}
}
