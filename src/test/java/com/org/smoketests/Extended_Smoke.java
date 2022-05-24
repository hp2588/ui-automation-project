package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.bouncycastle.asn1.cmc.GetCert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.actions.TransactionQueue_Actions;
import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Extended_Smoke extends BaseTest {

	String destination, priority;
	String firstname, lastname, middlename, room, bed, comments, visitno, mrn, orderno;
	String medItem, location;
	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;
	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, priorityCodeReturn;

	@Test(priority = 01, description = "VPLX : Pick Workflow - System displays Active Item Attributes..", testName = "VPLX : Pick Workflow - System displays Active Item Attributes.")
	public void Test01_1114478(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		String quantity = test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddPick.Quantity"));
		String priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		String destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyDestinationInCurrentPickWindow(destination);
		test.transactionQueueActions.patientNamePresent(firstname);
		test.transactionQueueActions
				.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertEquals(test.transactionQueueActions.getActiveQuantity(), quantity);
	}

	@Test(priority = 02, description = "VPLX : Pick Workflow - pending Pick Transaction is completed when Pick Label is scanned.", testName = "VPLX : Pick Workflow - pending Pick Transaction is completed when Pick Label is scanned.")
	public void Test02_1114479(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce());
		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner("", ""));

	}

	@Test(priority = 03, description = "VPLX : Restock Workflow - System displays attribute in the Active Item section of the Queue")
	public void Test03_1114482(Method method)

	{
		ArrayList<String> restocktransdetail = new ArrayList<String>();

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		String tqPriority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");

		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions
				.enterDatainSearchBox(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickFirstRestockNow();
		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		test.transactionQueueActions.verifyActiveRestockItemName(restocktransdetail);
		test.transactionQueueActions.verifyActiveRestockTransactionQuantity(restocktransdetail);
		Assert.assertFalse(test.transactionQueueActions.getActiveQuantity().isEmpty());

	}

	@Test(priority = 04, description = " VPLX : Restock Workflow - pending Restock Transaction is completed when Bin is scanned")
	public void Test04_1114483(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX : Restock Workflow - pending Restock Transaction is completed when Bin is scanned");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickFirstRestockNow();
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		test.transactionQueueActions.verifyNoActiveRestockTransaction("Scan or select an item in queue to restock");
	}

	@Test(priority = 05, description = "Waste Workflow - User is able to perform a waste when processing a Pick transaction.", testName = "Waste Workflow - User is able to perform a waste when processing a Pick transaction.")
	public void Test05_1114484(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.clickPickNowLink();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		Integer inititalQOH = test.transactionQueueActions.getQuantityOnHandActiveTransactionNew() - 1;
		test.transactionQueueActions.clickWasteItem();
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity", "1");
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		Assert.assertTrue(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());
		Integer afterQOH = test.transactionQueueActions.getQuantityOnHandActiveTransactionNew();
		Assert.assertEquals(afterQOH, inititalQOH);

	}

	@Test(priority = 06, description = "Waste Workflow - System should as user Waste Reason while submitting  pick.", testName = "Waste Workflow - System should as user Waste Reason while submitting  pick.")
	public void Test06_1114485(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.clickPickNowLink();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		test.transactionQueueActions.clickWasteItem();
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity", "1");
		Assert.assertFalse(test.supportDataActions.verifySaveButtonIsEnabledOrDisabled());
		test.transactionQueueActions.confirmWasteItem("Cancel");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());

	}

	@Test(priority = 07, description = "Delete Transaction - User can delete one or more pending transaction from the Transaction Queue in Pick Mode.", testName = "Delete Transaction - User can delete one or more pending transaction from the Transaction Queue in Pick Mode.")
	public void Test07_1114487(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"");
		test.transactionQueueActions.clickDeleteButton_Sanity();
		// test.transactionQueueActions.clickDelete_Sanity(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickConfirmToDeleteButton("Confirm");
	}

	@Test(priority = 8, description = "Waste Workflow - System should as user Waste Reason while submitting waste in restock/return.", testName = "Waste Workflow - System should as user Waste Reason while submitting waste in restock/return.")
	public void Test08_1114490(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickFirstRestockNow();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		test.transactionQueueActions.clickWasteItem();
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity", "1");
		Assert.assertFalse(test.supportDataActions.verifySaveButtonIsEnabledOrDisabled());
		test.transactionQueueActions.confirmWasteItem("Cancel");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());

	}

	@Test(priority = 9, description = "Pick Workflow - User should able to preocess manually added pick transcations.", testName = "Pick Workflow - User should able to preocess manually added pick transcations.")
	public void Test09_1114493(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"");
		test.transactionQueueActions.clickPickNowLink();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());

	}

	@Test(priority = 10, description = "Return/Restock - User can save quantity on hand for an item.", testName = "Return/Restock - User can save quantity on hand for an item.")
	public void Test10_1114491(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickFirstRestockNow();
		test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantityNew2();

	}

	@Test(priority = 29, description = "Remote Order: System allow user to verify orders when Verify Remote Order option is enabled.", testName = "Remote Order: System allow user to verify orders when Verify Remote Order option is enabled.")
	public void Test29_1114495(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction
				.enterSearchTerminRoutingRule(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.verifyAndClickEditButtonOnItemLocation();
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("verifyRemoteOrderFlag", true);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enableReceiveNSend", true);
		test.siteConfigurationAction.verifyAndClickRemoveButton();
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		test.siteConfigurationAction
				.enterItemNameForDestinationItem(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.selectCheckboxForItem();
		test.supportDataActions.clickSaveAndClose("Save & Close");
		test.siteConfigurationAction.selectCheckboxForShowItems("limitedOrderQuantity-0", true);
		test.siteConfigurationAction.enterLimitOrderQtyValue("100");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction
				.enterSearchTerminRoutingRule(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.verifyAndClickEditButtonOnItemLocation();
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");

		//////// CREATE WEBORDER/////////////////
		String itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		String FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		String DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		String Distributor = TestDataPropertyReaderAndWriter.getProperty("DistributorName");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions
				.clickAvailableItemOnRO(TestDataPropertyReaderAndWriter.getProperty("ItemCode").trim(), itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");

		/********************** Validate a Remote Web Order **************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.PendingState"));
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

	@Test(priority = 30, description = "Remote Order: System allow user to submit a remote order.", testName = "Remote Order: System allow user to submit a remote order.")
	public void Test30_1114502(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");

		test.siteConfigurationAction
				.enterSearchTerminRoutingRule(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.verifyAndClickEditButtonOnItemLocation();
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("verifyRemoteOrderFlag", true);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enableReceiveNSend", true);
		test.siteConfigurationAction.verifyAndClickRemoveButton();
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		test.siteConfigurationAction
				.enterItemNameForDestinationItem(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.selectCheckboxForItem();
		test.supportDataActions.clickSaveAndClose("Save & Add More");
		test.siteConfigurationAction
				.enterItemNameForDestinationItem(TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.siteConfigurationAction.selectCheckboxForItem();
		test.supportDataActions.clickSaveAndClose("Save & Close");
		test.siteConfigurationAction.selectCheckboxForShowItems("limitedOrderQuantity-0", true);
		test.siteConfigurationAction.selectCheckboxForShowItems("limitedOrderQuantity-1", true);
		test.siteConfigurationAction.enterLimitOrderQtyValue("100", 1);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		//////// CREATE WEBORDER/////////////////
		String itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		String itemName1 = TestDataPropertyReaderAndWriter.getProperty("ItemName1").trim();
		String FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		String DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		String Distributor = TestDataPropertyReaderAndWriter.getProperty("DistributorName");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions
				.clickAvailableItemOnRO(TestDataPropertyReaderAndWriter.getProperty("ItemCode").trim(), itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions
				.clickAvailableItemOnRO(TestDataPropertyReaderAndWriter.getProperty("ItemCode").trim(), itemName1);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");

		//// VERIFY ORDER ON WEBORDER////////////
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.PendingState"));
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);

	}
	////////////////////////////////// Arun ////////////////////////////////////

	@Test(priority = 14, description = "Transaction Queue Pick list columns.", testName = "Transaction Queue Pick list columns.")
	public void Test14_1114474(Method method) {

		ArrayList<String> cols = new ArrayList<>(
				Arrays.asList("Priority", "Quantity", "Item", "Destination", "Patient name", "Actions"));
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.siteConfigurationAction.verifyColumnHeaders(cols);

	}

	@Test(priority = 1, description = "Create and View Pick Transaction", testName = "Create and View Pick Transaction")
	public void Test01_1114322(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions
				.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

	}

	@Test(priority = 2, description = "Create and View Restock Transaction")
	public void Test02_1114323(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();

	}

	@Test(priority = 4, description = "VPLX : Distributor Orders: Creating - User is able to create PO.")
	public void Test04_1114327() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);

	}

	@Test(priority = 5, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
	public void Test05_1114351(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "Return");
		// location=test.transactionQueueActions.selectDropdownForAddPick("Source
		// Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				getData("AddPick.Quantity"));

	}

	@Test(priority = 7, description = "Manual Pick from Transaction Queue.", testName = "Manual Pick from Transaction Queue.")
	public void Test07_1114444(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		lastname = "UI_LN" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_last_name", lastname);
		firstname = "UI_FN" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		middlename = "UI_MN" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_middle_name", middlename);
		room = "UI_Room" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("room", room);
		bed = "UI_Bed" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("bed", bed);
		comments = "UI_Comments" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("comments", comments);
		visitno = "UI_VisitNo" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("visit_number", visitno);
		mrn = "UI_MRN" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("mrn", mrn);
		orderno = "UI_Bed" + TransactionQueue_Actions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("order_number", orderno);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions
				.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

	}

	@Test(priority = 23, description = "Transaction Queue Search - User should able to search item in transaction queue (Pick Mode).", testName = "Transaction Queue Search - User should able to search item in transaction queue (Pick Mode).")
	public void Test23_1114486(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"");

	}

	@Test(priority = 31, description = "Quantity on Hand - User is able to edit the Quantity on Hand when processing a Pick transaction.", testName = "Quantity on Hand - User is able to edit the Quantity on Hand when processing a Pick transaction.")
	public void Test31_1114503(Method method) {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.clickPickNowLink();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantityNew2();

	}

	@Test(priority = 32, description = " VPLX : Quantity on Hand - User is able to edit the Quantity on Hand when processing a Restock transaction.")
	public void Test32_1114504(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Quantity on Hand - User is able to edit the Quantity on Hand when processing a Restock transaction.");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickRestockNowButton();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantityNew2();

	}

}
