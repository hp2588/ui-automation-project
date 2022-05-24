package com.org.tests.manualrestock;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982256 extends BaseTest {
	String[] ColunHeaderRestock = { "Item", "Item ID", "Brand Name","Quantity on hand","Location","Preferred NDC" };

	@Test(priority=1, description= "VPLX:Manual Restock: UI: Items and its Details (Item Name,Item ID, Generic Name, Location,Product ID) are displayed when user searches for valid item having location on Add Restock page.")
	public void Test01_1029541(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Items are displayed when user searches for valid items on Add Restock page");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		//test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		//test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		//test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	   // test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.verifyColumnHeadersText(ColunHeaderRestock);
		test.transactionQueueActions.verifySearchItemFields(getData("AddRestock.ExpectedSearchHeader"));
		
	}
	
	
	@Test(priority=2, description= "VPLX:Manual Restock: UI: Showinactive toggle if true displays inactive items in search results.")
	public void Test02_1029556(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Showinactive toggle if true displays inactive items in search results.");
		
		test.transactionQueueActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Item Management");
		//test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		
		Assert.assertTrue(test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item"));
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", 
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		String itemName = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		String itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickToggleButtonItemMgt("false", "activeFlag");
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.clickToggleButtonItemMgt("true", "toggle");
		test.supportDataActions.clickRecordNameToEdit(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		
		test.siteConfigurationAction.verifyHeader("Barcodes");
		String barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		
		String productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID = " + productID);
		
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//TestDataPropertyReaderAndWriter.setProperty("ItemCode",itemID);
		//TestDataPropertyReaderAndWriter.setProperty("ItemName",itemName);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), 
				"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemName);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		//test.transactionQueueActions.clearText("search_item");   
		test.transactionQueueActions.verifyAndClickShowAllItems();
 	   	test.transactionQueueActions.searchItemValue(itemName);
 	   	test.transactionQueueActions.verifySearchedResultForReturn("Item Name", itemName);
 	   	test.transactionQueueActions.verifySearchItemFields(getData("AddRestock.ExpectedSearchHeader"));
 	   	test.siteConfigurationAction.clickButton("cancel");
 	   	test.siteConfigurationAction.clickButton("primary");
 	   	
	} 
	
	@Test(priority=3, description= "VPLX:Manual Restock: [UI]: No items are displayed when "
			+ "no item matching the search string is present.")
	public void Test03_1029579(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: [UI]: No items are displayed when no item matching the search string is present.");
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		
		//test.transactionQueueActions.clearText("search_item");
		test.transactionQueueActions.searchItemValue(getData("AddRestock.InvalidItemName"));
     	test.transactionQueueActions.verifyNoResultFound(getData("AddRestock.NoItemFoundMessage"));
     	
    }

}
