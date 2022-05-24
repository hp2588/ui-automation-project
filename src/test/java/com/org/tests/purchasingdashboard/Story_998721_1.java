package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_998721_1 extends BaseTest {
	
	String className;
	
	@Test(priority = 1, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with '0' quantity, export button disabled.")
	public void Test01_1145283() {
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
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO(test.purchaseDashboardActions.generatingRandomStringForDistributor(25));
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clearrOrderQuantityItemLevel("toOrderQuantity");
		test.purchaseDashboardActions.enterOrderQuantityItemLevel("toOrderQuantity", "0");
		
		Assert.assertFalse(test.purchaseDashboardActions.verifyExportButtonIsEnabled("exportNow"), "[Assertion Failed] : Export button is not disabled");
	}
	
	@Test(priority = 2, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with '0' quantity, qty field is red color."
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with '0' quantity, error message is given.")
	public void Test02_1145284_1145289() {
		
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantity("Invalid Order Quantity"));
		
	}
	
	@Test(priority = 3, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with 'blank' quantity, export button disabled."
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with 'blank' quantity, qty field is red color."
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with 'blank' quantity, error message is given.")
	public void Test03_1145293_1145295_1145304() {
		// Order Quantity Required
		test.purchaseDashboardActions.clearrOrderQuantityItemLevel("toOrderQuantity");
		test.purchaseDashboardActions.savePONumber("New Order");
		Assert.assertFalse(test.purchaseDashboardActions.verifyExportButtonIsEnabled("exportNow"), "[Assertion Failed] : Export button is not disabled");
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantity("Invalid Order Quantity"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantityColor("toOrderQuantity","rgba(255, 0, 0, 1)"));
		
	}

	@Test(priority = 4, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA electronic PO with '0' quantity, export button is disabled.")
	public void Test04_1145285() {
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(), true);
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO(test.purchaseDashboardActions.generatingRandomStringForDistributor(25));
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clearrOrderQuantityItemLevel("toOrderQuantity");
		test.purchaseDashboardActions.enterOrderQuantityItemLevel("toOrderQuantity", "0");
		
		Assert.assertFalse(test.purchaseDashboardActions.verifyExportButtonIsEnabled("exportNow"), "[Assertion Failed] : Export button is not disabled");
		
	}
	
	@Test(priority = 5, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA PO with '0' quantity, qty field is red color." 
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA manual PO with '0' quantity, error message is given.")
	public void Test05_1145286_1145289() {
		
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantity("Invalid Order Quantity"));
	}
	
	@Test(priority = 6, description = "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA electronic PO with 'blank' quantity, export button is disabled."
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA PO for electronic order with 'blank' quantity, qty field is red color."
									+ "VPLX: Export All POs (All ISAs): [UI]: When User tries to export ALL ISA electronic PO with 'blank' quantity, error message is given.")
	public void Test06_1145298_1145300_1145302() {
		
		test.purchaseDashboardActions.clearrOrderQuantityItemLevel("toOrderQuantity");
		test.purchaseDashboardActions.savePONumber("New Order");
		Assert.assertFalse(test.purchaseDashboardActions.verifyExportButtonIsEnabled("exportNow"), "[Assertion Failed] : Export button is not disabled");
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantity("Invalid Order Quantity"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyErrorMessageOnItemQuantityColor("toOrderQuantity","rgba(255, 0, 0, 1)"));
	}
	
	
}
