package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_985353 extends BaseTest {
	
	ArrayList<String> list = new ArrayList<String>(Arrays.asList("PYXISLOAD", "STAT", "CRITLOW", "PATIENTPICK"));
	ArrayList<String> list2 = new ArrayList<String>(Arrays.asList("STAT Order", 
			"Multi-Part STAT", "Manual STAT Order", "STAT Redispense"));
	List<String> gridViewData = new ArrayList<String>();
	List<String> listViewData = new ArrayList<String>();
	String ISAName;
	
	Integer NumberOfISA;
	String isaShortName;
	String facilityName;
	List<String> transactionPriorities = null;
	ArrayList<ArrayList<String>> prioritiesOnISACards = null;
	String isaName1, shortName1;
	String isaName2, shortName2, deviceNumber2, ipAddress2, portNumber2; 
	String computerName, computerIPaddress;
	String computerName2, computerIPaddress2;
	
	
	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]: The Transaction Count for both "
			+ "Pending and Active Transaction is displayed on the basis of top four Transaction priorities"
			+ "\n&\n"
			+ "VPLX: Select ISA Screen: [UI] Maximum 4 Transaction priorities are visible on the "
			+"ISA card according to priority order") 
	public void Test01_Test02_1017210_1124538(Method method) {
		facilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityName);
		transactionPriorities = test.siteConfigurationAction.getRecordListOfTransactionPriorities();
		System.out.println("Priorities for the facility " + facilityName + ": " + transactionPriorities);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		NumberOfISA = test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage();
		Assert.assertNotEquals(NumberOfISA, 0);
		
		prioritiesOnISACards = test.storageAreaAction.getTheVisiblePrioritiesFromGridViewForAllAvailableISA(NumberOfISA);
		
		for(ArrayList<String> priorityList: prioritiesOnISACards) {
			for(String priority: priorityList) {
				System.out.print(priority + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i< prioritiesOnISACards.size(); i++) {
			Assert.assertTrue(prioritiesOnISACards.get(i).size() <= 4, 
					"[ASSERTION FAILED]: More than four priorities visible on ISA Card");
			
			if(prioritiesOnISACards.get(i).size() == 0) {
				test.storageAreaAction.verifyNoPicKErrorMessage(i+1, "No Transaction");
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
			}
		}
		
		// test.storageAreaAction.clickButton("Cancel");
	}
	
	
	@Test(priority = 2, description = "VPLX:Select ISAs:Start Work button gets disabled "
			+ "when checkboxes for all available ISAs get unchecked")
	public void Test03_1017393(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:Start Work button gets disabled when checkboxes for all available ISAs get unchecked");
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		Assert.assertFalse(test.storageAreaAction.isButtonWithTextEnabled("Start Work"), 
				"[ASSERTION FAILED]: Start Work did not become disable when user unchecks checkbox "
				+ "for all available ISA");
	}
	
	
	@Test(priority = 3, description = "VPLX: Select ISAs: [UI]: To verify that the Transaction count "
			+ "is accurate for the transactions priorities in both Grid and List views")
	public void Test04_1019205(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:The Transaction count is accurate for the transactions in both Grid and List views	");
		
		gridViewData = test.storageAreaAction.getTheTopFourPriorityListValueFromGridViewForAllAvailableISA(
				list2, NumberOfISA);
		System.out.println(gridViewData);
		test.storageAreaAction.switchToListViewOfISA();
		listViewData = test.storageAreaAction.verifyTransactionCountForTransactionIsMatchingInGridAndListView(list2,
				NumberOfISA);
		System.out.println(listViewData);
		Assert.assertEquals(gridViewData, listViewData,
				"[ASSERTION FAILED]: Transaction count in grid view : " + gridViewData
				+ "\nTransaction count in list view :" + listViewData);
		test.storageAreaAction.switchToGridViewOfISA();
	}
	
	
	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]: To verify that the Multiple Checkboxes "
			+ "can be selected for the available ISAs")
	public void Test05_1008419(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs: Multiple Checkboxes can be selected for the available carousels");
		test.storageAreaAction.switchToGridViewOfISA();
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")),
				"[ASSERTION FAILED] : Checkbox of ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName")
				+ " is not selected");
		
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName2"), 1);
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName2")),
				"[ASSERTION FAILED] : Checkbox of ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName2")
				+ " is not selected");
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}
	
	
	@Test(priority = 5, description = "VPLX:Select ISAs:The Select ISA's button "
			+ "redirects the user to Select ISA screen")
	public void Test06_1017206(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:The Choose ISA's button redirects the user to Select ISA screen");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item6"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage());
		Assert.assertTrue(
				test.storageAreaAction.verifyFacilityNameOnStorageAreaPage().contains(
						TestDataPropertyReaderAndWriter.getProperty("FacilityName")));
		Assert.assertEquals(Integer.valueOf(test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage()), 
				NumberOfISA);
	}
	
	
	@Test(priority = 6, description = "VPLX: Select ISAs: [UI]:To verify that If the ISA "
			+ "doesn't have any Computer and printer then its available for taking control "
			+ "and printer is not displaying on Transaction queue screen"
			+ "\n&\n"
			+ "VPLX: Select ISAs: [UI]:To verify that the User is able to "
			+ "navigate to Transaction Queue screen after selecting atleast one isa." )
	public void Test07_Test08_1153221_1152603(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(), 
				"VPLX: Select ISAs: [UI]:If the ISA doesn't have any Computer and printer then its available "
				+ "for taking control and printer is not displaying on WFA screen"
				+ "\n&\n"
				+ "VPLX: Select ISAs: [UI]:To verify that the User is able to "
				+ "navigate to Transaction Queue screen after selecting atleast one isa.");
		
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5")));
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName5"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5")));
		
		// verifying default printer
		Assert.assertEquals(test.storageAreaAction.verifyMappedPrinterCorresspondingToISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5")), "Select",
				"[ASSERTION FAILED] : Default selected option in printer dropdown for ISA " 
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName5") + "is not 'Select'");
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName5"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5")));
		
		Assert.assertEquals(test.storageAreaAction.selectPrinterUsingKeyboard(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5"), "P"), 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"),
				"[ASSERTION FAILED]: Printer for ISA "
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName5") + " is not selected");
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		
	}
	
	// TODO - Yugal- two TCs are contradictory
	@Test(priority = 7, description = "VPLX: Select ISAs: [UI]: If computer takes the control of ISA "
			+ "and clicks on Start work then on coming back from TQ screen the ISA is displaying "
			+ "selected and available or Grayed Out as they are displaying initally before taking control"
			+ "\n&\n"
			+ "VPLX: Select ISAs: [UI]: To verify that the Checkbox for last selected ISAs is selected "
			+ "when the user goes back to Select ISA again" )
	public void Test09_Test10_1153869_1018270(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(), 
				"VPLX: Select ISAs: [UI]: If computer takes the control of ISA and clicks on Start work then on coming back from "
				+ "TQ screen the ISA is displaying selected and available or Grayed Out as they are displaying initally before taking control");
		
		if(test.storageAreaAction.isButtonWithTextDisplayed("Cancel")) {
			test.storageAreaAction.clickButton("Cancel");
		}
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		
		NumberOfISA = test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage();
		
		boolean[] checkboxValues = new boolean[NumberOfISA];
		for(int i = 0; i < NumberOfISA; i++) {
			checkboxValues[i] = test.storageAreaAction.isCheckboxSelected(i);
		}
		
		String[] backgroundColorOfISACards =  new String[NumberOfISA];
		for(int i = 0; i < NumberOfISA; i++) {
			backgroundColorOfISACards[i] = test.storageAreaAction.getISACardBackgroundColor(i);
			System.out.println("ISA " + String.valueOf(i+1) + " background color: " + backgroundColorOfISACards[i]);
		}
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item6"));
		
		for(int i = 0; i < NumberOfISA; i++) {
			Assert.assertEquals(test.storageAreaAction.isCheckboxSelected(i), checkboxValues[i]);
		}
		for(int i = 0; i < NumberOfISA; i++) {
			Assert.assertEquals(test.storageAreaAction.getISACardBackgroundColor(i), backgroundColorOfISACards[i]);
		}
		
		// test.storageAreaAction.clickButton("Cancel");
	}
	
	
	@Test(priority = 7, description = "VPLX:Select ISAs:The ISA gets selected for Manual Use "
			+ "when Manual Use button bar is slided over")
	public void Test11_1016930(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:The ISA gets selected for Manual Use when Manual Use button bar is slided over");
		
		if(test.storageAreaAction.isButtonWithTextDisplayed("Cancel")) {
			test.storageAreaAction.clickButton("Cancel");
		}
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress2"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName3"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));	
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}
	
	
	@Test(priority = 8, description = "VPLX: Select ISAs: [UI] : If any ISA doesn't have any "
			+ "default or approved computer (For carousel) the carousel ISA is available for taking control "
			+ "to any computer mapped to Facility which can control carousel ISA")
	public void Test12_1153865(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(), 
				"VPLX: Select ISAs: [UI] : If any ISA doesn't have any default or approved computer (For carousel) "
				+ "the carousel ISA is available for taking control to any computer mapped to Facility which can control carousel ISA");
		
		if(test.storageAreaAction.isButtonWithTextDisplayed("Cancel")) {
			test.storageAreaAction.clickButton("Cancel");
		}
		test.landingPageActions.navigateToMenu("Main Menu");
		test.siteConfigurationAction.pageRefresh();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(
				TestDataPropertyReaderAndWriter.getProperty("IPAddress3"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")));
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName4"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")));
		
		test.storageAreaAction.verifyManualUseOptionForNonStaticISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")),
				"[ASSERTION FAILED]: Manual use option is not selected for carousel ISA " 
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName4"));
		
		// verifying default printer
		Assert.assertEquals(test.storageAreaAction.verifyMappedPrinterCorresspondingToISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")), "Select",
				"[ASSERTION FAILED] : Default selected option in printer dropdown for ISA " 
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName4") + " is not 'Select'");
		
		Assert.assertEquals(test.storageAreaAction.selectPrinterUsingKeyboard(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4"), "P"), 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"),
				"[ASSERTION FAILED]: Printer for ISA "
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName4") + " is not selected");
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}
	
	@Test(priority = 9, description = "VPLX: Select ISAs: [UI]:The static ISA(having Approved Computer) "
			+ "is greyed out when logged in from non-Approved Computer")
	public void Test13_1153192(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(), 
				"VPLX: Select ISAs: [UI]:The static ISA(having Approved Computer) is greyed out when logged in from non-Approved Computer");
		
		if(test.storageAreaAction.isButtonWithTextDisplayed("Cancel")) {
			test.storageAreaAction.clickButton("Cancel");
		}
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName"));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		
		// verifying that background color of first ISA card(static) is grey 
		// when logged into TQ via non-approved computer
		String bgColor = test.storageAreaAction.getISACardBackgroundColor(0);
		System.out.print("Background color of ISA card 0 - " + bgColor);
		Assert.assertEquals(bgColor, "rgba(204, 204, 204, 1)");
	}
	
	
	@Test(priority = 10, description = "VPLX: Select ISAs: [UI]:To verify that the carousel ISA(having default "
			+ "computer) are available to take control and not selected when logged in from non-default computer "
			+ "(for which computer can take control of carousel is selected)")
	public void Test14_1153197(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(), 
				"VPLX: Select ISAs: [UI]:The carousel ISA(having default computer) are available to take control "
				+ "and not selected when logged in from non-default computer (for which computer can take control of carousel is selected)");
		
		// verifying that the ISA card with ShortName3 is not selected by default 
		// and can be selected by clicking checkbox 
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName3"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		
		// verifying default printer
		Assert.assertEquals(test.storageAreaAction.verifyMappedPrinterCorresspondingToISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")), 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim(),
				"[ASSERTION FAILED] : Default selected option in printer dropdown for ISA " 
				+ TestDataPropertyReaderAndWriter.getProperty("ShortName3")+ "is not '" 
				+ TestDataPropertyReaderAndWriter.getProperty("PrinterName") + "'");
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		
		test.landingPageActions.navigateToMenu("Menu");
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(
				TestDataPropertyReaderAndWriter.getProperty("IPAddress2"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		test.storageAreaAction.clickButton("Cancel");
	}
	
}
