package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_985355 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Select ISAs:The last selected ISAs are displayed when the user goes back to Select ISA screen")
	public void Test01_1018270(Method method) {
		ExtentTestManager.startTest(getClass().getName() +" :: "+ method.getName(),
				"VPLX:Select ISAs:The last selected ISAs are displayed when the user goes back to Select ISA screen");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item6"));
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")),
				"[ASSERTION FAILED] : The last selected ISAs are not displayed when the user goes back to select ISA screen");
	}
	
}
