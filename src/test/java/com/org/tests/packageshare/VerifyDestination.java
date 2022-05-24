package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class VerifyDestination extends BaseTest {

	@Test(priority = 1, description = "VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Distributor Accounts Tabs Have any settings for Existing Destination")
	public void Test01_1144774_1144781_1144784(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Distributor Accounts Tabs Have any settings for Existing Destination");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"), "search");
		test.supportDataActions.clickEditButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"));
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving").trim());
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "123456789");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"), "search");
		test.supportDataActions.clickEditButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"));
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		test.siteConfigurationAction.verifyPackageShareAlert("Cannot enable Destination Participates in Package sharing option until all settings in Distributor Accounts, Items, and Users tabs are cleared.");
		test.siteConfigurationAction.navigationButtonOnItemManagement("primary");
	}
	
	@Test(priority = 2, description = "VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Items  Tabs Have any settings for Existing Destination")
	public void Test02_1152264(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Items  Tabs Have any settings for Existing Destination");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.clearRandomValueInAccountNumberField("vendorAccountNumberText-0");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyPageTitleContains("Items");
		test.siteConfigurationAction.selectCheckboxPackageSharing("enableReceiveNSend");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"), "search");
		test.supportDataActions.clickEditButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"));
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		test.siteConfigurationAction.verifyPackageShareAlert("Cannot enable Destination Participates in Package sharing option until all settings in Distributor Accounts, Items, and Users tabs are cleared.");
		test.siteConfigurationAction.navigationButtonOnItemManagement("primary");
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyPageTitleContains("Items");
		test.siteConfigurationAction.selectCheckboxPackageSharing("enableReceiveNSend");	
	}
	
	@Test(priority = 3, description = "VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Users Tabs Have any settings for Existing Destination")
	public void Test03_1152265(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Package Sharing:[UI]:[CR]:Modal window with Alert message  is coming \"Can't enable Destination Particpates in Package Sharing\" if Items  Tabs Have any settings for Existing Destination");
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyPageTitleContains("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.clickCheckboxForUsers();
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"), "search");
		test.supportDataActions.clickEditButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("DestinationName1"));
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		test.siteConfigurationAction.verifyPackageShareAlert("Cannot enable Destination Participates in Package sharing option until all settings in Distributor Accounts, Items, and Users tabs are cleared.");
		test.siteConfigurationAction.navigationButtonOnItemManagement("primary");
	}
}
