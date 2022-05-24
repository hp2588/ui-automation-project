package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.actions.TransactionQueue_Actions;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class WastingAnItemFeature extends BaseTest {

	@Test(priority = 1, description = "VPLX:Wasting items in Transaction Queue:[UI]: "
			+ "Waste item button is present on dashboard for an active transaction")
	public void Test01_1066108(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Waste item button is present on dashboard for an active transaction");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
		test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
		test.transactionQueueActions.clickAdditionalInfoToggle();
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.VerifyButtons("Waste Item");
	}
	
	
	@Test(priority = 2, description = "VPLX:Wasting items in Transaction Queue:[UI]: "
			+ "List of defined waste reasons is displayed when user clicks on Waste Reason dropdown.")
	public void Test02_1066155(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"List of defined waste reasons is displayed when user clicks on Waste Reason dropdown.");
		
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
	}
	
	
	@Test(priority = 3, description = "VPLX:Wasting items in Transaction Queue:[UI]: "
			+ "Waste item screen is closed & user is on Transaction queue dashboard "
			+ "when user click on save button")
	public void Test03_1066159(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Waste item screen is closed & user is on Transaction queue dashboard " + 
				"when user click on save button");
		
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		test.transactionQueueActions.verifyWasteItemPopupGetsClosed();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}
	
	
	@Test(priority = 4, description = "VPLX: Wasting items in Transaction Queue [UI]: "
			+ "Quantity on hand is updated after wasting an item in the Transaction Queue dashboard "
			+ "during a pick transaction.")
	public void Test04_1066243(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Quantity on hand is updated after wasting an item in the Transaction Queue dashboard " 
				+ "during a pick transaction.");
		
		if (test.transactionQueueActions.verifyActiveTransactionBox()) {
			String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
			test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
			test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");

			test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
					getData("TQ.wasteQuatity"));
			test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
			test.transactionQueueActions.confirmWasteItem("Save");
			String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
			Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");
		}
	}
	
	
	@Test(priority = 11, description = "VPLX: Wasting items in Transaction Queue [UI]: Quantity on hand "
			+ "is updated after wasting an item in the Transaction Queue dashboard "
			+ "during a restock/instant restock transaction.")
	public void Test11_1070109(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Wasting items in Transaction Queue [UI]: Quantity on hand is updated "
				+ "after wasting an item in the Transaction Queue dashboard "
				+ "during a restock/instant restock transaction.");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddReturn.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddRestock.TransactionPriority"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");
	}
	
	
	@Test(priority = 12, description = "VPLX: Wasting items in Transaction Queue [UI]: Quantity on hand "
			+ "is updated after wasting an item in Transaction Queue dashboard during "
			+ "a return/instant return a transaction.")
	public void Test12_1070113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Wasting items in Transaction Queue [UI]: Quantity on hand is updated "
				+ "after wasting an item in Transaction Queue dashboard "
				+ "during a return/instant return a transaction.");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddReturn.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "RETURN");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");

	}
	
}
