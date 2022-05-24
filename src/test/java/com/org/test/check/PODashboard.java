	package com.org.test.check;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class PODashboard extends BaseTest {

	String medItem;
	
	@Test(priority = 1, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given")
	public void Test01_1051207() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue("Fac1586015444596");
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", "a");
		test.supportDataActions.verifyPOItemSearchResult("a");
		medItem=test.supportDataActions.clickSearchedPOItem("0","ItemName1586021752673");
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(),true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard("dis1586017579611");
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice", test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve("ItemName1586021752673");
		test.supportDataActions.enterItemCostForInvoice("cost","10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve("ItemName1586021752673");
		test.siteConfigurationAction.clickButton("ReceivedandSent");
	}
	
}
