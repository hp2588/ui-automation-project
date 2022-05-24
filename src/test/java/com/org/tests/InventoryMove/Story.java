package com.org.tests.InventoryMove;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story extends BaseTest{

	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -When Adjusted QOH falls below Minimum Qty after Transaction completion, Inventory Move Transaction for another ISA (Chosen as Replenish source)is created in Pick Tab")
	public void Test01_1154174(Method method) {
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
		.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
    Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
		"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
    	test.storageAreaAction.verifyStartWorkButtonAndClick();
    	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
    	test.transactionQueueActions.verifyInventoryMovePickTransaction();
		
	
	}

	
	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - The Destination of Inventory Move transaction in Pick tab is the location of the item assigned to ISA which needs to be replenished")
	public void Test02_1154177(Method method) {
	
    	test.transactionQueueActions.verifyInvMovePickDestination(TestDataPropertyReaderAndWriter.getProperty("InvMoveISA2").trim());
		
	}
	
	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -When Adjusted QOH falls below Minimum Qty after Transaction completion, Inventory Move Transaction for the same ISA is created in Restock Tab")
	public void Test03_1154176(Method method) {
	
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.selectRestockTransaction_Sanity("Inventory Move");
		test.transactionQueueActions.clickRestockButton_SanityNew();

	}
}


