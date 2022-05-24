package com.org.tests.mainmenu.transactionpriorities;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982972 extends BaseTest {

	String priorityName, code;
	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] -  Check that user can view and select any one option from  destination , Label and patient name , when click on secondary sort drop down button")
	public void Test01_1054605(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Check that user can view and select any one option from  destination , Label and patient name , when click on secondary sort drop down button");
		test.landingPageActions.navigateToFeature("Priorities");

		test.supportDataActions.verifyLabelIsPresent("Priorities");
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
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");
	}
	
	@Test(priority = 2, description = "VPLX: Manage Transaction Priorities:[UI] -  Check the functionality of 'Show Inactive' toggle button., when toggle button is active it display Active priorities and when it is inactive  it display  active and inactive ")
	public void Test02_1054082(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] -  Check the functionality of 'Show Inactive' toggle button., when toggle button is active it display Active priorities and when it is inactive  it display  active and inactive ");
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
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		Assert.assertFalse(test.siteConfigurationAction.verifyInactiveTransactionPriorities("activeFlag"),
				"[Assertion Failed]: CheckBox Is of Newly created Inactive is checked");
	}

}
