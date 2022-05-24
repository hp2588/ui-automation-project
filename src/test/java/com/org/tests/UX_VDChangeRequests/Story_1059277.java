package com.org.tests.UX_VDChangeRequests;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1059277 extends BaseTest {
	
	String itemName,itemName1;

	@Test(priority = 1, description = "VPLX:UX/VD Change Requests :[UI]:Change priority dropdown label for add restock.")
	public void Test1_1080272() throws InterruptedException {
		
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
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		//Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		test.transactionQueueActions.addRestock("Add Restock");
		
		test.transactionQueueActions.searchItemValue("itemID");
		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		 
		 itemName1 = itemName.split(" ")[0];
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
		Assert.assertEquals(test.transactionQueueActions.verifydropdownName("priority"),"Transaction Priority");
	}
	
	
}
