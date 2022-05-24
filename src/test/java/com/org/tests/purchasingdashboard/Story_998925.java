package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_998925 extends BaseTest{

	String po_Description;
	
	@Test(priority = 1, description = "VPLX: Void New Orders- [UI] - Void all the orders by clicking Void All link in the PO for a vendor")
	public void Test01_1056049() {
	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickOnVoidAll("Void All");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
	}
	
	@Test(priority = 2, description = "VPLX: Void New Orders : [UI] Void one of the item in an PO for a vendor having in new status")
	public void Test02_1056623() {
	
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickOnVoidButton("1");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		
	}
	
	@Test(priority = 3, description = "VPLX: Void New Orders : [UI] Void a single order and Confirm popup message on void order")
	public void Test03_1056624() {
	
		
		po_Description=test.purchaseDashboardActions.getPODescription("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		Assert.assertTrue(test.purchaseDashboardActions.getConfirmVoidPopupText(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(),po_Description));
		
		
	}
	
	@Test(priority = 4, description = "VPLX: Void New Orders : [UI] Void order conformation by Successful message after voiding order")
	public void Test04_1056625() {
	
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		String successmessage="The PO "+po_Description+" for the distributor "+TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()+" successfully voided.";
		//System.out.println(successmessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successmessage);
		
		
	}
	
	@Test(priority = 5, description = "VPLX: Void New Orders : [UI] Item gets removed from new card after clicking on void button")
	public void Test05_1056626() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		po_Description=test.purchaseDashboardActions.getPODescription("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		String successmessage="The PO "+po_Description+" for the distributor "+TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()+" successfully voided.";
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		
		//System.out.println(successmessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successmessage);
		
	}
	
	@Test(priority = 6, description = "VPLX: Void New Orders : [UI] Order is not able to recreated after pressing refresh button with today's date")
	public void Test06_1056627() {
	
		test.purchaseDashboardActions.clickRefreshOrderDetailsPage("#");
		test.purchaseDashboardActions.itemIsNotVisible(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	}
	
	@Test(priority = 7, description = "VPLX: Void New Orders:[UI]:User verifies the messages on the voiding the order.",enabled=false)
	public void Test07_1066133() {
	
		test.purchaseDashboardActions.clickOnVoidAll("Void All");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		//All POs for the distributor DistDRDOManOFF successfully voided.
		
		String successmessage="All POs for the distributor "+TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()+" successfully voided.";
		System.out.println(successmessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successmessage);
	}
	
}
