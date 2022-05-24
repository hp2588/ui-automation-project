package com.org.tests.transactionqueue.transactionqueueupdates;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1066587 extends BaseTest {

	String priorityName,code;
	String app_url, ipAddress_2, computerName_2, priority, destination, firstname,firstname_1,firstname_2;
	String updatedQty1 = "570";
	
	@Test(priority = 1, description = "VPLX: Transaction Queue Updates: [UI]:Add Buttons to the Transaction Queue screen header")
	public void Test01_Test02_Test03_Test04_Test05_Test06_Test07_Test08_Test09_Test10_1071089_1070870_1070561_1070207_1130891_1070550_1070867_1070609_1070869_1119068(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates: [UI]:Add Buttons to the Transaction Queue screen header");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.transactionQueueActions.verifyFacilityNameIsReadOnly(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()));
		test.siteConfigurationAction.verifyButtonIsPresentOnWFAScreen("Cancel");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.VerifyButtons("Waste");
		test.transactionQueueActions.VerifyButtons("Reposition Carousel");
		test.transactionQueueActions.VerifyButtons("Reprint Label");
		test.transactionQueueActions.VerifyButtons("Hold");
		
	}
	
	
	@Test(priority = 3, description = "To verify Header message for on hold tab."
			+ "VPLX: Transaction Queue Updates:[UI]:Header message for restock.	")
	public void Test11_Test12_1077875_1077866(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates:[UI]:Rename med scan to item scan in the header for on Hold.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyTQTabHeaderText("Items put on hold from work queues will appear here");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("Released items will return back to their respective work queues");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	}
	
	@Test(priority = 4, description = "VPLX: Transaction Queue Updates: [UI]: Text changes on the transaction header.")
	public void Test13_Test14_1071130_1076847(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]: Text changes on the transaction header.");
		
		/*
		 * test.transactionQueueActions.verifyTQPageAndAppendIP(
		 * TestDataPropertyReaderAndWriter.getProperty( "IPAddress").trim());
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 */
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyScanOverrideOption();
		test.transactionQueueActions.verifyOverrideText("Please scan item.");
		
	}
	
	@Test(priority = 5, description = "VPLX: Transaction Queue Updates: [UI]: Reposition of  texts on the Transaction Header.")
	public void Test15_1071106(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]: Reposition of  texts on the Transaction Header.");
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionLocation());
		
	}	
	
	
	@Test(priority = 6, description = "VPLX: Transaction Queue Updates: [UI]:System should allow the user to edit the QOH box.")
	public void Test16_1071143(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]:System should allow the user to edit the QOH box.");
		 
		test.transactionQueueActions.clickBinScanOverride();
		// test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantity();
		// test.transactionQueueActions.updateActiveTxnQOH(3);
		test.transactionQueueActions.verifyOnHandQuanity();
		test.transactionQueueActions.clickOnQuantityOnHand(updatedQty1);
		test.supportDataActions.clickButtonIfPresent("primary");
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedQOH(updatedQty1),
				"[ASSERTION FAILED]: QOH is not updated.");
		
	}
	
	
	@Test(priority = 7, description = "VPLX: Transaction Queue Updates: [UI]: ESC key should cancel the operation, restore the original value and field should become inactive.")
	public void Test17_1071150(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]: ESC key should cancel the operation, restore the original value and field should become inactive.");
		test.siteConfigurationAction.refreshPage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.clickBinScanOverride();
		test.transactionQueueActions.verifyEscButtonResetQOH_New();
	}
	
	
	@Test(priority = 13, description = "VPLX: Transaction Queue Updates : [UI]: Search is working for Start with \"String\"")
	public void Test18_1080256(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates : [UI]: Search is working for Start with \"String\"");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.transactionQueueActions.verifyFacilityNameIsReadOnly(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()));
		test.siteConfigurationAction.verifyButtonIsPresentOnWFAScreen("Cancel");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyAndClickAddPick();
		// test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResult("Item Name",	TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		String Substring_itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim().substring(2);
		test.transactionQueueActions.searchItemValue(Substring_itemName);
		// test.transactionQueueActions.verifyNoResultFound(getData("AddRestock.NoItemFoundMessage"));
		
		test.transactionQueueActions.verifyNoResultFound(getData("AddPick.NoItemFoundMessage"));
		// test.supportDataActions.clickButton("cancel");
		// test.transactionQueueActions.clickClearSearch();
		test.transactionQueueActions.searchItemValue("");
		test.supportDataActions.clickButton("cancel");
		
	}
	
	
	@Test(priority = 14, description = "VPLX: Transaction Queue Updates: [UI]: Cancel button for select ISA from the Action menu of the Transaction Queue,")
	public void Test19_1070778(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates: [UI]: Cancel button for select ISA from the Action menu of the Transaction Queue,");
		
		//test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Select ISAs");
		test.storageAreaAction.clickButton("Cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	}
	
	@Test(priority = 15, description = "VPLX: Transaction Queue Updates: [UI]: User is able to view top four transaction priorities transaction on WFA screen.")
	public void Test20_1070616(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]: User is able to view top four transaction priorities transaction on WFA screen.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		// test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		//  test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionprioritiesOnSelectISAPage());
		test.storageAreaAction.clickButton("Cancel");
		
	}
	
	
	@Test(priority = 16, description = "VPLX: Transaction Queue Updates: [UI]: User is able to redirect to Main Menu Dashboard from WFA on clicking cancel button")
	public void Test21_1070556(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates: [UI]: User is able to redirect to Main Menu Dashboard from WFA on clicking cancel button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		//test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
		"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.clickButton("Cancel");
		test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions");
		
	}
	
	@Test(priority = 17, description = "VPLX: Transaction Queue Updates: [UI]: Rename \"Choose ISA\" to \"Select ISA\" from Action menu")
	public void Test22_1070868(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates: [UI]: Rename \"Choose ISA\" to \"Select ISA\" from Action menu");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.transactionQueueActions.verifyFacilityNameIsReadOnly(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Select ISAs");
		test.storageAreaAction.clickButton("Cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	}
	
	
	@Test(priority = 18, description = "VPLX: Transaction Queue Updates: [UI]: Cross button should be removed from the select ISA screen when navigated through Action button")
	public void Test23_1070758(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction Queue Updates: [UI]: Cross button should be removed from the select ISA screen when navigated through Action button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.transactionQueueActions.verifyFacilityNameIsReadOnly(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Select ISAs");
		test.supportDataActions.verifyCrossButtonIsNotPresent();
		test.storageAreaAction.clickButton("Cancel");
	}
	
	
	@Test(priority = 19, description = "VPLX: Transaction Queue Updates: [UI]: Error message displays for computer "
			+ "not mapped to facility.")
	public void Test24_1070871(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue Updates: [UI]: Error message displays for computer not mapped to facility.");
		
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(IPAddress);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		Assert.assertTrue(test.transactionQueueActions.verifyErrorMessageForUnauthorizedComputer(getData("TQ.UnauthorizedComputer")));
		test.transactionQueueActions.clickButton("primary");
		
	}
	
	
}
