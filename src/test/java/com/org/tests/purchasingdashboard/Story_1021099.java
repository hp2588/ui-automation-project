package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1021099 extends BaseTest{

	@Test(priority = 1, description = "VPLX:Remove Items from PO:[UI]:Select one item from the list of items under PO")
	public void Test01_1028668() {
	
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
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	}
	
	@Test(priority = 2, description = "VPLX:Remove Items from PO:[UI]:Remove button is present on the page")
	public void Test02_1028673_1028696() {
		
	Assert.assertTrue(test.purchaseDashboardActions.verifyRemoveButtonEnabled("ignore"));
	}
	
	@Test(priority = 3, description = "VPLX:Remove Items from PO:[UI]:Select one item and click on remove button to delete the item")
	public void Test03_1028684_1028693() {
		
	test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
	test.purchaseDashboardActions.clickButtonOnDashboard("primary");
	Assert.assertTrue(test.purchaseDashboardActions.verifyItemIsNotVisibleAfterRemove(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim()));
	
	}
	
	@Test(priority = 4, description = "VPLX:Remove Items from PO:[UI]:Select more than one item and click on remove button to delete the items")
	public void Test04_1028689() {
		
		// add an item and click on checkall
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.SearchPOItem("Search Item", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantityAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.clickCheckboxAll("checkboxALL");
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
		//test.purchaseDashboardActions.verifyRemoveButtonIsDisabled("ignore");
	}
	
	@Test(priority = 5, description = "VPLX:Remove Items from PO:[UI]:Check the remove button disable when no item is selected")
	public void Test05_1028700() {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.verifyRemoveButtonIsDisabled("ignore");
		
	}	
	
	@Test(priority = 6, description = "VPLX:Remove Items from PO:[UI]: Item gets deselected on selection and then deselection")
	public void Test06_1028703() {
	
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	}
	
}
