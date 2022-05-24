package com.org.tests.managedestinations.distributoraccounts;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1043832 extends BaseTest {
	
	String destinationName,destinationCode, searchedText,DistributorAccount_old,DistributorAccount_new,accountNo,accountNo_new;

	@Test(priority = 1, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User is able to see Distributor Accounts tab is enabled after entering mandatory fields under General tab while searching distributor accounts.")
	public void Test01_1048045(Method method) {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User is able to see Distributor Accounts tab is enabled after entering mandatory fields under General tab while searching distributor accounts.");
		test.landingPageActions.navigateToFeature("Destinations");
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
	
	@Test(priority = 2, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the Distributor accounts title while adding distributor account information.")
	public void Test02_1048572(Method method) {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the Distributor accounts title while adding distributor account information.");
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
	}
	
	@Test(priority = 3, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the Show All toggle while searching distributor accounts on Add Destination screen.")
	public void Test03_1048050(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the Show All toggle while searching distributor accounts on Add Destination screen.");
		
	    //test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		//test.supportDataActions.clickToggleButton("true", "toggle");
		
		}
	
	 
	//  Bug 1095493
	@Test(priority = 4, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User Switch off the Show All toggle so Pop up message is displayed while no distributor is active.")
	public void Test04_1048548(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User Switch off the Show All toggle so Pop up message is displayed while no distributor is active.");
	
		
		test.siteConfigurationAction.clickActiveToggle("Show All");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		test.siteConfigurationAction.clickActiveToggle("Show All");
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
	test.siteConfigurationAction.verifyPopupMessage(getData("ErrorMessage.ActiveToggleMessage"));
	}
	
	 //  Bug 1095493
	@Test(priority = 5, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Distributor name.")
	public void Test05_1048538(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Distributor name.");
	
		//
		test.siteConfigurationAction.clickActiveToggle("Show All");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		//test.supportDataActions.enterSearchTermInSearchField("Glaxy","search");
		DistributorAccount_old = test.siteConfigurationAction.getFirstRecordOfColumn("2");
		System.out.println("DistributorAccount_old :"+ DistributorAccount_old);
		test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor(DistributorAccount_old,"search");
		//test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor(" ","search");
		//DistributorAccount_new = test.siteConfigurationAction.getColumnFirstData("2");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(DistributorAccount_old);
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(DistributorAccount_old,"2"));
		//test.siteConfigurationAction.clearText("input_searchPrinter");
		//Assert.assertEquals(DistributorAccount_old, DistributorAccount_new);
		//test.siteConfigurationAction.clearText("search");
		
		
	}
	 //  Bug 1095493
	@Test(priority = 6, description = "VPLX:Manage Destinations-Distributor Accounts:[UI]:User verifies the list of distributor accounts by searching Account No.")
	public void Test06_1048541(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manage Destinations-Distributor Accounts:[UI]: User verifies the list of distributor accounts by searching Account No.");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		accountNo=test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor("1111", "vendorAccountNumberText");
	
	test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor("1111","search");
	test.supportDataActions.verifyNewlyAddedRecordNameInList("1111");
	//Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsForDistributorAccountNumbers("1111"));
	
		}
	
}
