package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class ForcedCycleCount extends BaseTest{
	

	String facilityName,brandName,min,clickedDate,facilityOnWFAScreen,ISAName,genericName,itemid;
	String [] genericname=new String[3];
	String [] itemID=new String[3];	
	String facility, facilityOnISA, name1, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime, app_url, resetInterval, name2;	
	int i;

	@Test(priority=1, description= "VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level")
	public void Test01_1067791(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level");
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		name1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"ISA" + System.currentTimeMillis());
		
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName_ForcedCC").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName_ForcedCC").trim());
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName_ForcedCC"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName_ForcedCC"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.clickSaveButton();
		
		
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();		
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();
	
		
	}
	

	@Test(priority=2, description="VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Facility Item Setup screen for active items.")
	public void Test02_1131153(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Facility Item Setup screen for active items.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericName=	test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemid= test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","2");
		test.siteConfigurationAction.clickButton("save");
	
	}
	

	@Test(priority=3, description="VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Location level.")
	public void Test03_1131156(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at Location level.");
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(genericName,	"search");
		test.purchaseDashboardActions.clickOnEditLinkCorresspondingToItem(genericName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		test.siteConfigurationAction.selectValueForDropDown("isa",name1);
	//	test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
		
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();

		
		
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
//		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBoxSanity(genericName),
				"\"[ASSERTION FAILED]: Cycle Count Transaction not found");
	
		
	}


	@Test(priority=4, description="VPLX:Forced Cycle Count:[UI]: Cycle count interval at item level is used if not set at location level.")
	public void Test04_1131157(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval at item level is used if not set at location level.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		for (i=0;i<3;i++)
		{
	    test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
   
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericname[i]= test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		System.out.println("Generic name: "+genericname[i]);
		itemID[i] = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

        
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","2");
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));			
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(genericname[i],"search");
		test.purchaseDashboardActions.clickOnEditLinkCorresspondingToItem(genericname[i]);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		
		
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC").trim());	
		
		
		 test.siteConfigurationAction.selectValueForDropDown("isa", name1);


	   
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		}
		
		test.landingPageActions.navigateToMenu("Main Menu");

		test.landingPageActions.navigateToMenu("Transaction Queue");
	//	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
	//	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		for (i=0;i<3;i++)
		{
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname[i]),
				"\"[ASSERTION FAILED]: Cycle Count Transaction not found");
		}
	}
	
	
	@Test(priority=5, description="VPLX:Forced Cycle Count:[UI]:User is able to hold the transaction.")
	public void Test05_1067792(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:User is able to hold the transaction.");
		
		test.transactionQueueActions.verifyTransaction(genericname[0].trim());
		test.transactionQueueActions.clickHoldButton_Sanity();
		
   //     test.transactionQueueActions.clickHoldButtonForCycleCount(genericname[0]);
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	//	test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		
		
	}
	

	
	@Test(priority=6, description="VPLX:Forced Cycle Count:[UI]:User is able to release the transaction.")
	public void Test06_1065404(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:User is able to release the transaction.");
		
		test.transactionQueueActions.verifyTransaction(genericname[0].trim());
	//	test.transactionQueueActions.selectRestockTransaction_Sanity(genericname[0]);
	//	test.transactionQueueActions.clickRelease_Sanity(genericname[0]);
		test.transactionQueueActions.releaseCycleCountTransaction();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTransaction(genericname[0]);
		
	}
	
	@Test(priority=7, description="VPLX:Forced Cycle Count:[UI]:Creation of a Cycle Count transaction for any item location is skipped if an identical transaction already exists in the queue")
	public void Test07_1131147(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:Creation of a Cycle Count transaction for any item location is skipped if an identical transaction already exists in the queue");
		
		Assert.assertTrue(test.transactionQueueActions.verifySingleCycleCountTransactionForAnItem(genericname[0]));
	}
	
	/*
	@Test(priority=8, description="VPLX:Forced Cycle Count:[UI]: User is able to delete the transaction.")
	public void Test08_1065525(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: User is able to delete the transaction.");
		
		test.transactionQueueActions.selectRestockTransaction_Sanity(genericName);
	//	test.transactionQueueActions.clickDelete_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		
		test.transactionQueueActions.clickDelete_Sanity(genericName);
		test.transactionQueueActions.enterDeleteReason("deleteReason", "delete restock transaction");
		
	    test.transactionQueueActions.clickConfirmToDeleteButton("Confirm");
	
		
	}

	@Test(priority=9, description="VPLX:Forced Cycle Count:[UI]:Cycle count transactions are created after the Cycle Count interval expires.")
	public void Test09_1131151(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:Cycle count transactions are created after the Cycle Count interval expires.");
		test.transactionQueueActions.waitForCycleCountTransaction();
		test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname[0]);
		
	}
	
	*/
	
	@Test(priority=10, description="From selecting to deselecting\"Create Cycle Count Transactions for items with Assigned Cycle Count Interval\"Checkbox the system removes all the active cycle count transaction from the queue")
	public void Test10_1131293(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"From selecting to deselecting\"Create Cycle Count Transactions for items with Assigned Cycle Count Interval\"Checkbox the system removes all the active cycle count transaction from the queue");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"), "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", false);
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
//		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
	//	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertFalse(test.transactionQueueActions.verifyCycleCountTransactionInTQPage());
		
	}

	@Test(priority=11, description="From deselecting to selecting \"Create Cycle Count Transactions for items with Assigned Cycle Count Interval\"Checkbox the system generates cycle count transaction for all items")
	public void Test11_1131295(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"From selecting to deselecting\"Create Cycle Count Transactions for items with Assigned Cycle Count Interval\"Checkbox the system removes all the active cycle count transaction from the queue");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"), "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
		
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		//test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress_ForcedCC").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
//		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname[0]),
				"\"[ASSERTION FAILED]: Cycle Count Transaction not found");		
	}
		

	
	@Test(priority=12, description="Only reposition carousel is displayed for the active cycle count transaction.")
	public void Test12_1067811(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"Only reposition carousel is displayed for the active cycle count transaction.");
		

		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountButton("Reprint Label"));
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountButton("Waste Item"));

	}
	

	@Test(priority=15, description="VPLX: Forced Cycle Count:[UI]: User is not able to waste quantity greater than quantity on hand.")
	public void Test15_1065367(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Forced Cycle Count:[UI]: User is not able to waste quantity greater than quantity on hand.");
	//	test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname);
	//	 test.transactionQueueActions.waitForCycleCountTransaction();
		test.transactionQueueActions.clickOnPickNow_Sanity(genericname[2]);
	   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");

		test.transactionQueueActions.enterQuantityAndWasteQuantity();
	/*	test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.enterConfirmQuantity("200");
		test.transactionQueueActions.enterConfirmQuantity("200");

		test.transactionQueueActions.clickConfirmButtonQOHPopup();*/
	//	test.transactionQueueActions.verifyToastMessageOnSuccess();
		
	}
	
	@Test(priority=16, description="VPLX:Forced Cycle Count:[UI]:User is able to waste item for forced cycle count transaction.")
	public void Test16_1067785(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]:User is able to waste item for forced cycle count transaction.");
		test.transactionQueueActions.enterQuantityWasted();
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.clickConfirmButtonQOHPopup();
	}
		
	
		@Test(priority=13, description="Transaction is completed for QOH lesser than Min quantity")
		public void Test13_1136256(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"Transaction is completed for QOH lesser than Min quantity");
			
	
			
			
			
			Assert.assertTrue(test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname[0]),
					"\"[ASSERTION FAILED]: Cycle Count Transaction not found");
			test.transactionQueueActions.clickOnPickNow_Sanity(genericname[0]);
		   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");

			test.transactionQueueActions.enterQOHValueForCycleCount("30");
			test.transactionQueueActions.clickOutside();

			test.transactionQueueActions.enterConfirmQuantity("200");
			test.transactionQueueActions.clickOutside();

			test.transactionQueueActions.enterConfirmQuantity("200");
			test.transactionQueueActions.clickConfirmButtonQOHPopup();

		}
		
		
		@Test(priority=14, description="Transaction is completed for QOH greater than Max quantity")
		public void Test14_1136258(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"Transaction is completed for QOH greater than Max quantity");
			Assert.assertTrue(test.transactionQueueActions.verifyCycleCountTransactionByItemName(genericname[1]),
					"\"[ASSERTION FAILED]: Cycle Count Transaction not found");
			test.transactionQueueActions.clickOnPickNow_Sanity(genericname[1]);
		   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");

			test.transactionQueueActions.enterQOHValueForCycleCount("405");
			test.transactionQueueActions.clickOutside();
			test.transactionQueueActions.enterConfirmQuantity("200");
			test.transactionQueueActions.clickOutside();

			test.transactionQueueActions.enterConfirmQuantity("200");
			test.transactionQueueActions.clickConfirmButtonQOHPopup();

		}
		
	
		@Test(priority=17, description="Users must be able to easily select the same hour of the day for all days of the week.")
		public void Test17_1131189(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"Users must be able to easily select the same hour of the day for all days of the week.");
			test.landingPageActions.navigateToMenu("Main Menu");
			test.landingPageActions.navigateToFeature("Facilities");

			test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"), "search");
			test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName_ForcedCC"));
			
			test.siteConfigurationAction.clickTab("Cycle Counts");
			test.transactionQueueActions.selectHourForAllDays();

		}
		
		@Test(priority=18, description="User must be able to easily select or deselect the entire day all hours of any day of the the week.")
		public void Test18_1131286(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"User must be able to easily select or deselect the entire day all hours of any day of the the week.");
			test.transactionQueueActions.selectCheckBoxForDisableDay();

		}
	
		@Test(priority=19, description="User is able to remove previously specified dates when the user does not want to view the transaction.")
		public void Test19_1131292(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"User is able to remove previously specified dates when the user does not want to view the transaction.");
			test.transactionQueueActions.selectCheckBoxForDisableDay();
			test.siteConfigurationAction.clickCycleCountCalendar();
			String date = test.siteConfigurationAction.getCurrentMonth_Year();
			int day = test.siteConfigurationAction.getCurrentDate();
			test.siteConfigurationAction.ClickCurrentDate(date);
			String a = test.siteConfigurationAction.getCurrentMonth_Year();
			test.siteConfigurationAction.clickAddButton();
			clickedDate = test.siteConfigurationAction.getCurrentMonth_Year();
			Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate));
			test.siteConfigurationAction.clickRemoveButton();
			Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate));
		}
		
		@Test(priority=20, description="Users must be able to select hour of the day in each weekday for the scheduler defined for each facility.")
		public void Test20_1131183(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"Users must be able to select hour of the day in each weekday for the scheduler defined for each facility.");
			test.siteConfigurationAction.clickScheduleButton("Wednesday19_pm");
			test.siteConfigurationAction.clickScheduleButton("Saturday2_am");

		}
		/*

		@Test(priority=21, description="Separate Cycle Count transaction is created for each individual location where the item is kept")
		public void Test21_1131152(Method method)
		
		{
			ExtentTestManager.startTest(method.getName(),
					"Separate Cycle Count transaction is created for each individual location where the item is kept");
			test.landingPageActions.navigateToMenu("Main Menu");
			test.landingPageActions.navigateToFeature("Item Management");
			test.siteConfigurationAction.enterRandomValueInRichInputFieldForExternalSystem(getData("CycleCount.FacilityName"));
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericname= test.siteConfigurationAction.enterDataInInputField("genericName",
					"Systemlevelfacility" + System.currentTimeMillis());
			itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
					"SystemlevelItem" + System.currentTimeMillis());
			brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
			test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
			test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
			test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
			test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(getData("CycleCount.FacilityName"));
		
			test.siteConfigurationAction.clickButton("save");
			test.siteConfigurationAction.clickfacilityonEditItem("2");
		//	test.transactionQueueActions.clickConfirmPopupButton();

			
			test.siteConfigurationAction.enterDataInInputField("cycleCountIntervalDayAmount","2");
			test.siteConfigurationAction.clickButton("save");
			
			
			test.landingPageActions.navigateToFeature("Main Menu");
			test.landingPageActions.navigateToFeature("Item Locations");
			test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",getData("CycleCount.FacilityName"));
			test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
			
			
			test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
			test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
			
			test.siteConfigurationAction.selectValueForDropDown("facility", "API-LKO");
			test.siteConfigurationAction.selectValueForDropDown("isa", "ISA123");
			
			test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
			test.siteConfigurationAction.clickAssignLocationButton();
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		
			test.siteConfigurationAction.clickSaveButton();
			test.landingPageActions.navigateToMenu("Transaction Queue");

		}
	
	
	*/

}


