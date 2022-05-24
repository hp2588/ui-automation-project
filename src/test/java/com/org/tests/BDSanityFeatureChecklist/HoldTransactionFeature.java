package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class HoldTransactionFeature extends BaseTest {
	ArrayList<String> previous_data, after_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Transaction Queue-Pick-UI: Select the Pick tab if not selected.")
	public void Test01_1129531(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Select the Pick tab if not selected.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.getDetails());
	}
	
	
	@Test(priority = 2, description = "VPLX:Transaction queue actions-Hold selected: Hold Button is available in front of all the transactions available in the transaction queue and at the top right corner when multiple transactions are selected")
	public void Test02_1000899(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Hold Button is available in front of all the transactions available in the transaction queue and at the top right corner when multiple transactions are selected");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionHoldLinkCount("Hold"));
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionButtonIsPresent(getData("ButtonActions.Hold"));
	}
	
	
	@Test(priority = 3, description = "VPLX:Transaction queue actions-Hold selected: [UI]:To verify Putting "
			+ "a single transaction from Pick tab on Hold")
	public void Test03_1000362_1129692_1013314(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: [UI]: Putting a single transaction from Picks tab on Hold");
		
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		test.transactionQueueActions.verifyAndReleaseHoldTransaction(getData("TabActions.Release"), "Release");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		previous_data = test.transactionQueueActions.getTransactionDetails(1);
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Hold");
		test.transactionQueueActions.verifySuccessMessageOnViewPage("Transaction put on hold");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		after_data = test.transactionQueueActions.getTransactionDetails(1);
		Assert.assertEquals(previous_data, after_data,
				"[ASSERTION FAILED] : Single transaction does not get hold from Pick tab");
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Release");
	}
	
	
	@Test(priority = 4, description = "VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Restock tab on Hold")
	public void Test04_1000373(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Restock tab on Hold");
		
		test.transactionQueueActions.verifyAndReleaseHoldTransaction(getData("TabActions.Release"), "Release");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		previous_data = test.transactionQueueActions.getTransactionDetails(1);
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Hold");
		test.transactionQueueActions.verifySuccessMessageOnViewPage("Transaction put on hold");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		after_data = test.transactionQueueActions.getTransactionDetails(1);
		Assert.assertEquals(previous_data, after_data,
				"[ASSERTION FAILED] : Single transaction does not get hold from Restock tab");
	}
	
	
	@Test(priority = 05, description = "VPLX:Transaction queue actions-Hold selected: [UI]: Hold Reason list available in hold item dialog box is populated as per the site configuration")
	public void Test05_1000396(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: [UI]: Hold Reason list available in hold item dialog box is populated as per the site configuration");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(getData("FacilityName"));
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requireHoldReasonFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Hold");
		test.transactionQueueActions.seeValuesFromHoldDropDown("holdReasonId");
	}
	
}
