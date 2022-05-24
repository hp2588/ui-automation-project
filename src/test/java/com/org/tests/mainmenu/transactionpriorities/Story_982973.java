package com.org.tests.mainmenu.transactionpriorities;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982973 extends BaseTest {
	String priorityName, code, color;
	int maxHold;
	

	@Test(priority = 1, description = "VPLX: Manage Transaction Priorities:[UI] - Check that user can  Edit the transaction priority by click on Edit button.")
	public void Test01_Test02_Test03_1053367_1054001_1132189(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that user can  Edit the transaction priority by click on Edit button.");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.editLinkTP("edit");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		//test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		maxHold = test.supportDataActions.verifyMaxLengthOfAnSearchField("maxHoldMinutes");
		Assert.assertEquals(maxHold, 4);
		Assert.assertTrue(test.siteConfigurationAction.verifyMaxHoldMins());
		test.siteConfigurationAction.enterMaxHoldMins(DateUtil.generateRandomDigits(5), 4);
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	}
	
	@Test(priority = 2, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The System Flag is unchecked by default for User Defined priorities")
	public void Test04_1117194() {
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		//Assert.assertFalse(test.siteConfigurationAction.checkSystemFlagIsChecked("systemFlag_0"));	
		Assert.assertFalse(test.siteConfigurationAction.verifySystemCheckBoxIsChecked(),"[ASSERTION FAILED]: System Checkbox is checked by default.");

		
	}
	

	@Test(priority = 3, description = "VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page")
	public void Test05_1054850(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page");
		//test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		String preValue = test.siteConfigurationAction.getMaxHoldMinsData(priorityName);
		test.siteConfigurationAction.clickButton("reset");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		String postValue = test.siteConfigurationAction.getMaxHoldMinsData(priorityName);
		Assert.assertEquals(preValue, postValue);
		test.siteConfigurationAction.clickButton("reset");
	}
	
	@Test(priority = 4, description = "VPLX: Manage Transaction Priorities:[UI] - Check that a new transaction priority is not created by click on Edit button corresponding to transaction priority")
	public void Test06_1053369(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that a new transaction priority is not created by click on Edit button corresponding to transaction priority");
		int pre = test.siteConfigurationAction.checkCountOfPriorityName(priorityName);
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		test.siteConfigurationAction.enterMaxHoldMins(DateUtil.generateRandomDigits(5), 4);
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		int post = test.siteConfigurationAction.checkCountOfPriorityName(priorityName);
		Assert.assertEquals(pre, post);
	}
	
	@Test(priority = 5, description = "VPLX: Manage Transaction Priorities:[UI] - Check that User is not able to edit an existing user generated priority with duplicate code")
	public void Test07_1132195(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that User is not able to edit an existing user generated priority with duplicate code");	
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		/*priorityName =*/ test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				code);
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.siteConfigurationAction.verifyErrorMessageRoutingRule(
				"This Transaction Priority Code already exists. Please provide a unique Transaction Priority Code .");
	}
	
	@Test(priority = 6, description = "VPLX: Manage Transaction Priorities:[UI] - Check that User is not able to edit code for system generated priorities")
	public void Test08_1132193(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that User is not able to edit code for system generated priorities");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.codeNotEditable("transactionPriorityCode");
		Assert.assertTrue(test.siteConfigurationAction.checkSystemFlagIsChecked("systemFlag_0"));
		test.siteConfigurationAction.clickButton("reset");
			}
	
	
	
}	
	
	
	
	
