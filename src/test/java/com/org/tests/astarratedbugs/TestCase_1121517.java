package com.org.tests.astarratedbugs;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class TestCase_1121517 extends BaseTest {
	
	String firstname, destination, priority;

	@Test(priority = 1, description = "Create and View Pick Transaction",testName="Create and View Pick Transaction")
	public void Test01_ProvidingTQ(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress3").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName2"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName1"));
		Assert.assertTrue(test.storageAreaAction.verifyPackageShareTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortISAName2")));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName2"), 0);
		Assert.assertTrue(test.storageAreaAction.verifyPackageShareTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortISAName2")));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.getQuantityOnActiveBox();	
	}
	
	@Test(priority = 2, description = "Create and View Pick Transaction",testName="Create and View Pick Transaction")
	public void Test02_ReceivingTQ(Method method) {
		Open_Browser_Window();
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress4").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName3"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		Assert.assertTrue(test.storageAreaAction.verifyReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortISAName3")));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName3"), 0);
		Assert.assertTrue(test.storageAreaAction.verifyReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortISAName3")));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.getQuantityOnActiveBox();				
	}

}
