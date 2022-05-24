package com.org.tests.BDSanityFeatureChecklist;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class CCForce extends BaseTest{

	String facilityName,itemID,brandName,genericname,min,clickedDate,facilityOnWFAScreen,ISAName;

	@Test(priority=1, description= "VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level")
	public void Test01_1067791(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level");
		
		test.landingPageActions.navigateToMenu("Main Menu");

		test.landingPageActions.navigateToMenu("Transaction Queue");
	//	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
	}
	

	@Test(priority=5, description="VPLX:Forced Cycle Count:[UI]:User is able to hold the transaction.")
	public void Test05_1067792(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:User is able to hold the transaction.");
		
		test.transactionQueueActions.verifyActiveTransactionBoxSanity("Systemlevelfacility1603613130915");
		test.transactionQueueActions.clickHoldButton_Sanity();
		
   //     test.transactionQueueActions.clickHoldButtonForCycleCount(genericname[0]);
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	//	test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		
		
	}
	

	
	@Test(priority=6, description="VPLX:Forced Cycle Count:[UI]:User is able to release the transaction.")
	public void Test06_1065404(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:User is able to release the transaction.");
		
		test.transactionQueueActions.verifyActiveTransactionBoxSanity("Systemlevelfacility1603700595546");
	//	test.transactionQueueActions.selectRestockTransaction_Sanity(genericname[0]);
	//	test.transactionQueueActions.clickRelease_Sanity(genericname[0]);
		test.transactionQueueActions.releaseCycleCountTransaction();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTransaction("Systemlevelfacility1603700595546");
		
	}
}
