package com.org.data.api;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class BatchPick_Sanity extends BaseTest {
	String activeQuantity, activeQuantityAfter;

	@Test(priority = 1, description = "VPLX:Batch Picks:[UI]-User is able to see the Batch Transactions "
			+ "on 'Review/Release Batch Pick' screen")
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
	
	
	@Test(priority = 2, description = "VPLX:Batch Picks:[UI] - User is able to see the batches "
			+ "on the basis of Transaction Priority.")
	public void Test02_1051350(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to see the batches on the basis of Transaction Priority.");
		test.transactionQueueActions.batchTransactionListOnPriorityBasis(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
	}

	@Test(priority = 3, description = "VPLX:Batch Picks:[UI]-User verifies the columns "
			+ "on the left grid of the section")
	public void Test03_1051361(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]-User verifies the columns on the left grid of the section");
		test.transactionQueueActions.columnHeadingBatchPick("Priority");
		test.transactionQueueActions.columnHeadingBatchPick("Total Items");
		test.transactionQueueActions.columnHeadingBatchPick("Number of Destinations");
	}
	
	@Test(priority = 4, description = "VPLX: Batch Picks :[UI]- Release and Ignore buttons are present "
			+ "in individual row on 'Review/Release Batch Pick' screen.")
	public void Test04_1053270_1053349_1053282(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Batch Picks :[UI]- Release and Ignore buttons are present in individual row on 'Review/Release Batch Pick' screen.");
		Assert.assertTrue(test.transactionQueueActions.verifyBatchTransactionReleaseLinkCount());
		Assert.assertTrue(test.transactionQueueActions.verifyBatchTransactionIgnoreLinkCount());
	}
	
	@Test(priority = 5, description = "VPLX: Batch Picks :[UI]- Destination wise information "
			+ "for every batch is present on the right side.")
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
	
	@Test(priority = 8, description = "VPLX:Batch Picks:[UI]- User is able to see "
			+ "the batch creation date and time on the 'Review/Release Batch Pick' screen")
	public void Test08_1053355(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]- User is able to see the batch creation date and time on the 'Review/Release Batch Pick' screen");
		test.transactionQueueActions.verifyBatchCreationTime();
	}
	
	@Test(priority = 9, description = "VPLX:Batch Picks:[UI]- Release/Ignore button "
			+ "gets enabled for Batch Ignore and release on selection of destination for processing")
	public void Test09_1053411(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI]- Release/Ignore button gets enabled for Batch Ignore and release on selection of destination for processing");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyRightPanelReleaseButton();
		test.transactionQueueActions.verifyRightPanelIgnoreButton();
	}

	@Test(priority = 10, description = "VPLX:Batch Picks:[UI] - User is able to close Batch screen "
			+ "by clicking on cancel button")
	public void Test10_1053412(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Batch Picks:[UI] - User is able to close Batch screen by clicking on cancel button");
		test.supportDataActions.clickButton("cancel");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}

	@Test(priority = 11, description = "VPLX: Batch Picks :[UI]- User is able to mark Batch transaction "
			+ "as 'Processed' when click on 'Ignore' button.")
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
	
}
