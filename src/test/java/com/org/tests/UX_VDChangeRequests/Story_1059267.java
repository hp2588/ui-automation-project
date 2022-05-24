package com.org.tests.UX_VDChangeRequests;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1059267 extends BaseTest {

	@Test(priority = 1, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Manual Pick.")
	public void Test1_1101731() throws InterruptedException {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
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
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifydefaultfocus();

	}

	@Test(priority = 2, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Manual Restock.")
	public void Test2_1101734() throws InterruptedException {
		test.supportDataActions.clickButton("cancel");
		//test.supportDataActions.clickButton("primary");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		//Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		test.transactionQueueActions.addRestock("Add Restock");
		test.transactionQueueActions.verifydefaultfocus();
	}

	@Test(priority = 3, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Manual Return.")
	public void Test3_1101737() throws InterruptedException {

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.verifydefaultfocus();
	}

	@Test(priority = 4, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Search Items.")
	public void Test4_1101744() throws InterruptedException {

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.landingPageActions.navigateToFeature("Item Management");
		test.transactionQueueActions.verifydefaultfocus();
		
	}

	@Test(priority = 5, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Add Items.")
	public void Test5_1101743() throws InterruptedException {

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		//test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.transactionQueueActions.verifydefaultfocus();

	}

	@Test(priority = 6, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Dashboard.")
	public void Test6_1101746() throws InterruptedException {
		
		test.landingPageActions.navigateToMenu("Main Menu");
        test.landingPageActions.navigateToFeature("Purchasing Dashboard");
        test.transactionQueueActions.verifydefaultfocus();
        
	}
	
	@Test(priority = 7, description = "VPLX: UX/VD Change Requests: [UI]: Focus is on first field on Cycle Count.")
	public void Test7_1101751() throws InterruptedException {
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
					
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Cycle Count");
		test.transactionQueueActions.verifydefaultfocus();

		
	}
		
	
}
