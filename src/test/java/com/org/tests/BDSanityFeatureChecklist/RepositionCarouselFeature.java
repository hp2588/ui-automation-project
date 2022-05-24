package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;

public class RepositionCarouselFeature extends BaseTest {

	@Test(priority = 1,enabled = true, description = "Verify that the Reposition Carousel button is displayed on the transaction queue header anytime a Pick, Restock, or Return transactions is active.")
	public void Test01_1017111(){
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		/*test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);*/
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifybuttonisDisplayed("Reposition Carousel");
		
	}
	
	@Test(priority = 2,enabled = true, description = "Verify that Reposition Carousel button is disabled if there is no active transaction.")
	public void Test02_1017109(){
		
		test.siteConfigurationAction.verifyButtonIsDisabled("2");
	}
	
	@Test(priority = 3,enabled = true, description = "Verify that the Reposition Carousel button is disabled if the item is not located in a carousel ISA.")
	public void Test03_1015776(){
		
		test.siteConfigurationAction.verifyButtonIsDisabled("2");
	}
	
	@Test(priority = 4,enabled = true, description = "Verify that the user is able to click the Reposition Carousel button.")
	public void Test04_1017107(){
	
	
	test.transactionQueueActions.verifybuttonisDisplayed("Reposition Carousel");
	}
}
