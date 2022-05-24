package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class Story_124 extends BaseTest {

	String medItem;

	@Test(priority = 1, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given")
	public void Test01_1051207() {

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
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");

		// Go to TQ restock tab and check Receiving transaction
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.storageAreaAction.verifyTransactionCountForReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ISAName")));
		/*
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		 * test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		 */
		// test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),getData("AddPick.Quantity"));
		// test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));

		/*
		 * test.transactionQueueActions.selectRestockTransaction(
		 * TestDataPropertyReaderAndWriter.getProperty("ItemName")); boolean
		 * status=test.transactionQueueActions.verifyReceivingTransactionAfterRelease(
		 * TestDataPropertyReaderAndWriter.getProperty("ItemName")); Assert.assertTrue(
		 * status,"[Assertion Failed]: Receiving Transaction is not present in Restock tab"
		 * );
		 */
		// test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		// test.supportDataActions.enterItemCostForInvoice("cost","10");
		// test.supportDataActions.savePONumber("PendingReceive");
		// test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		// test.siteConfigurationAction.clickButton("ReceivedandSent");
	}

}
