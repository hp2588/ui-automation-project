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

public class RemoteOrderFeature_SplitOrder  extends BaseTest {
	
	String orderName;
	String FacilityName, DestinationName, Distributor;
	String columnHeaders[] = { "Order name", "Order Id", "Order date", "Items", "Ordered by", "Order from", "Status" };
	ArrayList<String> actual_data, sorted_data, order_ids, unique_order_ids;
	String OrderName, itemQuantity, ExternalSystemName, barcode, productID, ISAName,  DosageFormCode,
			DispenseUnitCode, MedClassCode;
	String OrderName_Split,OrderNamRnS, ItemCode_split, itemName_split;
	
	

	  @Test(priority = 1, description = "VPLX:Remote Ordering:[Integration]: [UI]: Pick/eaches is created "
	  		+ "in TQ while creating order from RO in case IsSplit flag is true where Package size "
	  		+ "is not equal to ordered quantity.")
		public void Test01_1117889(Method method) {
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Remote Ordering:[Integration]: [UI]: Pick/eaches is created in TQ while creating order from RO in case IsSplit flag is true where Package size is not equal to ordered quantity.");
			
			FacilityName = getData("RemoteWebOrder.FacilityName");
			DestinationName = getData("RemoteWebOrder.DestinationName_Split");
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

			itemName_split = test.siteConfigurationAction.enterDataInInputField("genericName",
					"ItemName" + System.currentTimeMillis());
			ItemCode_split = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
			test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", MedClassCode.trim());
			test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
			test.siteConfigurationAction.clickButton("save");

			test.siteConfigurationAction.verifyAndClickProductID("Product ID");
			test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
			test.siteConfigurationAction.verifyHeader("Barcodes");
			barcode = test.siteConfigurationAction.enterRandomValueInInputField_RO("barcodeValue",
					"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
			
			test.siteConfigurationAction.enterRandomValueInInputField_RO("barcodeValue",barcode);
			test.siteConfigurationAction.clickButton("search");

			productID = test.siteConfigurationAction.getParsedProductID();
			System.out.println("productID=" + productID);

			test.siteConfigurationAction.clickButton("link");
			test.siteConfigurationAction.verifyAddedProductID(productID);
			test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
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
			test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ItemCode_split);

			test.supportDataActions.enterSearchTermInSearchField(ItemCode_split, "search");
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemCode_split);

			test.siteConfigurationAction.verifyAndClickItemFacility(FacilityName);
			test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
			test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
			//test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
			test.siteConfigurationAction.verifyAndClickProductID("Product ID");
			test.siteConfigurationAction.enterCostValue("replacementCost", "10");
			test.siteConfigurationAction.clickButton("save");

			test.landingPageActions.navigateToFeature("Main Menu");
			test.landingPageActions.navigateToFeature("Item Locations");
			test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
			test.supportDataActions.enterSearchTermInSearchFieldGl(itemName_split, "search");

			// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName_split);
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
			
			test.landingPageActions.navigateToMenu("Main Menu");
			test.landingPageActions.navigateToFeature("Destinations");
			test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
			test.siteConfigurationAction.clickEditLink(DestinationName);
			test.siteConfigurationAction.clickTab("Items");
			test.siteConfigurationAction.clickButton("add");
			test.siteConfigurationAction.enterItemNameForDestinationItem(itemName_split);
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_split);
			test.siteConfigurationAction.clickCheckbox("activeFlag-0");
			test.siteConfigurationAction.clickActionbutton("Save & Close");
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_split);
			test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
			test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxDailyQuantity"));
			test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
			test.siteConfigurationAction.clickButton("save");
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

			test.launchApplication(getData("weborder_app_url"));
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
			test.siteConfigurationAction.hardWaitForChromeBrowser(20);
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			String OrderName_Split = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
			test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "textValue");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode_split,itemName_split);
			test.remoteWebOrderActions.enterItemQuantityOnROCard("15");
			test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
			
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_Split,getData("RemoteWebOrder.PendingState"));
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);
			
			test.launchApplication(getData("app_url"));
			test.landingPageActions.navigateToFeature("Transaction Queue");
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
			test.transactionQueueActions.verifyTQPageAndAppendIP("IPAddress");
			test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
			test.storageAreaAction.verifyStartWorkButtonAndClick();
			test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			
			Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
			ArrayList<String> activeTransactionName = new ArrayList<String>();
			activeTransactionName.add(itemName_split+ItemCode_split);
			activeTransactionName.add(DestinationName);
			activeTransactionName.add("Destination Orders");
			if(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName))
			{
				Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
				//Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
				//Assert.assertTrue(test.transactionQueueActions.confirmPopup());
				Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
						"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
			}
			else
			{	test.transactionQueueActions.makeROTransactionActive();
				if (test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName)) {
					Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
					Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
					Assert.assertTrue(test.transactionQueueActions.confirmPopup());
					Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
							"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
				}
				
			}
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
			test.launchApplication(getData("weborder_app_url"));
			test.siteConfigurationAction.hardWaitForChromeBrowser(20);
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_Split,getData("RemoteWebOrder.SentToDestinationState"));	
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode_split,itemName_split);
			test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName_split,"1");
			test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
			test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_Split,getData("RemoteWebOrder.VerifiedState"));	
			test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
			test.siteConfigurationAction.hardWaitForChromeBrowser(30);
			
		}

}

