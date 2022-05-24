package com.org.tests.astarPurchaseDashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class Story_124 extends BaseTest {

	String medItem;

	@Test(priority = 1, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given"
			+ "VPLX : System creates an updated pending order when changing and processing receiving quantity in transaction queue.,"
			+ "VPLX : Pending invoices should not be displayed in all facility invoices.")
	public void Test01_Create_Order_and_Export() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		//test.supportDataActions.openPurchaseOrderManualcard();
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber","PO"+System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}
	
	
		//1121500
		@Test(priority = 2, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given"
				+ "VPLX : System creates an updated pending order when changing and processing receiving quantity in transaction queue.,"
				+ "VPLX : Pending invoices should not be displayed in all facility invoices.")
		public void Test01_1121500() {
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName1"));
		test.purchaseDashboardActions.verifyPendingReceiveCardIsNotPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		}
		
		
		//1121521
		@Test(priority = 3, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given"
				+ "VPLX : System creates an updated pending order when changing and processing receiving quantity in transaction queue.,"
				+ "VPLX : Pending invoices should not be displayed in all facility invoices.")
		public void Test01_1121520() {
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		//test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");
		
		// Go to TQ WFA screen and check Receiving transaction is created
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.storageAreaAction.verifyTransactionCountForReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		//test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),getData("AddPick.Quantity"));
		//test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));

		
		/*
		 * test.transactionQueueActions.selectRestockTransaction(
		 * TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		 * test.transactionQueueActions.restockNowReceivingTransaction(
		 * TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		 */
		
		test.transactionQueueActions.selectRestockTransaction_Sanity("Receiving".trim());
		//test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickRestockButton_SanityNew();
		
		 int quantity= test.transactionQueueActions.getActiveRestockTransactionQuantity( "update-quantity"); 
		 
		
		 
		 System.out.println("active txn quantity: " + quantity);
		 /* int
		 * updatedQuantity=test.transactionQueueActions.updateRestockTransactionQuantity
		 * ("update-quantity","20"); System.out.println("active txn updated quantity: "
		 * + updatedQuantity); test.transactionQueueActions.clickOnQueueText();
		 */
		int updatedQuantity=3;
		test.transactionQueueActions.clickScanOverrideOnce();
		test.transactionQueueActions.clickOnQuantity("3");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce());
		int pendingQuantity=test.transactionQueueActions.getQuantityPendingRestockTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		System.out.println("pending txn updated quantity: " + pendingQuantity);
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQuantity(quantity,updatedQuantity,pendingQuantity),"[Assert Failed] : Difference in restock transaction quantity");
		}
}
