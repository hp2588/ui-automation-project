package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Feature_DestinationClass extends BaseTest  {

	List<String> actualUserNames;
	List<String> expectedUserNames;
	String facilitydropdown,searched_item,item_detail,facility,destination_name1;
	String destinationName, facility_Name, destinationCode,accountNo,DistributorAccount_old,DistributorAccount_new,accountNo_new;;
	
	

	
			
			/*
		
		
	
	@Test(priority = 1, description = "VPLX:Manage Destinations - General:[UI] User is able to search and filter out the destination that is mapped with any Facility")
	public void Test01_1129832(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations - General:[UI] User is able to search and filter out the destination that is mapped with any Facility");
	
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		
		/*
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		facility_Name = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("costCenterCode",
				"CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(destinationName);
		*/
	//	test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
	
	
	/*
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		 test.supportDataActions.verifyRecordList();
		 test.siteConfigurationAction.enterSearchTermInSearchField("Destination15875485953531", "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults("Destination15875485953531", "1"));
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName("Destination15875485953531");
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("costCenterCode",
				"CC" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("Facility.ValidEmail"));
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1676");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-1676");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-1676", "45671");
		
		
		
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		//searched_item = test.siteConfigurationAction.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem1"));
		searched_item = test.siteConfigurationAction.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem1"));
		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();

		test.siteConfigurationAction.getSearchedItemDetails();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");
		
		
		test.siteConfigurationAction.clickTab(getData("DestinationTab.Users"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", true);
		
		test.siteConfigurationAction.clickCheckboxForItemUserstab();
		
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(getData("Destination_ItemsTab.DestinationName"), "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults("Destination15875485953531", "1"));
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName("Destination15875485953531");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("costCenterCode"));
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-1676");
		
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyItemIsPresentonDestinationUsersTab("ItemID1587551161556");
		
		test.siteConfigurationAction.clickTab(getData("DestinationTab.Users"));
		test.siteConfigurationAction.verifyCheckboxIsCheckedDestinationUsersTab("activeFlag");
		
	}
	
	
	
	/*
	
		@Test(priority = 1, description = "VPLX:Manage Destinations - General:[UI] User is able to search and filter out the destination that is mapped with any Facility")
	public void Test01_1129831_AND_1129833(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations - General:[UI] User is able to search and filter out the destination that is mapped with any Facility");
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		
		//Assert.assertTrue(test.siteConfigurationAction.verifyFacilitydropdownonDestinationPage());
		//facility=test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", getData("AddISA.FacilityName"));
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		 test.supportDataActions.verifyRecordList();
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		facility_Name = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("costCenterCode",
				"CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyDestinationPopupForClosingProcessOnClickingCancelButton(
				"Are you sure you want to cancel this process?");
		 test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(destinationName);
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(destinationName, "1"));

	}

	@Test(priority = 2, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User is able to see Distributor Accounts tab is enabled after entering mandatory fields under General tab while searching distributor accounts.")
	public void Test02_1048045(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-Distributor Accounts:[UI]:User is able to see Distributor Accounts tab is enabled after entering mandatory fields under General tab while searching distributor accounts.");
		test.supportDataActions.clearSearchBox("search");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.supportDataActions.selectValueFromDropdownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
					"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
					"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag",true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
					"costCenterCode", "CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts"));
			
		}
	
	@Test(priority = 3, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Account No.")
	public void Test03_1048541(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]: User verifies the list of distributor accounts by searching Account No.");
		/*
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(destinationName, "1"));
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		
		*/
		
	
	/*
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		
			test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-12");
			test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-12");
			test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-12", "123456789");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		accountNo=test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor("123456789", "vendorAccountNumberText");
	
	test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor("123456789","search");
	test.supportDataActions.verifyNewlyAddedRecordNameInList("123456789");
	
		
}
	@Test(priority = 5, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Distributor name.")
	public void Test05_1048538(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Distributor name.");
	
		//test.siteConfigurationAction.clickActiveToggle("Show All");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		DistributorAccount_old = test.siteConfigurationAction.getFirstRecordOfColumn("2");
		System.out.println("DistributorAccount_old :"+ DistributorAccount_old);
		test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor(DistributorAccount_old,"search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(DistributorAccount_old);
		
		
		
		//test.siteConfigurationAction.clickSaveButton();
		
}
	@Test(priority = 6, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify note is visible only when either one or  both  of the checkboxes 'Enable Split Orders' or 'Enable Receive-N-Send' is checked")
	public void Test6_1055240(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Verify note is visible only when either one or  both  of the checkboxes 'Enable Split Orders' or 'Enable Receive-N-Send' is checked");
		//test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		//test.siteConfigurationAction.selectFacilityDropdownForDestination("Fac1586166951259");
		//String fac=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndexForDestination(1);
		//System.out.println("Facility name "+fac);
		//test.siteConfigurationAction.selectValueFromDropdownByIndexForDestination(1);
		//destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
			//	"descriptionText", "Destination" + System.currentTimeMillis());
		//destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
			//	"destinationCode", "Code" + System.currentTimeMillis());
		//test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1674");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-1674");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-1674", "8899");
	test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
	accountNo=test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor("8899", "vendorAccountNumberText");
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyShowItemsField();
	}
			
	@Test(priority = 7, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button")
	public void Test7_1055183(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem1"));

		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();

		test.siteConfigurationAction.getSearchedItemDetails();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");

		item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(0);
		Assert.assertTrue(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, searched_item)); 
	}
		
		@Test(priority = 8, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Control will remain on the search window after clicking on the Save and More button")
	public void Test8_1055228(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Formulary Items : [UI] Verify Control will remain on the search window after clicking on the Save and More button");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem2"));
		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.selectCheckboxForItem();
		test.siteConfigurationAction.getSearchedItemDetails();
		test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Add");
		test.siteConfigurationAction.verifyAddItemPopup();

	}
		
		@Test(priority = 9, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify Items can be removed after clicking on the Remove button for the  respective Formulary")
		public void Test9_1055232(Method method) {
			ExtentTestManager.startTest(method.getName(),
					"VPLX: Manage Destinations - Formulary Items : [UI] Verify Items can be removed after clicking on the Remove button for the  respective Formulary");
			
			searched_item = test.siteConfigurationAction
					.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem3"));

			test.siteConfigurationAction.verifyItemSearchResult();
			test.siteConfigurationAction.selectCheckboxForItem();
			test.siteConfigurationAction.clickCancelButtonOnItemPopup();
			//item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(1);
			//Assert.assertFalse(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, searched_item));
			
			test.siteConfigurationAction.enterLimitOrderQtyValue(getData("Destination_ItemsTab.maxordervalue"));
			test.siteConfigurationAction.verifyAndClickRemoveButton();
			//Assert.assertFalse(test.siteConfigurationAction.verifyAddedItemOnDestinationPage2(item_detail, "Calpol"));
			test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
			test.siteConfigurationAction.verifyAddItemPopup();
			searched_item = test.siteConfigurationAction
					.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem3"));
			test.siteConfigurationAction.verifyItemSearchResult();
			test.siteConfigurationAction.selectCheckboxForItem();
			test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");
			item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(0);
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(searched_item);
			
			
			
			
			
			/*
			
			searched_item = test.siteConfigurationAction
					.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem2"));
			test.siteConfigurationAction.verifyItemSearchResult();
			test.siteConfigurationAction.selectCheckboxForItem();
			test.siteConfigurationAction.getSearchedItemDetails();
			test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");
			test.siteConfigurationAction.enterLimitOrderQtyValue(getData("Destination_ItemsTab.maxordervalue"));
			test.siteConfigurationAction.verifyAndClickRemoveButton();
			
			test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
			test.siteConfigurationAction.verifyAddItemPopup();
			searched_item = test.siteConfigurationAction
					.enterItemNameForDestinationItem(getData("Destination_ItemsTab.SearchItem3"));
			test.siteConfigurationAction.verifyItemSearchResult();
			test.siteConfigurationAction.selectCheckboxForItem();
			test.siteConfigurationAction.clickSaveCloseORCancelORSaveAddButton("save_btn", "Save & Close");
			item_detail = test.siteConfigurationAction.getItemDetailsonItemsTab(0);
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(searched_item);
		*/

		
		

/*
		@Test(priority = 10, description = "VPLX:Manage Destinations-Users:[UI]:User verifies the Available Users headline while assigning users to the destination.")
		public void Test10_1058633(Method method) {
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Manage Destinations-Users:[UI]:User verifies the Available Users headline while assigning users to the destination.");
			
		test.siteConfigurationAction.clickTab(getData("DestinationTab.Users"));
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show All").contains("false"));
		Assert.assertTrue(test.siteConfigurationAction.verifyLabelHeaderOnUserTab("Available Users"));
	}
		
		@Test(priority = 11, description = "VPLX:Manage Destinations-Users:[UI]: Verify the list of active users when Show All toggle is off.")
		public void Test11_1058995(Method method) {
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Manage Destinations-Users:[UI]: Verify the list of active users when Show All toggle is off.");
			test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", true);
			test.siteConfigurationAction.selectCheckboxCorresspondingToName(expectedUserNames.get(0), true);
			test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show All", false);
			actualUserNames = test.siteConfigurationAction.getListOfAvailableUsers();
			Assert.assertTrue(actualUserNames.size() == 1);
			Assert.assertEquals(actualUserNames.get(0), expectedUserNames.get(0));
		}
		*/
	@Test(priority = 1, description = "VPLX:Manage Destinations-General:[UI]: Contact screen after making Edit changes.")
	public void Test01_1045922(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]: Contact screen after making Edit changes.");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		facility_Name = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("costCenterCode",
				"CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("Facility.ValidEmail"));
		test.siteConfigurationAction.clickSaveButton();
		
		 test.siteConfigurationAction.enterSearchTermInSearchField("destinationName", "search");
			Assert.assertTrue(test.siteConfigurationAction.verifySearchResults("destinationName", "1"));
			test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName("destinationName");
			
		
			test.siteConfigurationAction.clickTab("Contact");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("streetAddressText");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("streetAddressText"),
					"[ASSERTION FAILED]: Input Field streetAddressText is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText"), 120,
					"[ASSERTION FAILED]: Max Length for input field streetAddressText is not 120");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("streetAddressText"),
					"[ASSERTION FAILED]: input field Street Address is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("cityName");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("cityName"),
					"[ASSERTION FAILED]: Input Field cityName is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName"), 50,
					"[ASSERTION FAILED]: Max Length for input field cityName is not 50");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("cityName"),
					"[ASSERTION FAILED]: input field City Name is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("postalCode");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("postalCode"),
					"[ASSERTION FAILED]: Input Field  zip Code is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode"), 20,
					"[ASSERTION FAILED]: Max Length for input field zip Code is not 20");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("postalCode"),
					"[ASSERTION FAILED]: input field Zip Code is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("countryName");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("countryName"),
					"[ASSERTION FAILED]: Input Field countryName is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName"), 50,
					"[ASSERTION FAILED]: Max Length for input field countryName is not 50");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("countryName"),
					"[ASSERTION FAILED]: input field Country Name is mandatory");
		
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("stateName");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("stateName"),
					"[ASSERTION FAILED]: Input Field stateName is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName"), 50,
					"[ASSERTION FAILED]: Max Length for input field stateName is not 50");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("stateName"),
					"[ASSERTION FAILED]: input field State Name is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactEmailAddressValue"),
					"[ASSERTION FAILED]: Input Field Email is not blank by default");
			Assert.assertEquals(
					test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue"), 50,
					"[ASSERTION FAILED]: Max Length for input field Email is not 50");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"),
					"[ASSERTION FAILED]: input field Email is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactPhoneNumberText");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactPhoneNumberText"),
					"[ASSERTION FAILED]: Input Field Phone Number is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactPhoneNumberText"),
					15, "[ASSERTION FAILED]: Max Length for input field Phone Number is not 10");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactPhoneNumberText"),
					"[ASSERTION FAILED]: input field Phone Number is mandatory");
			
			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactFaxNumberText");
			Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactFaxNumberText"),
					"[ASSERTION FAILED]: Input Field Fax is not blank by default");
			Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactFaxNumberText"),
					15, "[ASSERTION FAILED]: Max Length for input field Fax is not 10");
			Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactFaxNumberText"),
					"[ASSERTION FAILED]: input field Fax Number is mandatory");
			
			
			
			test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
					getData("InvalidEmail.invalid1"));
			Assert.assertTrue(test.siteConfigurationAction
					.verifyEmailFormatValidationMessageAppearOrNot(getData("ErrorMessage.Email")));
			
			test.siteConfigurationAction.verifyErrorMessageForPhoneNumberAndFaxFieldIsDisplayedTillThe10thNumberIsEntered(
					"vendorContactPhoneNumberText", "phone number");
			
			test.siteConfigurationAction.verifyErrorMessageForPhoneNumberAndFaxFieldIsDisplayedTillThe10thNumberIsEntered(
					"vendorContactFaxNumberText", "fax number");
	
	
	}



}



