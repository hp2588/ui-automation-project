package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981947 extends BaseTest {

	String[] listPopupItems = { "Item ID", "Item Description" };
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };

	 ArrayList<String> restocktransdetail = new ArrayList<String>();

	/*@Override
	@Test(priority = 0, description = "Login.")
	public void Open_Browser_Window() {

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameTenant1").trim(),
				getData("Auth.passwordTenant1").trim(), getData("Auth.ip3").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	}
*/
	@Test(priority = 01, description = "VPLX:Transaction Queue -Restock: Manual Override of restock transaction")
	public void Test00_1013774(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue -Restock: Manual Override of restock transaction");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");

		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		test.transactionQueueActions.selectRestockItem();

		// Assert.assertTrue(test.transactionQueueActions.restockFirstTransaction());
		Assert.assertTrue(test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		// Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning());
		Assert.assertTrue(test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
	}

	@Test(priority = 02, description = "VPLX:Transaction Queue -Restock: User Credentials of Scan Override pop-up")
	public void Test01_1013775(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Quantity Change pop-up");
		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		test.transactionQueueActions.selectRestockItem();
		Assert.assertTrue(test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());

	}

}
