package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_PO_Pkg_Shr_Data extends BaseTest {
	
	String medItem, firstname, destination, priority;
	
	@Test(priority = 1, description = "VPLX:Package Sharing:[UI]:New order is created from Receiving Facility at Purchase Dashboard Screen")
	public void Test01_1114617_1116280_1152067_1152070_1152065_1152074_1152077_1152078() {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		medItem=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.packageSizeOnIS();
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(),true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		//test.supportDataActions.packageSizeOnPOCard();
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickReceiveCard(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")));
		//test.supportDataActions.packageSizeOnRPO();	
	}

	@Test(priority = 2, description = "Create and View Pick Transaction",testName="Create and View Pick Transaction")
	public void Test02_ProvidingTQ_1115107_1116279_1153574_1153575(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress1").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName1"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName1"));
		Assert.assertTrue(test.storageAreaAction.verifyPackageShareTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName1")));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName1"), 0);
		Assert.assertTrue(test.storageAreaAction.verifyPackageShareTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName1")));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.getQuantityOnActiveBox();	
	}
	
	@Test(priority = 3, description = "Create and View Pick Transaction",testName="Create and View Pick Transaction")
	public void Test03_ReceivingTQ_1124854(Method method) {
		Open_Browser_Window();
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress2").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName2"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		Assert.assertTrue(test.storageAreaAction.verifyReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName2")));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName2"), 0);
		Assert.assertTrue(test.storageAreaAction.verifyReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName2")));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.getQuantityOnActiveBox();
	}

}
