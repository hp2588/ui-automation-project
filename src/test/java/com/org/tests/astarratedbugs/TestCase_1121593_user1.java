package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;
public class TestCase_1121593_user1 extends BaseTest {
	
	String facilityOnWFAScreen,ISAName,firstname,itemName,transaction_type;
	
	@Test(priority = 1, description = "VPLX : Tranqueue withstand multiple workstations to to process a transaction.")
	public void Test01_1018270(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Tranqueue withstand multiple workstations to to process a transaction.");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
		ISAName=test.storageAreaAction.getISANameOnWFAScreen();

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		/*======================Add Manual Pick Transaction=================================*/
		
		
		test.transactionQueueActions.verifyAndClickAddPick();
		
		test.transactionQueueActions.searchItemValue("ItemID");
		//test.transactionQueueActions.searchItemValue(itemID);
		//test.transactionQueueActions.verifySearchedResult("Item Name", itemID);
		test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		
		itemName=test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "6");
		transaction_type=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("priority", 1);
				
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination",
				1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		//Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		String[] itemName_parsed=itemName.split(" ");
		String ItemNameNew=itemName_parsed[0];
		System.out.println("ItemNameNew=" +ItemNameNew);
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(transaction_type, ItemNameNew);
	
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	}


}
