package com.org.tests.tqDelete;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982051 extends BaseTest {
	int previous_count, after_count;
	ArrayList<String> previous_data, after_data;
	String priority, destination, firstname;

	@Test(priority = 0, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab")
	public void Test00_996665(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab");
		test.landingPageActions.navigateToMenu("Transaction Queue");
	    test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		/*
		 * test.transactionQueueActions.verifyAndClickAddPick();
		 * test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"
		 * )); test.transactionQueueActions.verifySearchedResult("Item Name",getData(
		 * "AddPick.searchItemName"));
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
		 * getData("AddPick.Quantity")); destination=test.transactionQueueActions.
		 * selectDropdownForAddPick("Transaction Priority",getData("AddPick.Priority"));
		 * destination=test.transactionQueueActions.selectDropdownForAddPick(
		 * "Destination", getData("AddPick.Destination"));
		 * test.transactionQueueActions.clickAdditionalInfoToggle(); firstname="UI_"+
		 * test.transactionQueueActions.getAlphaNumericString(4);
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup(
		 * "patient_first_name", firstname);
		 * test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton(
		 * "save_close_btn", "Save & Close");
		 */
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		//previous_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		previous_count = test.transactionQueueActions.getTransactionTableDataCount();

		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
		//after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		after_count = test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertNotEquals(previous_count, after_count);
		

	}

	@Test(priority = 1, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab")
	public void Test01_1002443(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab");
		test.landingPageActions.navigateToMenu("Transaction Queue");
	    test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		/*
		 * test.transactionQueueActions.verifyAndClickAddPick();
		 * test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"
		 * )); test.transactionQueueActions.verifySearchedResult("Item Name",getData(
		 * "AddPick.searchItemName"));
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
		 * getData("AddPick.Quantity")); destination=test.transactionQueueActions.
		 * selectDropdownForAddPick("Transaction Priority",getData("AddPick.Priority"));
		 * destination=test.transactionQueueActions.selectDropdownForAddPick(
		 * "Destination", getData("AddPick.Destination"));
		 * test.transactionQueueActions.clickAdditionalInfoToggle(); firstname="UI_"+
		 * test.transactionQueueActions.getAlphaNumericString(4);
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup(
		 * "patient_first_name", firstname);
		 * test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton(
		 * "save_close_btn", "Save & Close");
		 */
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		//previous_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		previous_count = test.transactionQueueActions.getTransactionTableDataCount();

		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
		//after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		after_count = test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertNotEquals(previous_count, after_count);
		

	}

	@Test(priority = 4, description = "VPLX:Transaction queue actions-Delete selected:Deletion of single transaction from the Hold tab")
	public void Test02_998124(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:Deletion of single transaction from the Hold tab");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Hold");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
		

	}

	

	@Test(priority = 1, description = "VPLX:Transaction queue actions-Delete selected: Confirmation box is appearing when delete button is clicked")
	public void Test04_999481(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected: Confirmation box is appearing when delete button is clicked");
		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("cancel");
		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");

	}

	@Test(priority = 3, description = "VPLX:Transaction queue actions-Delete selected:Delete button is clicked without selecting any transaction")
	public void Test05_999482(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:Delete button is clicked without selecting any transaction");
		Assert.assertTrue(test.transactionQueueActions.verifyDeleteButtonIsDisabledWhenNoTransactionIsSelected());

	}

	@Test(priority = 2, description = "VPLX:Transaction queue actions-Delete selected: Proper Toast Message on UI is needed when a transaction is deleted successfully")
	public void Test06_1002438(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected: Proper Toast Message on UI is needed when a transaction is deleted successfully");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		Assert.assertTrue(test.transactionQueueActions.verifyFirstActionLinkAndClick("Delete"));
		Assert.assertTrue(test.transactionQueueActions.verifyDeleteConfirmationBox("confirm"));
		//test.transactionQueueActions.verifyToastMessageOnSuccess();
		test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader("Transaction has been deleted from Pick queue",60,500);
	}

	@Test(priority = 5, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Restock tab")
	public void Test07_998120(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Restock tab");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		previous_count = Integer.parseInt(test.transactionQueueActions.getTabCount(1).trim());
		previous_data = test.transactionQueueActions.getTransactionDetails(1);
		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");

	}

	@Test(priority = 6, description = "VPLX:Transaction queue actions-Delete selected:[UI]:User is able to delete the transaction.")
	public void Test08_1002443(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete selected:[UI]:User is able to delete the transaction.");
		test.landingPageActions.navigateToMenu("Transaction Queue");
	    test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		/*
		 * test.transactionQueueActions.verifyAndClickAddPick();
		 * test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"
		 * )); test.transactionQueueActions.verifySearchedResult("Item Name",getData(
		 * "AddPick.searchItemName"));
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
		 * getData("AddPick.Quantity")); destination=test.transactionQueueActions.
		 * selectDropdownForAddPick("Transaction Priority",getData("AddPick.Priority"));
		 * destination=test.transactionQueueActions.selectDropdownForAddPick(
		 * "Destination", getData("AddPick.Destination"));
		 * test.transactionQueueActions.clickAdditionalInfoToggle(); firstname="UI_"+
		 * test.transactionQueueActions.getAlphaNumericString(4);
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup(
		 * "patient_first_name", firstname);
		 * test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton(
		 * "save_close_btn", "Save & Close");
		 */
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		//previous_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		previous_count = test.transactionQueueActions.getTransactionTableDataCount();

		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
		//after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		after_count = test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertNotEquals(previous_count, after_count);
		

	}
	
	@Test(priority = 7, description = "VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab")
	public void Test09_1014029(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		test.transactionQueueActions.VerifyButtonsOnTQ("Delete");

	}
	
	/*@Test(priority = 8, description = "VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab")
	public void Test08_1014029(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		test.transactionQueueActions.VerifyButtonsOnTQ("Delete");

	}
	
	@Test(priority = 7, description = "VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab")
	public void Test09_1014029(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Delete Selected:[UI]:Delete option is visible on Restock tab");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		test.transactionQueueActions.VerifyButtonsOnTQ("Delete");

	}*/
	

}
