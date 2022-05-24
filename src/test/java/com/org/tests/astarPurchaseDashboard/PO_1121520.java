package com.org.tests.astarPurchaseDashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class PO_1121520 extends BaseTest{

	String medItem;
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	String priority, location;
	
	@Test(priority = 1, description = "VPLX : System creates an updated pending order when changing and processing receiving quantity in transaction queue.")
	public void Test01_1121520() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(),true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice", test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost","10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");
		
		/* TQ restock transaction check */		
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		//test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),getData("AddPick.Quantity"));
		//test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));

		test.transactionQueueActions.selectRestockTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.restockNowReceivingTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		int quantity= test.transactionQueueActions.getActiveRestockTransactionQuantity("update-quantity");
		System.out.println("active txn quantity: " + quantity);
		int updatedQuantity=test.transactionQueueActions.updateRestockTransactionQuantity("update-quantity","20");
		System.out.println("active txn updated quantity: " + updatedQuantity);
		test.transactionQueueActions.clickOnQueueText();
		test.transactionQueueActions.clickScanOverride();
		int pendingQuantity=test.transactionQueueActions.getQuantityPendingRestockTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		System.out.println("pending txn updated quantity: " + pendingQuantity);
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQuantity(quantity,updatedQuantity,pendingQuantity),"[Assert Failed] : Difference in restock transaction quantity");
		
	}
	
}
