package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018886 extends BaseTest {
	String priority, location;

	/*
	 * @Override
	 * 
	 * @Test(priority = 0, description = "Login.") public void Open_Browser_Window()
	 * {
	 * 
	 * test = new TestSessionInitiator(this.getClass().getSimpleName()); String
	 * app_url = getYamlValue("app_url"); test.launchApplication(app_url); //
	 * test.loginPageAction.verifyUserIsOnBDLoginPage();
	 * test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameTenant1").
	 * trim(), getData("Auth.passwordTenant1").trim(), getData("Auth.ip3").trim());
	 * test.landingPageActions.navigateToMenu("Main Menu");
	 * Assert.assertTrue(test.landingPageActions.
	 * verifyUserIsOnLandingPage("Key Destinations"),
	 * "[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	 * 
	 * }
	 * 
	 */
	@Test(priority = 1, description = "VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button")
	public void Test01_1032983(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		// test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA3"), 2);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");

	}

	@Test(priority = 2, description = "VPLX:Manual Return: UI:Verify User navigates to add return page after clicking add return button")
	public void Test02_1032984(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify User navigates to add return page after clicking add return button");
		test.transactionQueueActions.verifyAddPickPopup(getData("AddReturn.ExpectedMessage"));
	}

	@Test(priority = 3, description = "VPLX:Manual Return: UI:Verify search text box to search medication by entering text")
	public void Test03_1032988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify search text box to search medication by entering text");
		test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddReturn.searchItemName1"));
	}

	@Test(priority = 4, description = "VPLX:Manual Return: UI:Verify quantity and priority as mandatory field to enter after selecting item")
	public void Test04_1033015(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX:Manual Return: UI:Verify quantity and priority as mandatory field to enter after selecting item");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.verifyFieldIsMandatory("Quantity");
		test.transactionQueueActions.verifyFieldIsMandatory("Transaction Priority");
	}

	@Test(priority = 5, description = "VPLX:Manual Return: UI:Verify full transaction of manual return and added in restock queue")
	public void Test05_1033397(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify full transaction of manual return and added in restock queue");
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));

		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddReturn.TransactionPriority"));
		location = test.transactionQueueActions.selectDropdownForAddPick("Source Location",
				getData("AddReturn.Source Location"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");

		test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),
				getData("AddPick.Quantity"));

	}

	@Test(priority = 6, description = "VPLX:Manual Return: UI:Verify user successfully cancel the selected item")
	public void Test06_1033040(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify user successfully cancel the selected item");
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");

		test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddReturn.searchItemName1"));

		test.transactionQueueActions.clickSearchedItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddReturn.TransactionPriority"));
		location = test.transactionQueueActions.selectDropdownForAddPick("Source Location",
				getData("AddReturn.Source Location"));
		test.transactionQueueActions.verifyButtonAddPick("cancel_btn", "Cancel");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
		test.transactionQueueActions.verifyCancelPopupOnAddReturn(getData("AddPick.ConfirmMessage"));
		// test.transactionQueueActions.clickConfirmPopupButton();
	}

	// Bug#1110687 logged since Item is not getting inactive,which is pre-requisite
	// for this Test Case
	@Test(priority = 7, description = "VPLX:Manual Return: UI:Verify all active as well as inactive items displayed when show all items switch turn on")
	public void Test07_1033023(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify all active as well as inactive items displayed when show all items switch turn on");
		test.transactionQueueActions.verifyAndClickShowAllItems();
		test.transactionQueueActions.searchItemValue(getData("AddReturn.searchInactiveItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddReturn.searchInactiveItemName"));
	}

}
