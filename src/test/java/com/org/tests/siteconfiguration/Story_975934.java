package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975934 extends BaseTest {
	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax, limitOrder, itemName, item_detail, searched_item;
	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 0, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button")
	public void Test00_1055183(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.siteConfigurationAction.selectFacilityDropdownForDestination("Fac1586166951259");
		//String fac=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndexForDestination(1);
		//System.out.println("Facility name "+fac);
		//test.siteConfigurationAction.selectValueFromDropdownByIndexForDestination(1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		
		
		test.siteConfigurationAction.clickTab("Items");
		// test.siteConfigurationAction.verifyColumnsonItemTab(getData("Destination_ItemsTab.ExpectedColumns"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem1"));

		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();

		test.siteConfigurationAction.getSearchedItemDetails();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");

		item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(0);
		Assert.assertTrue(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, searched_item)); 
	}

	@Test(priority = 1, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field default value will be -")
	public void Test01_1054649(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field default value will be -");
		test.siteConfigurationAction.isCheckboxDisplayedItemsTab("limited_order_qty_checkbox");
		test.siteConfigurationAction.verifyMaxOrderQty("—");
		test.siteConfigurationAction.verifyColumnsonItemTab(getData("Destination_ItemsTab.ExpectedColumns"));
	}

	@Test(priority = 2, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field can be updated after clicking on the Limit Order Quantity field"

	)
	public void Test02_1054685(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field can be updated after clicking on the Limit Order Quantity field");
		test.siteConfigurationAction.selectCheckboxItemsTab("limitedOrderQuantity-0", true);

	}

	@Test(priority = 3, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field can have max value at 5 digit"

	)
	public void Test03_1054718(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) field can have max value at 5 digit");
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("maximumOrderQuantity-0"));
		test.siteConfigurationAction.enterLimitOrderQtyValue(getData("Destination_ItemsTab.maxordervalue"));

	}

	@Test(priority = 4, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) value persist after unchecking the Limit Order Quantity field"

	)
	public void Test04_1054735(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Maximum order Quantity (eaches) value persist after unchecking the Limit Order Quantity field");
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("maximumOrderQuantity-0"));
		test.siteConfigurationAction.enterLimitOrderQtyValue(getData("Destination_ItemsTab.maxordervalue"));
		test.siteConfigurationAction.selectCheckboxItemsTab("limitedOrderQuantity-0", false);

		test.siteConfigurationAction.verifyInputFieldvalue("max_order_qty_text",
				getData("Destination_ItemsTab.maxordervalue"));
	}

	@Test(priority = 5, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify error message will throw when Maximum order Quantity (eaches) value is blank"

	)
	public void Test05_1054769(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify error message will throw when Maximum order Quantity (eaches) value is blank");

		test.siteConfigurationAction.selectCheckboxItemsTab("limitedOrderQuantity-0", true);
		test.siteConfigurationAction.clearText("max_order_qty_text");
		test.siteConfigurationAction.verifyValidationMessageOnField("maxOrderQuantity", "1",
				getData("Destination_ItemsTab.MaxQtyErrorMessage"));
	}

	@Test(priority = 6, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Package size field is disabled by default"

	)
	public void Test06_1054823(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Package size field is disabled by default");

		Assert.assertFalse(test.siteConfigurationAction.verifyPackageSizeFieldisGrayedOrNot());
	}

	@Test(priority = 7, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Package size field values will get enabled after clicking on the one of the check box for Remote order or Split order"

	)
	public void Test07_1054828(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Package size field values will get enabled after clicking on the one of the check box for Remote order or Split order");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableSplitOrders", true);

		Assert.assertTrue(test.siteConfigurationAction.verifyPackageSizeFieldisGrayedOrNot());
		test.siteConfigurationAction.selectCheckboxItemsTab("enableSplitOrders", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("enableReceiveNSend", true);
		Assert.assertTrue(test.siteConfigurationAction.verifyPackageSizeFieldisGrayedOrNot());

	}

	@Test(priority = 8, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify User can check either one or both the check boxes for the 'Enable Split Orders' and  'Enable Receive-N-Send'"

	)
	public void Test08_1054841(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify User can check either one or both the check boxes for the 'Enable Split Orders' and  'Enable Receive-N-Send'");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableSplitOrders", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("enableReceiveNSend", true);
		Assert.assertTrue(test.siteConfigurationAction.verifyPackageSizeFieldisGrayedOrNot());
	}

	@Test(priority = 9, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Show items that will never be included in a distributor purchase order check box will get disabled by default"

	)
	public void Test09_1054886(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Show items that will never be included in a distributor purchase order check box will get disabled by default");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableSplitOrders", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("enableReceiveNSend", false);
	}

	@Test(priority = 10, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify check box of Show items that will never be included in a distributor purchase order will include the order which has Maximum Order Quantity is less than a full package size")

	public void Test10_1055155(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify check box of Show items that will never be included in a distributor purchase order will include the order which has Maximum Order Quantity is less than a full package size");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableSplitOrders", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("showItems"));
		test.siteConfigurationAction.selectCheckboxForShowItems("showItems", true);

	}

	@Test(priority = 11, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Control will remain on the search window after clicking on the Save and More button")
	public void Test11_1055228(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Control will remain on the search window after clicking on the Save and More button");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem2"));
		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();
		test.siteConfigurationAction.getSearchedItemDetails();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Add");
		test.siteConfigurationAction.verifyAddItemPopup();

	}

	@Test(priority = 12, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify No item will get added after clicking on the Cancel button"

	)
	public void Test12_1055229(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify No item will get added after clicking on the Cancel button");
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem3"));

		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();
		test.siteConfigurationAction.clickCancelButtonOnItemPopup();
		item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(1);
		Assert.assertFalse(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, searched_item));

	}

	@Test(priority = 17, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Items can be removed after clicking on the Remove button for the  respective Formulary")
	public void Test18_1055232(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Items can be removed after clicking on the Remove button for the  respective Formulary");
		test.siteConfigurationAction.enterLimitOrderQtyValue(getData("Destination_ItemsTab.maxordervalue"));
		test.siteConfigurationAction.verifyAndClickRemoveButton();
		Assert.assertFalse(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, "Calpol"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem3"));
		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");
		item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(0);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(searched_item);

	}

	@Test(priority = 13, description = "VPLX: Manage Destinations - Formulary Items : [UI] Perform Sorting on the basis of Item field")
	public void Test13_1054589(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Perform Sorting on the basis of Item field");
		test.siteConfigurationAction.verifyAndClickSortIcon("Item");
		previous_data = test.siteConfigurationAction.captureDataForParticularColumnDestination("Item");
		sorted_data = test.siteConfigurationAction.sortDataForParticularColumnInAscendingOrderDestination("Item");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");

	}

	@Test(priority = 15, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify note is visible only when either one or  both  of the checkboxes 'Enable Split Orders' or 'Enable Receive-N-Send' is checked")
	public void Test15_1055240(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Perform Sorting on the basis of Item field");
		test.siteConfigurationAction.verifyShowItemsField();

	}

	@Test(priority = 16, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verification of all the fields related to Formulary items for the Destination")

	public void Test16_1054435(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verification of all the fields related to Formulary items for the Destination");
		test.siteConfigurationAction.verifyColumnsonItemTab(getData("Destination_ItemsTab.ExpectedColumns"));

	}

	@Test(priority = 18, description = "VPLX:Manage Destination Formulary Items: Feature testing -User is able to save the record when max order quantity is entered")
	public void Test17_1061962(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destination Formulary Items: Feature testing -User is able to save the record when max order quantity is entered");

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

	}
}
