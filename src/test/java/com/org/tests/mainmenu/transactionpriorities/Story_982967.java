package com.org.tests.mainmenu.transactionpriorities;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982967 extends BaseTest {
	
	String priorityName,priorityNameUnsaved,code,color;
	
	
	@Test(priority = 1, description = "VPLX: Manage Transaction Priorities:[UI]-  Check that save and cancel button is appear in the each of each new row creation."
			+ ""
			+ "VPLX: Manage Transaction Priorities:[UI] - Check that Error message is display if code and enter name text box are blank and priority should not get added for user defined transaction priorities"
			+ ""
			+ "VPLX: Manage Transaction priorities:[UI] -   'Restore system sort' button at manage transaction priorities get removed"
			)
	public void Test01_Test02_Test03_1052969_1053965_1120776(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]-  Check that save and cancel button is appear in the each of each new row creation.");
		test.landingPageActions.navigateToFeature("Priorities");
		test.supportDataActions.verifyLabelIsPresent("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyPickRoutingRuleButtonDisabled("Restore system sort");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
		test.siteConfigurationAction.verifyLinkText("save");
		test.siteConfigurationAction.verifyLinkText("reset");
		
	}

	@Test(priority = 4, description = "VPLX: Manage Transaction Priorities:[UI]- Check that a new row is created  when  user click on Add button at manage transaction priorities page")
	public void Test04_1052996(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check that a new row is created  when  user click on Add button at manage transaction priorities page");
		test.siteConfigurationAction.verifyInputField("transactionPriorityName");
	}
	
	@Test(priority = 5, description = "VPLX: Manage Transaction Priorities:[UI] -  Check that Add button is disable after creating a new row for transaction priority and get enable once user save or reset the  transaction priority")
	public void Test05_1053468_part1(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check that a new row is created  when  user click on Add button at manage transaction priorities page");
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Enabled");
	}
	
	
	@Test(priority = 6, description = "VPLX: Manage Transaction Priorities:[UI]- Check that all the fields of transaction priority are reset when user click on reset button at manage transaction priority page")
	public void Test06_1053344(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Check that all the fields of transaction priority are reset when user click on reset button at manage transaction priority page");
		test.siteConfigurationAction.clickButton("reset");
		test.siteConfigurationAction.verifyRecordIsNotPresent("transactionPriorityName");
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");

	}
	
	@Test(priority = 15, description = "VPLX: Manage Transaction Priorities:[UI] - Check that transaction priority get save by click on Save button across transaction priority."
			+ ""
			+ "VPLX: Manage Transaction priorities:[UI] - Check that user can enter Maximum 50 Alphanumeric characters in 'Enter name' text box of new transaction priority"
					+ ""
					+ "VPLX: Manage Transaction priorities:[UI] -  Check that user can enter max 50 and 25 alphanumeric characters in name and code text box respectively."
					+ ""
					+ "VPLX: Manage Transaction priorities:[UI] - Check that user can insert special symbols and numeric value in Enter name text box of transaction priority."
					+ ""
					+ "VPLX: Manage Transaction Priorities:[UI] - Check that Max Locked Second by default is 600 and it not accept more than 4 digit"
					+ ""
					+ "VPLX: Manage Transaction Priorities:[UI] - User not redirected to blank page while adding new transaction priorities"
					+ ""
					+ "VPLX : Manage Transaction Priorities: [UI] : Secondary sort drop down is available for System and User Defined priority"
					+ ""
					+ "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The System Flag is unchecked by default and is non-editable when User adds a new Transaction Priority"
					+ ""
					+ "VPLX: Manage Transaction Priorities:[UI] :Default Max locked time for user defined transaction priorities is by default 600 sec."
					)
	public void Test07_Test08_Test09_Test10_Test11_Test12_Test13_Test14_Test15_1053348_1053516_1053970_1053542_1054012_1056183_1125516_1117197_1153856(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that transaction priority get save by click on Save button across transaction priority.");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		String name=test.siteConfigurationAction.enterDataInInputField("transactionPriorityName", "452335sdfgsdd#$%#$%$%$%$%#$%#$%#$%#$%#$%#$%#$%#$%#");
		int nameLen = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldOnTransactionPriorities("transactionPriorityName");
		Assert.assertEquals(nameLen, 50);
		test.siteConfigurationAction.clearInputBoxTransactionPriority("transactionPriorityName");
		
		String code=test.siteConfigurationAction.enterDataInInputField("transactionPriorityCode", "code834705893753583759348");
		int codeLen = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldOnTransactionPriorities("transactionPriorityCode");
		Assert.assertEquals(codeLen, 25);
		test.siteConfigurationAction.clearInputBoxTransactionPriority("transactionPriorityCode");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyDefaultValueMaxLock("maxLockedSeconds"));
		
		String maxLocked=test.siteConfigurationAction.enterDataInInputField("maxLockedSeconds", "1234");
		int maxLockedLen = test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldOnTransactionPriorities("maxLockedSeconds");
		Assert.assertEquals(maxLockedLen, 4);
		test.siteConfigurationAction.clearInputBoxTransactionPriority("maxLockedSeconds");
		
		test.siteConfigurationAction.enterDataInInputField("maxLockedSeconds", "600");
		
		//test.siteConfigurationAction.verifyValueInInputField("maxLockedSeconds", "600");
		priorityName=test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName", "Name@#" + System.currentTimeMillis());
		code=test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		//test.siteConfigurationAction.selectPriorityColor("#F44E3B");
		Assert.assertTrue(test.siteConfigurationAction.checkCheckboxOfSystemFlagNewRecordTP());
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.selectDropdownDispenseExternal("PatientName", "groupBy");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	
	}
	
	/* AUTOMATED */

	@Test(priority = 16, description = "VPLX: Manage Transaction Priorities:[UI]- Priority is not added for user defined priorities, if code and name are blank.")
	public void Test16_1129200(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]- Priority is not added for user defined priorities, if code and name are blank.");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		/*test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyErrorMessageUnderFields("Please enter a valid name", "1");
		test.siteConfigurationAction.verifyErrorMessageUnderFields("Please enter a valid code", "2");*/

	}
	
	@Test(priority = 17, description = "VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page")
	public void Test17_1129211(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that data will not save if user make the changes and  click on cancel button on Edit page");
		test.siteConfigurationAction.clickButton("reset");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityNameUnsaved = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("reset");
		Assert.assertFalse(test.siteConfigurationAction.verifyNewlyAddedPriorityInTheList(priorityNameUnsaved),
				"[Assertion Failed]: Priority is Added");

	}
	
	@Test(priority = 18, description = "VPLX: Manage Transaction Priorities:[UI] -  Check that Add button get enable once user save or reset the  transaction priority")
	public void Test05_1053468_part2(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] -  Check that Add button get enable once user save or reset the  transaction priority");
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("add"),
				"[Assertion Failed]: Add button is Disabled");
	}
	
	
	@Test(priority = 19, description = "VPLX: Manage Transaction Priorities:[UI] - Check that System check box is system generated and user not be able to edit this check box")
	public void Test19_1054020(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Check that System check box is system generated and user not be able to edit this check box");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		Assert.assertTrue(test.siteConfigurationAction.verifySystemCheckBoxIsEnabledOrDisabled(),"[ASSERTION FAILED]: System Checkbox is enabled.");
	}
	
	@Test(priority = 20, description = "VPLX: Manage Transaction priorities:[UI]- Check that user can select and deselect the check boxes at manage transaction priority page for newly created transaction priorities.")
	public void Test20_1054025(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI]- Check that user can select and deselect the check boxes at manage transaction priority page for newly created transaction priorities.");
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("useInterfaceMedNameFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("useInterfaceMedNameFlag"),
				"[Assertion Failed]: CheckBox Use Source Item Name is Unchecked");
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("useInterfaceMedNameFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("useInterfaceMedNameFlag"),
				"[Assertion Failed]: CheckBox Use Source Item Name is checked");
		
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
				"[Assertion Failed]: CheckBox Is ADC is Unchecked");
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
				"[Assertion Failed]: CheckBox Is ADC is checked");
		
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("forManualPickFlag"),
				"[Assertion Failed]: CheckBox Manual Pick is Unchecked");
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("forManualPickFlag"),
				"[Assertion Failed]: CheckBox Manual Pick is checked");

		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("forManualRestockFlag"),
				"[Assertion Failed]: CheckBox Manual Restock is Unchecked");
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("forManualRestockFlag"),
				"[Assertion Failed]: CheckBox Manual Restock is checked");

	}
	
	@Test(priority = 21, description = "VPLX: Manage Transaction priorities:[UI]- Check that newly created transaction priorities are placed at lowest priority")
	public void Test21_1054061(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI]- Check that newly created transaction priorities are placed at lowest priority");
		test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsPlacedAtTheBottom("transactionPriorityName");
		
	}	
	
	/* AUTOMATED */

	@Test(priority = 22, description = "VPLX: Manage Transaction Priorities:[UI] - User is unable to edit the attributes : Code, System, and Assigned Label for predefined transactions")
	public void Test22_1129199(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - User is unable to edit the attributes : Code, System, and Assigned Label for predefined transactions");
		test.siteConfigurationAction.clickButton("reset");	
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority("Manual STAT Order");
		Assert.assertFalse(
				test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("transactionPriorityName"));
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotEditable("transactionPriorityCode"));
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("systemFlag_1"));
	}
	
}
