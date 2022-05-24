package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981943 extends BaseTest {

	@Test(priority = 00, description = "VPLX:Transaction Queue-Pick-UI: Quantity change button is enabled when Pick Now is selected")
	public void Test00_1009372(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Quantity change button is enabled when Pick Now is selected");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertTrue(test.transactionQueueActions.verifyChangeQuantityButtonisEnabled());
	}

	@Test(priority = 01, description = "VPLX:Transaction Queue-Pick-UI: Quantity Change pop-up")
	public void Test01_1012162(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Quantity Change pop-up");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyChangeQuantityButtonisEnabled());
		Assert.assertTrue(test.transactionQueueActions.verifyChangeQuantityPopup());
	}

	@Test(priority = 02, description = "VPLX:Transaction Queue-Pick-UI: Value change of Quantity Processed")
	public void Test02_1012163(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Value change of Quantity Processed");
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyChangeQuantityButtonisEnabled());
		Assert.assertTrue(test.transactionQueueActions.verifyValueOfQuantityChangeOnReset(getData("TQ.inputQuantity")));
		Assert.assertTrue(test.transactionQueueActions.verifyValueOfQuantityChangeOnSave(getData("TQ.inputQuantity")));
	}

	@Test(priority = 03, description = "VPLX:Transaction Queue-Pick-UI: Cross button on Change Quantity Pop-Up")
	public void Test03_1012164(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Cross button on Change Quantity Pop-Up");
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyChangeQuantityButtonisEnabled());
		Assert.assertTrue(
				test.transactionQueueActions.verifyChangeQuantityPopupCrossButton(getData("TQ.inputQuantity")));

	}

	@Test(priority = 04, description = "VPLX:Transaction Queue-Pick-UI:Cancel button on Change Quantity Pop-Up")
	public void Test04_1012169(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI:Cancel button on Change Quantity Pop-Up");
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyChangeQuantityButtonisEnabled());
	}

	@Test(priority = 05, description = "VPLX:Transaction Queue-Pick-UI: Screen is showing scroll bars when screen size is reduced to below the minimum screen size")
	public void Test05_1048775(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Screen is showing scroll bars when screen size is reduced to below the minimum screen size");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyScrollBarIsPresentWhenScreenSizeIsMinimized());
	}

}
