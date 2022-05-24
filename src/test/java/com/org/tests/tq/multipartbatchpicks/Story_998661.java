package com.org.tests.tq.multipartbatchpicks;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998661 extends BaseTest {
	String activeQuantity, activeQuantityAfter;

	@Test(priority = 1, description = "VPLX:Batch Picks:[UI]-User is able to see the Batch Transactions on 'Review/Release Batch Pick' screen")
	public void Test01_1051330_1052883(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]-User is able to see the Batch Transactions on 'Review/Release Batch Pick' screen");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item8"));
		test.transactionQueueActions.batchTransactionListIsPresent();
		test.transactionQueueActions.headingBatchPick();
	}

	@Test(priority = 2, description = "VPLX:Batch Picks:[UI] - User is able to see the batches on the basis of Transaction Priority.")
	public void Test02_1051350(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to see the batches on the basis of Transaction Priority.");
		test.transactionQueueActions.batchTransactionListOnPriorityBasis(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
	}

	@Test(priority = 3, description = "VPLX:Batch Picks:[UI]-User verifies the columns on the left grid of the section")
	public void Test03_1051361(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]-User verifies the columns on the left grid of the section");
		test.transactionQueueActions.columnHeadingBatchPick("Priority");
		test.transactionQueueActions.columnHeadingBatchPick("Total Items");
		test.transactionQueueActions.columnHeadingBatchPick("Number of Destinations");
	}

	@Test(priority = 4, description = "VPLX: Batch Picks :[UI]- Release and Ignore buttons are present in individual row on 'Review/Release Batch Pick' screen.")
	public void Test04_1053270_1053349_1053282(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Batch Picks :[UI]- Release and Ignore buttons are present in individual row on 'Review/Release Batch Pick' screen.");
		Assert.assertTrue(test.transactionQueueActions.verifyBatchTransactionReleaseLinkCount());
		Assert.assertTrue(test.transactionQueueActions.verifyBatchTransactionIgnoreLinkCount());
	}

	@Test(priority = 5, description = "VPLX: Batch Picks :[UI]- Destination wise information for every batch is present on the right side.")
	public void Test05_1053276_1053368(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Batch Picks :[UI]- Destination wise information for every batch is present on the right side.");
		test.transactionQueueActions
				.clickTransactionOnBatch(TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.batchTransactionListOnPriorityBasis(
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
	}

	@Test(priority = 6, description = "VPLX:Batch Picks:[UI]- User is able to see the columns on the right grid of the section")
	public void Test06_1053384(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]- User is able to see the columns on the right grid of the section");
		test.transactionQueueActions.columnHeadingBatchPick("Destinations");
		test.transactionQueueActions.columnHeadingBatchPick("Quantity");
		test.transactionQueueActions.columnHeadingBatchPick("Picked");
		test.transactionQueueActions.columnHeadingBatchPick("Status");
	}

	@Test(priority = 7, description = "VPLX:Batch Picks:[UI] - User is able to see the check box on the right side.")
	public void Test07_1053396(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to see the check box on the right side.");
		test.transactionQueueActions.checkboxPresentOnBatchPickScreen();
	}

	@Test(priority = 8, description = "VPLX:Batch Picks:[UI]- User is able to see the batch creation date and time on the 'Review/Release Batch Pick' screen")
	public void Test08_1053355(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]- User is able to see the batch creation date and time on the 'Review/Release Batch Pick' screen");
		test.transactionQueueActions.verifyBatchCreationTime();
	}

	@Test(priority = 9, description = "VPLX:Batch Picks:[UI]- Release/Ignore button gets enabled for Batch Ignore and release on selection of destination for processing")
	public void Test09_1053411(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]- Release/Ignore button gets enabled for Batch Ignore and release on selection of destination for processing");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyRightPanelReleaseButton();
		test.transactionQueueActions.verifyRightPanelIgnoreButton();
	}

	@Test(priority = 10, description = "VPLX:Batch Picks:[UI] - User is able to close Batch screen by clicking on cancel button")
	public void Test10_1053412(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to close Batch screen by clicking on cancel button");
		test.supportDataActions.clickButton("cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}

	@Test(priority = 11, description = "VPLX: Batch Picks :[UI]- User is able to mark Batch transaction as 'Processed' when click on 'Ignore' button.")
	public void Test11_1053281_1053329_1053330_1053272_1053274_1053406_1053401(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Batch Picks :[UI]- User is able to mark Batch transaction as 'Processed' when click on 'Ignore' button.");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item8"));
		test.transactionQueueActions.headingBatchPick();
		test.transactionQueueActions.listProcessedItem(0);
		test.transactionQueueActions.clickIgnoreButton();
		test.transactionQueueActions.listProcessedItem(1);
		test.transactionQueueActions
				.rightPanelBatchCreationTime(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.checkboxNotPresentOnBatchPickScreen();
		test.supportDataActions.clickButton("cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.listBatchPick(0);
	}

	@Test(priority = 12, description = "VPLX: Batch Picks :[UI]- User is able to see Batch transaction in TQ[UI] when the Batch is Processed from 'Review/Release Batch Pick' screen.	Kumar Prakash")
	public void Test12_1053268_1053361_1053269_1059056(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to close Batch screen by clicking on cancel button");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item8"));
		test.transactionQueueActions.headingBatchPick();
		test.transactionQueueActions.clickReleaseButton();
		test.transactionQueueActions.listProcessedItem(2);
		test.transactionQueueActions.clickReleaseButton();
		test.supportDataActions.clickButton("cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.listBatchPick(1);
	}
	
	@Test(priority = 13, description = "VPLX: Batch Picks :[UI]- System is removed Ignore/Release button once the whole Batch processed.")
	public void Test13_1053278(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX: Batch Picks :[UI]- System is removed Ignore/Release button once the whole Batch processed.");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item8"));
		test.transactionQueueActions.headingBatchPick();
		test.transactionQueueActions.releaseRemainingTransaction();
		test.transactionQueueActions.verifyIgnoreAndReleaseLinkAreAbsent();
		test.supportDataActions.clickButton("cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}
	
	@Test(priority = 14, description = "VPLX:Batch Picks:[UI]- Accept button is disabled if sum of Quantity to Process is not equal to Picked Quantity.")
	public void Test14_1054840_1054782_1055015_1059447_1059451(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Batch Picks:[UI]- Accept button is disabled if sum of Quantity to Process is not equal to Picked Quantity.");
		test.transactionQueueActions.listBatchPickNow();
		activeQuantity = test.transactionQueueActions.getCurrentQuantity();
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		test.transactionQueueActions.clickOnQuantity("100");	
		//test.siteConfigurationAction.clickPickRoutingRuleButton("showQuantityField-submit");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.transactionQueueActions.verifyBatchPopupHeading("Item ID");
		test.transactionQueueActions.verifyBatchPopupHeading("Item Description");
		test.transactionQueueActions.verifyBatchPopupHeading("Requested Quantity");
		test.transactionQueueActions.verifyBatchPopupHeading("Picked Quantity");
		test.transactionQueueActions.verifyBatchPopupHeading("Correction Needed");

		/*Assert.assertTrue(test.transactionQueueActions.verifyBatchPopupSubHeading("Item ID",
				TestDataPropertyReaderAndWriter.getProperty("ItemID")));
		Assert.assertTrue(test.transactionQueueActions.verifyBatchPopupSubHeading("Item Description",
				TestDataPropertyReaderAndWriter.getProperty("ItemName")));
		Assert.assertTrue(
				test.transactionQueueActions.verifyBatchPopupSubHeadingQuantity("Requested Quantity", activeQuantity));
		Assert.assertTrue(
				test.transactionQueueActions.verifyBatchPopupSubHeadingQuantity("Picked Quantity", "100"));
		Assert.assertTrue(test.transactionQueueActions.verifyBatchPopupSubHeadingQuantity("Correction Needed", "0"));*/
		
		test.transactionQueueActions.verifyProcessedQuantity("2.5", "5");
		test.transactionQueueActions.verifyProcessedQuantity("-23", "23");
		test.transactionQueueActions.updateProcessedQuantity("30");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("accept"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		activeQuantityAfter = test.transactionQueueActions.getCurrentQuantity();
		Assert.assertEquals(activeQuantity, activeQuantityAfter);
	}
	
	@Test(priority = 15, description = "VPLX:Batch Picks:[UI]-User is able to change the quantity when transaction is active.")
	public void Test15_1054722_1054795_1054820_1054995(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX:Batch Picks:[UI]-User is able to change the quantity when transaction is active.");
		activeQuantity = test.transactionQueueActions.getCurrentQuantity();
		test.transactionQueueActions.clickOnQuantity("100");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.transactionQueueActions.updateProcessedQuantity("20");
		test.siteConfigurationAction.clickPickRoutingRuleButton("accept");
		activeQuantityAfter = test.transactionQueueActions.getCurrentQuantity();
		Assert.assertEquals("100", activeQuantityAfter);
	}
	
	@Test(priority = 16, description = "VPLX: Batch Picks :[UI]-  system is removed Batch Transaction form TQ UI screen when batch pick transaction has been completed from UI.")
	public void Test16_1053279(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX: Batch Picks :[UI]-  system is removed Batch Transaction form TQ UI screen when batch pick transaction has been completed from UI.");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
	}

}
