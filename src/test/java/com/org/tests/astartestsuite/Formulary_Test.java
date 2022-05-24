package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Formulary_Test extends BaseTest {
	String  barcode, productID,PONumber,distributorName;
	String itemClass,itemName,bulkitemdes,itemID,medItem1, medItem2;
	private WebElement bulkitems;

	@Test(priority = 1, description = "Formulary (Cycle count interval): When value is given in Cycle count interval. For that item cycle count will be created automatically in transaction queue pick tab")
	public void Test01_1117346(Method method){
		ExtentTestManager.startTest(method.getName(), "Formulary (Cycle count interval): When value is given in Cycle count interval. For that item cycle count will be created automatically in transaction queue pick tab");
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField("SeattleHospitalCare");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityx"+System.currentTimeMillis());
		itemID=test.siteConfigurationAction.enterDataInInputField("itemId","SystemlevelItem77x"+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name3"));
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.clickfacilityonEditItem("1");
		test.siteConfigurationAction.enterRandomValueInInputField("cycleCountIntervalDayAmount", "1");
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		//test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		//test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		distributorName=test.supportDataActions.getAddedDistributorName();
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickButton("save");

		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionInPickTab(itemID),"[ASSERTION FAILED]: Created Item not found in Pick Queue Tab.");
		
		
		
		
	}
	
	@Test(priority = 2, description = "Formulary (Cycle count interval): When value is given in Cycle count interval. For that item cycle count will be created automatically in transaction queue pick tab")
	public void Test02_1117353(Method method){
		ExtentTestManager.startTest(method.getName(), "Formulary (Cycle count interval): When value is given in Cycle count interval. For that item cycle count will be created automatically in transaction queue pick tab");
		/*test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField("SeattleHospitalCare");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilityx"+System.currentTimeMillis());
		itemID=test.siteConfigurationAction.enterDataInInputField("itemId","SystemlevelItem77x"+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name3"));
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit Item");
		test.siteConfigurationAction.clickfacilityonEditItem("1");
		test.siteConfigurationAction.enterRandomValueInInputField("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
*/		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", itemID);
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem1=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		//PONumber=test.supportDataActions.EnterRandomValueInInputField("purchaseOrderNumber", "PO"+System.currentTimeMillis());
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(),true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(distributorName);
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice", test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterItemCostForInvoice("cost","10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");

		
	}
	
	@Test(priority = 3, description = "Formulary(Cost update fields: Average Cost (NDC-level)): System automatically updates Average cost every time a distributor order is received for the item.")
	public void Test03_1118494(Method method){
		ExtentTestManager.startTest(method.getName(), "Formulary(Cost update fields: Average Cost (NDC-level)): System automatically updates Average cost every time a distributor order is received for the item.");
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", itemID);
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem2=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		
					
	}
	
	@Test(priority = 4, description = "Formulary(Cost update fields: Replacement Cost (NDC-level)): System automatically updates Replacement cost every time a distributor order is received for the item.")
	public void Test04_1118494(Method method){
		ExtentTestManager.startTest(method.getName(), "Formulary(Cost update fields: Average Cost (NDC-level)): System automatically updates Average Formulary(Cost update fields: Replacement Cost (NDC-level)): System automatically updates Replacement cost every time a distributor order is received for the item.cost every time a distributor order is received for the item.");
	
	
	}
}
