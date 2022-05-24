package com.org.tests.selectisascreen;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class SelectISA_New_Tests extends BaseTest {
	
	String ISAName;
	Integer NumberOfISA;
	String facilityName;
	ArrayList<ArrayList<String>> prioritiesOnISACards;
	
	@Test(priority=1, description = "VPLX: Select ISAs: [UI]: My Last Selected button is "
			+ "displayed on Select ISA screen")
	public void Tes01_1019614(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				 "VPLX: Select ISAs: [UI]: My Last Selected button is displayed on Select ISA screen");
	
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(
				TestDataPropertyReaderAndWriter.getProperty("IPAddress"));
		Assert.assertTrue(
				test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\""
		);
		facilityName = test.siteConfigurationAction.getFacilityFromISAScreen();
		Assert.assertNotNull(
				facilityName.isEmpty(),
				"[ASSERTION FAILED]: No facility found on ISA pop-up"
		);
		NumberOfISA = test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage();
		Assert.assertNotEquals(
				NumberOfISA ,0
		);
		
		test.storageAreaAction.verifyButtonIsDisplayed("My Last Selected");
		
	}
	
	@Test(priority=2, description="VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same"
			+ " when user clicks on the same view button,which is currently being displayed on Select ISA screen")
	public void Test02_1032666(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				"VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same when user "
				+ "clicks on the same view button,which is currently being displayed on Select ISA screen");
		
		// by default, grid view is selected
		// click again on grid view button and make sure it remains in focus
		test.storageAreaAction.switchToGridViewOfISA();
		// make sure ISA Cards are visible
		Assert.assertNotEquals(
				test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(),0
		);
		
		// switch to list view
		test.storageAreaAction.switchToListViewOfISA();
		// make sure ISA row is there
		Assert.assertNotEquals(
				test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0
		);
		// click again on list view button and make sure it remains in focus
		test.storageAreaAction.switchToListViewOfISA();
		Assert.assertNotEquals(
				test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0
		);
		// switch back to grid view
//		test.storageAreaAction.switchToGridViewOfISA();
	}
	
	@Test(priority=3, description="VPLX: Select ISA Screen: [UI] Maximum 4 Transaction priorities are visible on the "
			+"ISA card according to priority order")
	public void Test03_1124538(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				"nVPLX: Select ISA Screen: [UI] Maximum 4 Transaction priorities are visible on the "
				+ "ISA card according to priority order");
		// TODO uncomment following line
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Cancel");
		// on pressing cancel goes back to Menu
//		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.supportDataActions.verifyLabelIsPresent("Priorities");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityName);
//		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", "Fac1591252906628");
		
		List<String> transactionPriorities = test.siteConfigurationAction.getRecordListOfTransactionPriorities();
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(
				test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\""
		);
		
		
		prioritiesOnISACards = 
				test.storageAreaAction.getTheVisiblePrioritiesFromGridViewForAllAvailableISA(NumberOfISA);
		
		for(ArrayList<String> priorityList: prioritiesOnISACards) {
			for(String priority: priorityList) {
				System.out.print(priority + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i< prioritiesOnISACards.size(); i++) {
			Assert.assertTrue(prioritiesOnISACards.get(i).size() <= 4, 
					"[ASSERTION FAILED]: More than four priorities visible on ISA Card");
			
//			logMessage("[ASSERTION PASSED]:  Maximum 4 priorities visible on ISA cards");
			
			if(prioritiesOnISACards.get(i).size() == 0) {
				test.storageAreaAction.verifyNoPicKErrorMessage(i+1, "No Pick Transaction");
			} else {
				// match priority order
				int tempIdx = 0;
				for(int j = 0; i < transactionPriorities.size(); j++) {
					if(tempIdx == prioritiesOnISACards.get(i).size())
						break;
					if(prioritiesOnISACards.get(i).get(tempIdx).contains(transactionPriorities.get(j)))
						tempIdx++;
				}
				
				Assert.assertEquals(prioritiesOnISACards.get(i).size(), tempIdx,
						"[ASSERTION FAILED]: Priorities on ISA card are not in order");
//				logMessage("[ASSERTION PASSED]: Priorities on ISA cards are in order");
			}
		}
		
	}
	
	@Test(priority=4, description="The Transaction Queue opens for the selected ISA when user goes back to "
			+ "Select ISA screen from Transaction Queue")
	public void Test04_1019187(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				 "The Transaction Queue opens for the selected ISA when user goes back to "
				+ "Select ISA screen from Transaction Queue");
		ISAName = test.storageAreaAction.getISANameOnWFAScreen();
		System.out.println("ISA name: " + ISAName);
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(ISAName, 0);
		
		test.storageAreaAction.clickOnStartWorkButton();	
		
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTQPageNew());
		
		test.transactionQueueActions.verifyISANameOnTQ(ISAName);
		
//		 Picks, Restocks, Locked Items, On Hold
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item6"));
		
		Assert.assertTrue(
				test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\""
		);
		Assert.assertEquals(
				test.siteConfigurationAction.getFacilityFromISAScreen(),
//				"Fac1591252906628"
				 facilityName 
		);
		
//		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
//		test.storageAreaAction.wait
		Assert.assertNotEquals(
				test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(),0
		);
		
//		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		
		ISAName = test.storageAreaAction.getISANameOnWFAScreen();
		System.out.println("ISA name: " + ISAName);
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(ISAName, 0);
		
		test.storageAreaAction.clickOnStartWorkButton();	
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTQPageNew());
		test.transactionQueueActions.verifyISANameOnTQ(ISAName);
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
	}
	

}

