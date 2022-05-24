package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_123  extends BaseTest{
	
	int previous_count, after_count;
	ArrayList<String> previous_data, after_data;
	String priority, destination, firstname;


	@Test(priority = 0, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab")
	public void Test00_DeleteTransaction(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		//test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		//test.transactionQueueActions.clickDelete_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickDeleteButton_Sanity();
		
		test.transactionQueueActions.enterDeleteReason("deleteReason", "delete restock transaction");
		test.transactionQueueActions.clickConfirmToDeleteButton("Confirm");
		boolean status=test.transactionQueueActions.verifyRestockTransactionAfterRelease(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		if(status=true) {
			Assert.fail("[Assertion Failed]: Transaction is present in Retock tab after delete");
		}
		else{
			System.out.println("[Assertion Passed]: Transaction deleted succesfully");
		}
		
	}

}
