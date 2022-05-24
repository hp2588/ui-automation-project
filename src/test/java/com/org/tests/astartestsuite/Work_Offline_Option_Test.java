package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Work_Offline_Option_Test extends BaseTest{

	@Test(priority = 1, description = "To verify user has the ability to select Work Offline when processing transactions against an ISA (Individual Storage Area)")
	public void Test01_1114480(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"To verify user has the ability to select Work Offline when processing transactions against an ISA (Individual Storage Area)");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertEquals(test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage() + "",
				getData("NumberOfISA"));
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.verifyUserIsAbleToSelectCheckboxesForAvailableISAs();
		
		test.storageAreaAction.verifyManualUseOptionForNonStaticISA(getData("ISAList.ISA5"));
		
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA5"), 4);
		test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(getData("ISAList.ISA5"));
	}
	
	@Test(priority = 2, description = "To verify that the Global View displays current pending transaction counts across all ISAs; grouped by ISA and Transaction Priority.")
	public void Test02_1114489(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"To verify that the Global View displays current pending transaction counts across all ISAs; grouped by ISA and Transaction Priority.");
		test.storageAreaAction
				.verifyTransactionCountForTopFourTransactionPrioritiesForEachAvailableISA(1);
	}
}
