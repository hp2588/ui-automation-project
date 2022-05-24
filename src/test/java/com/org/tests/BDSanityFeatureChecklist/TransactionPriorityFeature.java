package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class TransactionPriorityFeature extends BaseTest {

	ArrayList<String> priorities = new ArrayList<String>(Arrays.asList("STAT Order", "Multi-Part STAT",
			"Manual STAT Order", "STAT Redispense", "Multi-Part STAT Redispense", "Pyxis Stock Out",
			"Pyxis Critical Low", "New Order", "Multi-Part Order", "Manual New Order", "Cart Fill"));

	String priorityName, priorityName2,priorityNameUnsaved, code, code2, app_url;
	int pre_count, post_count;

	/* =========================AUTOMATED============================= */

	@Test(priority = 1, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test1_1129207(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");

		test.landingPageActions.navigateToFeature("Priorities");
		test.supportDataActions.verifyLabelIsPresent("Priorities");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 0);
		test.siteConfigurationAction.verifyTransactionPrioritiesAreDisplayed();
	}

	/* =========================AUTOMATED============================= */

	@Test(priority = 2, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test2_1129209(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");
		Assert.assertTrue(test.siteConfigurationAction.verifyPriorities(priorities));

	}

	/* =========================AUTOMATED============================= */

	@Test(priority = 3, description = "VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.")
	public void Test3_1129208(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- All the added facilities starts with the predefined system proprieties.");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifyPriorities(priorities));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 0);

	}

	@Test(priority = 4, description = "VPLX: Manage Transaction Priorities:[UI]-  Check that save and cancel button is appear in the each of each new row creation.")
	public void Test04_1052969(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]-  Check that save and cancel button is appear in the each of each new row creation.");

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyLinkText("save");
		test.siteConfigurationAction.verifyLinkText("reset");

	}

	/* AUTOMATED */

	@Test(priority = 5, description = "VPLX: Manage Transaction priorities:[UI]- Check that newly created transaction priorities are placed at lowest priority")
	public void Test05_1054061(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI]- Check that newly created transaction priorities are placed at lowest priority");

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPriorityInList(priorityName);

		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		test.siteConfigurationAction
				.verifyCheckboxOfNewlyCreatedTransactionPriorityIsPlacedAtTheBottom("transactionPriorityName");
		test.siteConfigurationAction.clickButton("reset");

	}

	@Test(priority = 6, description = "VPLX: Manage Transaction Priorities:[UI] - Check that user can change the order of transaction priority by grab the desired row for the priority and move it up or down")
	public void Test06_1053375_1129206(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that user can change the order of transaction priority by grab the desired row for the priority and move it up or down");

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
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPriorityInList(priorityName2);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");
		pre_count = test.siteConfigurationAction.returnPositionOfPriorityName(priorityName2);

		test.siteConfigurationAction.dragAndDropTransactionPriority(priorityName2, priorityName);

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.PriorityReorder"));
	}

	@Test(priority = 7, description = "VPLX: Manage Transaction Priorities:[UI] -  Check the order of transaction priorities , the list is displayed based on the last display order of the rows.")
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
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Priorities");

		test.supportDataActions.verifyLabelIsPresent("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.clickButton("reset");
		post_count = test.siteConfigurationAction.returnPositionOfPriorityName(priorityName2);
		Assert.assertEquals(pre_count - 1, post_count);

	}

	/* =======================AUTOMATED========================== */

	@Test(priority = 8, description = "VPLX: Manage Transaction Priorities:[UI] - The transaction types on the top has the highest priority.")
	public void Test09_1129456(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - The transaction types on the top has the highest priority.");

		pre_count = test.siteConfigurationAction.checkCountOfPriorityName("Multi-Part STAT");

		test.siteConfigurationAction.dragAndDropTransactionPriority("Multi-Part STAT", "STAT Order");

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.PriorityReorder"));
		post_count = test.siteConfigurationAction.checkCountOfPriorityName("Multi-Part STAT");
		Assert.assertEquals(pre_count, post_count);
	}

	@Test(priority = 9, description = "VPLX: Manage Transaction Priorities:[UI]- Check that a new row is created  when  user click on Add button at manage transaction priorities page")
	public void Test10_1052996(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check that a new row is created  when  user click on Add button at manage transaction priorities page");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyInputField("transactionPriorityName");
	}

	@Test(priority = 10, description = "VPLX: Manage Transaction Priorities:[UI] - Check that transaction priority get save by click on Save button across transaction priority.")
	public void Test11_1053348_1060294(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that transaction priority get save by click on Save button across transaction priority.");

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

	}

	/* AUTOMATED */

	@Test(priority = 11, description = "VPLX: Manage Transaction Priorities:[UI]- Check the order of Transaction priority on the basis of system generated and by default")
	public void Test12_1052927(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check the order of Transaction priority on the basis of system generated and by default");
		test.siteConfigurationAction.verifypriorityOrder("STAT Order");
		test.siteConfigurationAction.verifypriorityOrder(priorityName);
	}

	@Test(priority = 12, description = "VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page")
	public void Test13_1129211(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page");

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityNameUnsaved = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("reset");
		Assert.assertFalse(test.siteConfigurationAction.verifyNewlyAddedPriorityInTheList(priorityNameUnsaved),
				"[Assertion Failed]: Priority is Added");

	}

	@Test(priority = 13, description = "VPLX: Manage Transaction Priorities:[UI] -  Check the functionality of 'Show Inactive' toggle button., when toggle button is active it display Active priorities and when it is inactive  it display  active and inactive ")
	public void Test14_1054082(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] -  Check the functionality of 'Show Inactive' toggle button., when toggle button is active it display Active priorities and when it is inactive  it display  active and inactive ");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.scrollHorizontal();

		test.siteConfigurationAction.selectValueFromDropDown("groupBy", "Destination");
		test.siteConfigurationAction.selectValueFromDropDown("groupBy", "PatientName");
		test.siteConfigurationAction.selectValueFromDropDown("groupBy", "Location");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");

		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		Assert.assertTrue(test.siteConfigurationAction.verifySystemCheckBoxIsEnabledOrDisabled(),
				"[ASSERTION FAILED]: System Checkbox is enabled.");

		Assert.assertTrue(
				test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("activeFlag"),
				"[Assertion Failed]: Active CheckBox of Transaction Priorities is Unchecked");

		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));

		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		Assert.assertFalse(test.siteConfigurationAction.verifyInactiveTransactionPriorities("activeFlag"),
				"[Assertion Failed]: CheckBox Is of Newly created Inactive is checked");
	}

	/* AUTOMATED */

	@Test(priority = 14, description = "VPLX: Manage Transaction Priorities:[UI]- Priority is not added for user defined priorities, if code and name are blank.")
	public void Test15_1129200(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Priority is not added for user defined priorities, if code and name are blank.");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyErrorMessageUnderFields("Please enter a valid name", "1");
		test.siteConfigurationAction.verifyErrorMessageUnderFields("Please enter a valid code", "2");

	}

	@Test(priority = 15, description = "VPLX: Manage Transaction Priorities:[UI] - Check that user can  Edit the transaction priority by click on Edit button.")
	public void Test16_1053367(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that user can  Edit the transaction priority by click on Edit button.");
		test.siteConfigurationAction.clickButton("reset");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		 test.siteConfigurationAction.verifyAndClickInactiveToggle();
		/*test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");*/
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		test.siteConfigurationAction.enterMaxHoldMins(DateUtil.generateRandomDigits(5), 4);
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	}

	@Test(priority = 16, description = "VPLX: Manage Transaction Priorities:[UI] - Check that System check box is system generated and user not be able to edit this check box")
	public void Test17_1054020(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that System check box is system generated and user not be able to edit this check box");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		Assert.assertTrue(test.siteConfigurationAction.verifySystemCheckBoxIsEnabledOrDisabled(),
				"[ASSERTION FAILED]: System Checkbox is enabled.");
	}

	/* AUTOMATED */

	@Test(priority = 17, description = "VPLX: Manage Transaction Priorities:[UI] - User is unable to edit the attributes : Code, System, and Assigned Label for predefined transactions")
	public void Test18_1129199(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - User is unable to edit the attributes : Code, System, and Assigned Label for predefined transactions");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority("Manual STAT Order");
		
		Assert.assertFalse(
				test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("transactionPriorityName"));
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotEditable("transactionPriorityCode"));
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("systemFlag_1"));
	}

}
