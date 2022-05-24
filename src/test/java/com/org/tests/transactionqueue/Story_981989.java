package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981989 extends BaseTest {
	ArrayList<String> restocktransdetail = new ArrayList<String>();

	@Override
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

	@Test(priority = 1, description = "VPLX:Transaction Queue -Return: [UI]: Details of messages displayed after Active return transaction process")
	public void Test01_1013783(Method method)

	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Return: [UI]: Details of messages displayed after Active return transaction process");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip3").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyActiveRestockMessage(getData("AddReturn.ActiveBarMessage"));

	}

	@Test(priority = 2, description = "VPLX:Transaction Queue -Return: [UI]: Verify By default quantity for Return transactions")
	public void Test02_1024290(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Return: [UI]: Verify By default quantity for Return transactions");
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.clickSearchedItemValue(getData("AddReturn.searchItemName1"));

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));

		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddReturn.TransactionPriority"));
		test.transactionQueueActions.selectDropdownForAddPick("Source Location", getData("AddReturn.Source Location"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");

		test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),
				getData("AddPick.Quantity"));
		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();

		test.transactionQueueActions.selectRestockItem();
		test.transactionQueueActions.verifyActiveRestockTransaction();

	}

	@Test(priority = 3, description = "VPLX:Transaction Queue -Return: [UI]: Able to process transaction under or equal to on hand quantity")
	public void Test03_1024327(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Return: [UI]: Able to process transaction under or equal to on hand quantity");
		test.transactionQueueActions.updateOnHandQuantity();
	}

	@Test(priority = 4, description = "VPLX:Transaction Queue -Return: [UI]: Process transaction by providing credentials")
	public void Test04_1024299(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Return: [UI]: Process transaction by providing credentials");
		Assert.assertTrue(test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		test.transactionQueueActions.clickBinScanOverride();

	}
}
