package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Transaction_Priority_Test extends BaseTest{


	String priorityName, code;

	@Test(priority = 1, description = "VPLX : Site Configuration - Add Transaction Priority: Authorized User can add Transaction priority to the system")
	public void Test01_1114471(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Site Configuration - Add Transaction Priority: Authorized User can add Transaction priority to the system");

		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickButton("save");
		//TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		//TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		
		//TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		//TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);

	}
	
	@Test(priority = 2, description = "VPLX : Site Configuration -List of Transaction Priorities configured in the System")
	public void Test02_1114471(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Site Configuration -List of Transaction Priorities configured in the System");
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
	}
	}
