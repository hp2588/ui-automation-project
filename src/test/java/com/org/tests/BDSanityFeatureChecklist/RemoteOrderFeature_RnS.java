package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RemoteOrderFeature_RnS  extends BaseTest {
	
	String orderName;
	String FacilityName, DestinationName, ItemCode, Distributor, itemName;
	String columnHeaders[] = { "Order name", "Order Id", "Order date", "Items", "Ordered by", "Order from", "Status" };
	ArrayList<String> actual_data, sorted_data, order_ids, unique_order_ids;
	String OrderName, itemQuantity, ExternalSystemName, barcode, productID, ISAName, ItemCode2, DosageFormCode,
			DispenseUnitCode, MedClassCode,medItem;
	String OrderName_Split, OrderNamRnS;
	String firstData, dataEnteredName, dataEnteredCode, dataEnteredName1, dataEnteredCode1, old_data, new_data;
	
	
	
	@Test(priority = 1, description = "VPLX:Remote Ordering: [Integration]: [UI]:To verify that Order created "
			+ "from Remote order is displayed in PO Dashboard in case Receive and send flag is true "
			+ "for destination."
			+ "\n&\n"
			+ "VPLX:Remote Ordering: [Integration]: [UI]:To verify that Destination name is displaying "
			+ "in PO header when order is created from remote order and displays in all "
			+ "states - New, Exported, Pending Receive and Received on PO Dashboard")
	public void Test01_1117364_1117403(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX:Remote Ordering: [Integration]: [UI]: Order created from Remote order is displayed in Buyer Dashboard in case R&S is true");
			FacilityName = getData("RemoteWebOrder.FacilityName");
			DestinationName = getData("RemoteWebOrder.DestinationName");
			itemName = getData("RemoteWebOrder.itemName");
			ItemCode = getData("RemoteWebOrder.ItemCode");
			itemQuantity = getData("RemoteWebOrder.itemQuantity");
			Distributor = getData("RemoteWebOrder.DistributorName");
			ExternalSystemName = getData("RemoteWebOrder.ExternalSystemName");
			ISAName = getData("RemoteWebOrder.ISAName");
			MedClassCode = getData("RemoteWebOrder.MedClassCode");
			DispenseUnitCode = getData("RemoteWebOrder.DispenseUnitCode");
			DosageFormCode = getData("RemoteWebOrder.DosageFormCode");
			
				
				test.landingPageActions.navigateToItemManagementFeature("Item Management");
				test.siteConfigurationAction.enterExternalSystemValueDropdownField(ExternalSystemName).trim();
				test.siteConfigurationAction.clickActionbutton("Actions");
				test.siteConfigurationAction.clickActionbutton("Add New Item");
				test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
				test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", DosageFormCode.trim());
				test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey", DispenseUnitCode.trim());

				itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
						"ItemName" + System.currentTimeMillis());
				ItemCode2 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
				test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", MedClassCode.trim());
				test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
				test.siteConfigurationAction.clickButton("save");
				test.siteConfigurationAction.verifyAndClickProductID("Product ID");
				test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
				test.siteConfigurationAction.verifyHeader("Barcodes");
				test.siteConfigurationAction.hardWaitForChromeBrowser(5);
				barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
						"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
				test.siteConfigurationAction.clickButton("search");

				productID = test.siteConfigurationAction.getParsedProductID();
				System.out.println("productID=" + productID);

				test.siteConfigurationAction.clickButton("link");
				test.siteConfigurationAction.verifyAddedProductID(productID);
				test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
				test.siteConfigurationAction.clickButton("add");
				test.siteConfigurationAction.verifyAddedProductID(productID);

				test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
				// test.siteConfigurationAction.clickDistributorInfo("preferredDistributor",
				// "1");
				test.siteConfigurationAction.clickOnDistributorInfo(Distributor);
				// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1",
				// "" + System.currentTimeMillis());
				test.siteConfigurationAction.enterDistributorItemCode(Distributor, "" + System.currentTimeMillis());
				test.siteConfigurationAction.clickButton("primary");
				test.siteConfigurationAction.clickSaveButtonForISA();
				test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ItemCode);

				test.supportDataActions.enterSearchTermInSearchField(ItemCode2, "search");
				test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemCode);

				test.siteConfigurationAction.verifyAndClickItemFacility(FacilityName);
				test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
				test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
				test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
				test.siteConfigurationAction.verifyAndClickProductID("Product ID");
				test.siteConfigurationAction.enterCostValue("replacementCost", "10");
				test.siteConfigurationAction.clickButton("save");

				test.landingPageActions.navigateToFeature("Main Menu");
				test.landingPageActions.navigateToFeature("Item Locations");
				test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
				test.supportDataActions.enterSearchTermInSearchFieldGl(itemName, "search");

				// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
				test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
				test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
				test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
				test.siteConfigurationAction.selectValueForDropDown("facility", FacilityName);
				test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
				test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
				// test.siteConfigurationAction.clickAssignLocationButton();
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
				test.siteConfigurationAction.clickSaveButton();
				
			  
			test.landingPageActions.navigateToFeature("Destinations");
			test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
			test.siteConfigurationAction.clickEditLink(DestinationName);
			test.siteConfigurationAction.clickTab("Items");
			if (!test.siteConfigurationAction.verifyItemIsMappedForRO(itemName)) {
				test.siteConfigurationAction.clickButton("add");
				test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
				test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
				test.siteConfigurationAction.clickCheckbox("activeFlag-0");
				test.siteConfigurationAction.clickActionbutton("Save & Close");
				test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
				test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
				test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
						getData("RemoteWebOrder.MaxDailyQuantity"));
				test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
				test.siteConfigurationAction.clickTab("Users");
				if (!test.siteConfigurationAction.verifyUserIsMappedForRO(getData("RemoteWebOrder.UserName"))) {
					test.siteConfigurationAction.verifyAndClickInactiveToggle();
					test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
					test.siteConfigurationAction.clickButton("save");

				}
			} else {

				test.siteConfigurationAction.clickButton("save");

			}
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			String app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
			test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
			test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
			
			/**********************Validate a Remote Web Order**************************/
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.supportDataActions.selectDropDownValue(FacilityName);

			Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
					"[Assertion failed]: Distributor name is not present on present on the purchase order card");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
					"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
			test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
			test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);
			test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
					test.supportDataActions.generatingRandomStringForPO(5));
			test.supportDataActions.savePONumber("New Order");
			test.siteConfigurationAction.clickButton("exportNow");
			test.siteConfigurationAction.clickButton("primary");
			test.supportDataActions.clickPendingReceiveCard(Distributor);
			test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
					test.supportDataActions.generatingRandomStringForInvoice(5));
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.enterItemCostForInvoice("cost", "10");
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.selectItemtoRecieve(itemName);
			
	  }
	  
	
	  @Test(priority = 2, description = "VPLX:Remote Ordering: [Integration]: [UI]: Manual Order item status "
	  		+ "on Detail page is updated in RemoteOrder on moving the cart from New to Pending Receive "
	  		+ "and Received in Buyer Dashboard")
		public void Test02_1117779(Method method) {
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Remote Ordering: [Integration]: [UI]: Manual Order item status on Detail page is updated in RemoteOrder on moving the cart from New to Pending Receive and Received in Buyer Dashboard");
			
			FacilityName = getData("RemoteWebOrder.FacilityName");
			DestinationName = getData("RemoteWebOrder.DestinationName");
			itemName = getData("RemoteWebOrder.itemName");
			ItemCode = getData("RemoteWebOrder.ItemCode");
			itemQuantity = getData("RemoteWebOrder.itemQuantity");
			Distributor = getData("RemoteWebOrder.DistributorName");
			ExternalSystemName = getData("RemoteWebOrder.ExternalSystemName");
			ISAName = getData("RemoteWebOrder.ISAName");
			MedClassCode = getData("RemoteWebOrder.MedClassCode");
			DispenseUnitCode = getData("RemoteWebOrder.DispenseUnitCode");
			DosageFormCode = getData("RemoteWebOrder.DosageFormCode");
			
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			String app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
			test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
			test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
			
			/**********************Validate a Remote Web Order**************************/
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.supportDataActions.selectDropDownValue(FacilityName);

			Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
					"[Assertion failed]: Distributor name is not present on present on the purchase order card");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
					"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
			test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
			test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);
			test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
					test.supportDataActions.generatingRandomStringForPO(5));
			test.supportDataActions.savePONumber("New Order");
			test.siteConfigurationAction.clickButton("exportNow");
			test.siteConfigurationAction.clickButton("primary");
			
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.InvoiceReceivedState"));	
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.supportDataActions.selectDropDownValue(FacilityName);
			test.supportDataActions.clickPendingReceiveCard(Distributor);
			test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
					test.supportDataActions.generatingRandomStringForInvoice(5));
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.enterItemCostForInvoice("cost", "10");
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.selectItemtoRecieve(itemName);
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName,"1");
			test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
			test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_Split,getData("RemoteWebOrder.VerifiedState"));	
			
	  }
	  
	  
	  @Test(priority = 3, description = "VPLX:Remote Ordering: [Integration]: [UI]: Electronic Order "
	  		+ "item status on Detail page is updated in RemoteOrder on moving the cart from "
	  		+ "New to Exported to Pending Receive and Received in Buyer Dashboard")
		public void Test03_1117785(Method method) {
			ExtentTestManager.startTest(method.getName(),
					" VPLX:Remote Ordering: [Integration]: [UI]: Order created from Remote order is displayed in Buyer Dashboard in case R&S is true");
			FacilityName = getData("RemoteWebOrder.FacilityName");
			DestinationName = getData("RemoteWebOrder.DestinationName");
			itemName = getData("RemoteWebOrder.itemName");
			ItemCode = getData("RemoteWebOrder.ItemCode");
			itemQuantity = getData("RemoteWebOrder.itemQuantity");
			Distributor = getData("RemoteWebOrder.DistributorName");
			ExternalSystemName = getData("RemoteWebOrder.ExternalSystemName");
			ISAName = getData("RemoteWebOrder.ISAName");
			MedClassCode = getData("RemoteWebOrder.MedClassCode");
			DispenseUnitCode = getData("RemoteWebOrder.DispenseUnitCode");
			DosageFormCode = getData("RemoteWebOrder.DosageFormCode");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.InvoiceReceivedState"));	
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			
			test.landingPageActions.navigateToFeature("Distributors");
			test.supportDataActions.verifyLabelIsPresent("Distributors");
			test.supportDataActions.clickAddButtonOnDistributor("add");
			String electronic_distibutors = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
			dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
			test.supportDataActions.clickButton("orderingRadioGroupValue-electronicDistributorFlag");
			Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-electronicDistributorFlag"));
			test.supportDataActions.clickAddButtonOnDistributor("save");
			test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
				
				test.landingPageActions.navigateToItemManagementFeature("Item Management");
				test.siteConfigurationAction.enterExternalSystemValueDropdownField(ExternalSystemName).trim();
				test.siteConfigurationAction.clickActionbutton("Actions");
				test.siteConfigurationAction.clickActionbutton("Add New Item");
				test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
				test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", DosageFormCode.trim());
				test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey", DispenseUnitCode.trim());

				itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
						"ItemName" + System.currentTimeMillis());
				ItemCode2 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
				test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", MedClassCode.trim());
				test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
				test.siteConfigurationAction.clickButton("save");
				test.siteConfigurationAction.verifyAndClickProductID("Product ID");
				test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
				test.siteConfigurationAction.verifyHeader("Barcodes");
				test.siteConfigurationAction.hardWaitForChromeBrowser(5);
				barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
						"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
				test.siteConfigurationAction.clickButton("search");

				productID = test.siteConfigurationAction.getParsedProductID();
				System.out.println("productID=" + productID);

				test.siteConfigurationAction.clickButton("link");
				test.siteConfigurationAction.verifyAddedProductID(productID);
				test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
				test.siteConfigurationAction.clickButton("add");
				test.siteConfigurationAction.verifyAddedProductID(productID);

				test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
				// test.siteConfigurationAction.clickDistributorInfo("preferredDistributor",
				// "1");
				test.siteConfigurationAction.clickOnDistributorInfo(electronic_distibutors);
				// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1",
				// "" + System.currentTimeMillis());
				test.siteConfigurationAction.enterDistributorItemCode(Distributor, "" + System.currentTimeMillis());
				test.siteConfigurationAction.clickButton("primary");
				test.siteConfigurationAction.clickSaveButtonForISA();
				test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ItemCode);

				test.supportDataActions.enterSearchTermInSearchField(ItemCode2, "search");
				test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemCode);

				test.siteConfigurationAction.verifyAndClickItemFacility(FacilityName);
				test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
				test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
				test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
				test.siteConfigurationAction.verifyAndClickProductID("Product ID");
				test.siteConfigurationAction.enterCostValue("replacementCost", "10");
				test.siteConfigurationAction.clickButton("save");

				test.landingPageActions.navigateToFeature("Main Menu");
				test.landingPageActions.navigateToFeature("Item Locations");
				test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
				test.supportDataActions.enterSearchTermInSearchFieldGl(itemName, "search");

				// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
				test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
				test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
				test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
				test.siteConfigurationAction.selectValueForDropDown("facility", FacilityName);
				test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
				test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
				// test.siteConfigurationAction.clickAssignLocationButton();
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
				test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
				test.siteConfigurationAction.clickSaveButton();
				
			  
			test.landingPageActions.navigateToFeature("Destinations");
			test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
			test.siteConfigurationAction.clickEditLink(DestinationName);
			test.siteConfigurationAction.clickTab("Items");
			if (!test.siteConfigurationAction.verifyItemIsMappedForRO(itemName)) {
				test.siteConfigurationAction.clickButton("add");
				test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
				test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
				test.siteConfigurationAction.clickCheckbox("activeFlag-0");
				test.siteConfigurationAction.clickActionbutton("Save & Close");
				test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
				test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
				test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
						getData("RemoteWebOrder.MaxDailyQuantity"));
				test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
				test.siteConfigurationAction.clickTab("Users");
				if (!test.siteConfigurationAction.verifyUserIsMappedForRO(getData("RemoteWebOrder.UserName"))) {
					test.siteConfigurationAction.verifyAndClickInactiveToggle();
					test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
					test.siteConfigurationAction.clickButton("save");

				}
			} else {

				test.siteConfigurationAction.clickButton("save");

			}
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			String app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
			test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
			test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
			
			/**********************Validate a Remote Web Order**************************/
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.supportDataActions.selectDropDownValue(FacilityName);

			Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
					"[Assertion failed]: Distributor name is not present on present on the purchase order card");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
					"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
			test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
			test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);
			test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
					test.supportDataActions.generatingRandomStringForPO(5));
			test.supportDataActions.savePONumber("New Order");
			test.siteConfigurationAction.clickButton("exportNow");
			test.siteConfigurationAction.clickButton("primary");
			
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.InvoiceReceivedState"));	
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);

			test.launchApplication(getData("app_url"));
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
					getData("Auth.passwordAdminUser"), "");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.supportDataActions.selectDropDownValue(FacilityName);
			test.supportDataActions.clickPendingReceiveCard(Distributor);
			test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
					test.supportDataActions.generatingRandomStringForInvoice(5));
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.enterItemCostForInvoice("cost", "10");
			test.supportDataActions.savePONumber("PendingReceive");
			test.supportDataActions.selectItemtoRecieve(itemName);
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			app_url = getYamlValue("weborder_app_url");
			test.launchApplication(app_url);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
					getData("Auth.ip").trim());
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
			test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName,"1");
			test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
			test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_Split,getData("RemoteWebOrder.VerifiedState"));	
			
	  }

}

