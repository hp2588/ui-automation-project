package com.org.tests.mainmenu.transactionpriorities;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982966 extends BaseTest {

	ArrayList<String> priorities = new ArrayList<String>(Arrays.asList("STAT Order", "Multi-Part STAT",
			"Manual STAT Order", "STAT Redispense", "Multi-Part STAT Redispense", "Pyxis Stock Out",
			"Pyxis Critical Low", "New Order", "Multi-Part Order", "Manual New Order", "Cart Fill"));

	String priorityName, priorityName2, code, code2, app_url,top_priority;
	int pre_count, post_count;

	@Test(priority = 1, description = "VPLX: Manage Transaction Priorities:[UI]- Transaction Priority is properly appearing when Site Configuration Tab is clicked")
	public void Test01_1047995(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Transaction Priority is properly appearing when Site Configuration Tab is clicked");
		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");

		test.supportDataActions.verifyLabelIsPresent("Priorities");

	}

	@Test(priority = 2, description = "VPLX: Manage Transaction Priorities:[UI]- Transaction Priority is properly appearing when Site Configuration Tab is clicked")
	public void Test02_1052927(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Transaction Priority is properly appearing when Site Configuration Tab is clicked");
		test.siteConfigurationAction.verifyRecordListOfTransactionPriorities();
	}

	@Test(priority = 3, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test03_1129207(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");

		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 0);
		test.siteConfigurationAction.verifyTransactionPrioritiesAreDisplayed();
	}

	@Test(priority = 4, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test04_1129209(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");
		Assert.assertTrue(test.siteConfigurationAction.verifyPriorities(priorities));

	}

	@Test(priority = 5, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test05_1129208(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 0);
		Assert.assertTrue(test.siteConfigurationAction.verifyPriorities(priorities));
		// test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown",
		// 0);
	}

	@Test(priority = 6, description = "VPLX: Manage Transaction Priorities:[UI] - Check that user can change the order of transaction priority by grab the desired row for the priority and move it up or down")
	public void Test06_Test07_1053375__1129206(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that user can change the order of transaction priority by grab the desired row for the priority and move it up or down");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPriorityInList(priorityName);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName2 = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code2 = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPriorityInList(priorityName2);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");
		pre_count = test.siteConfigurationAction.checkCountOfPriorityName(priorityName2);

		test.siteConfigurationAction.dragAndDropTransactionPriority(priorityName2, priorityName);

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.PriorityReorder"));
	}

	@Test(priority = 8, description = "VPLX: Manage Transaction Priorities:[UI] -  Check the order of transaction priorities , the list is displayed based on the last display order of the rows.")
	public void Test08_1056638(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] -  Check the order of transaction priorities , the list is displayed based on the last display order of the rows.");
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Priorities");

		test.supportDataActions.verifyLabelIsPresent("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.clickButton("reset");
		post_count = test.siteConfigurationAction.checkCountOfPriorityName(priorityName2);
		Assert.assertEquals(pre_count, post_count);

	}

	@Test(priority = 9, description = "VPLX: Manage Transaction Priorities:[UI]- Check the order of Transaction priority on the basis of system generated and by default")
	public void Test09_1052927(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check the order of Transaction priority on the basis of system generated and by default");
		test.siteConfigurationAction.verifypriorityOrder("STAT Order");
		test.siteConfigurationAction.verifypriorityOrder(priorityName);
	}

	@Test(priority = 10, description = "VPLX: Manage Transaction Priorities:[UI] - The transaction types on the top has the highest priority.")
	public void Test10_1129456(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - The transaction types on the top has the highest priority.");

		top_priority=test.siteConfigurationAction.getTopPriority();
		pre_count = test.siteConfigurationAction.returnPositionOfPriorityName(top_priority);

		//test.siteConfigurationAction.dragAndDropTransactionPriorityatSystemLevel("STAT Order", "Manual STAT Order");

		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.PriorityReorder"));
		//post_count = test.siteConfigurationAction.returnPositionOfPriorityName("Manual STAT Order");
		Assert.assertEquals(pre_count, 0);
	}

}
