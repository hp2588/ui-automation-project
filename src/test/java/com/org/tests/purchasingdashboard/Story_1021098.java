package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1021098 extends BaseTest{
	
	String medItem_two;
	String poNumber;

	@Test(priority = 1, description = "VPLX:Remove Items from PO:[UI]: When all items get selected then remove button is available for use")
	public void Test01_1028644() {

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
		test.purchaseDashboardActions.verifyRemoveButtonIsDisabled("ignore");
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyRemoveButtonIsEnabled("ignore"),"[Assertion Failed]: Remove button is not enabled");
	}

	@Test(priority = 2, description = "VPLX:Remove Items from PO:[UI]: All the main attributes(Package Size,Order Quantity,Min Qty,Max Qty,Adjusted On Hand,Items,VIC and NDC) should be the part of listing")
	public void Test02_1028646() {
		
		//test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		//test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		//test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
		
		test.purchaseDashboardActions.verifyItemHeaders("Items");
		test.purchaseDashboardActions.verifyItemHeaders("VIC");
		test.purchaseDashboardActions.verifyItemHeaders("Order Qty (Pkgs)");
		test.purchaseDashboardActions.verifyItemHeaders("Min Qty");
		test.purchaseDashboardActions.verifyItemHeaders("Max Qty");
		test.purchaseDashboardActions.verifyItemHeaders("Adj On Hand");
		test.purchaseDashboardActions.verifyItemHeaders("Pkg Size");
		test.purchaseDashboardActions.verifyItemHeaders("NDC");
	}
	@Test(priority = 3, description = "VPLX:Remove Items from PO:[UI]: Select check box for each item is present")
	public void Test03_1028649() {
		
		//test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		//test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		//test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
	}
	
	@Test(priority = 4, description = "VPLX:Remove Items from PO:[UI]: Item name is display with full String count")
	public void Test04_1028655() {
		
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyItemNameInPOCard(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim()));
		
	}
	
	@Test(priority = 5, description = "VPLX:Remove Items from PO:[UI]: slide bar is present if more item in the PO list exist")
	public void Test05_1028656() {
		
		test.purchaseDashboardActions.dashboardScroll();
	}
	
	@Test(priority = 6, description = "VPLX:Remove Items from PO:[UI]: Local filtering is applicable for All ISA section")
	public void Test06_1048360() {
		
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyItemNameInPOCard(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim()));
	}
	
	@Test(priority = 7, description = "VPLX:Remove Items from PO:[UI]: Use Tab to navigate on the page though out")
	public void Test07_1048365() {
		test.purchaseDashboardActions.enterPONumberForActivePO(RandomStringUtils.random(10, false, true));
		test.purchaseDashboardActions.clickOnTabEditableField("exportNow");
	}
	
	@Test(priority = 8, description = "VPLX:Remove Items from PO:[UI]: PO format 15672(vendor item code):c02(carousel name ):06232019(date format) is editable")
	public void Test08_1048421() {
		
		test.purchaseDashboardActions.enterPODescription("purchaseOrderDescription","PODescription-Test");
	}
	
	@Test(priority = 9, description = "VPLX:Remove Items from PO:[UI]: PO number on the left side panel is editable upto 25 digit")
	public void Test09_1048431() {
		
		test.purchaseDashboardActions.enterPONumberForActivePO(RandomStringUtils.random(30, false, true));
		Assert.assertTrue(test.purchaseDashboardActions.validatePONumberFieldLength(),
				"[Assertion Failed]: The purchase order number field length is not 25");
	}
	
	@Test(priority = 10, description = "VPLX:Remove Items from PO:[UI]: Order Quantity can change/editable for \"created order\"")
	public void Test10_1048439() {
		
		test.purchaseDashboardActions.verifyItemNameInPOCard(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantityItemLevel("toOrderQuantity", "12");
	}
	
	@Test(priority = 11, description = "VPLX:Remove Items from PO:[UI]: Add Items button is enable on the item listing page")
	public void Test11_1048474() {
		
		Assert.assertTrue(test.purchaseDashboardActions.validateAddButtonIsPresent("add"));
		
	}
	
	@Test(priority = 12, description = "VPLX:Remove Items from PO:[UI]: Void and void all hyper links should be displayed as per the VD")
	public void Test12_1048480() {
		
		test.purchaseDashboardActions.verifyVoidLinkisPresent("void");
		
	}
}
