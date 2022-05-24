package com.org.test.cyclecount;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class CycleCount_Test extends BaseTest {

	@Test(priority = 1, description = "VPLX: Transaction Queue-Cycle Count: [UI]: An option for creation of Cycle Count transaction is available under the Actions Tab .")
	public void Test1_1014675() {

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		/*
		 * test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"),
		 * 1); test.storageAreaAction.
		 * UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled
		 * ();
		 * test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"),
		 * 0);
		 */
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Cycle Count");
		// test.transactionQueueActions.searchItemValue(itemID);

	}

	@Test(priority = 2, description = "VPLX: Transaction Queue-Cycle Count: [UI]: User gets an option to scan an item for cyclecount.")
	public void Test2_1014692() {

		test.supportDataActions.verifyLabelIsPresent("Cycle Count");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyOptionToScanitem("scanImage", "Scan the Bin Label to continue..."),
				"[SSERTION FAILED]: Scan option is not present");
	}

}
