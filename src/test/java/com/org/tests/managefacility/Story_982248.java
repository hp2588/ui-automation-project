package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982248 extends BaseTest {
	String facilityName;

	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"), 
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}

	@Test(priority = 1, description = "Test Case 1054741:VPLX:Specific Facility Settings:[UI]: "
			+ "To verify that the Transaction Priority Option headline is displayed "
			+ "under Transaction Priority Option tab"
			+ "\n&\n"
			+ "VPLX:Specific Facility Settings: [UI]: To verify that the "
			+ "Validate that Causes Transaction Queue Alert column is available "
			+ "while adding/editing a facility")
	public void Test01_1054741_1054749(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:User verifies the Show Inactive toggle under transaction priority options tab.");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 2);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("timeZoneID", 2);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Transaction Priority Options");
		test.siteConfigurationAction.verifyToggleOption("Transaction Priority Options");
	}
	
	
	@Test(priority = 2, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the Batch Wait Minutes field is displayed under Transaction priority Option tab.")
	public void Test02_1054434(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Verify the Batch Wait Minutes field under Transaction priority Option tab.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Batch Wait Minutes"));
		Assert.assertTrue(test.siteConfigurationAction.verifydefaultValueOfBatchWait());	
	}

	
	@Test(priority = 3, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the Transaction priority column is available while adding/editing a facility.")
	public void Test03_1054459(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Transaction priority column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Transaction Priority Options"));
		Assert.assertTrue(test.siteConfigurationAction.priorityListFacility());	
	}
	

	@Test(priority = 4, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the Batch column is available while adding/editing a facility.")
	public void Test04_1054607(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Batch column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Batch"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("batchEnabledFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("batchEnabledFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("batchEnabledFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("batchEnabledFlag"));	
	}
	
	
	@Test(priority = 5, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the Auto Release column is available while adding/editing a facility.")
	public void Test05_1054608(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Auto Release column is available while adding/editing a facility.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Auto Release"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("autoReleaseFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("autoReleaseFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("autoReleaseFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("autoReleaseFlag"));
	}
	
	
	@Test(priority = 6, description = "VPLX:Specific Facility Settings:[UI]:To verify that "
			+ "the Ignore'Send to Packager' column is available while adding/editing a facility.")
	public void Test06_1054609(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that 'Ignore Send to Packager' column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Ignore Send to Packager"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("ignorePackagerItemFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("ignorePackagerItemFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("ignorePackagerItemFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("ignorePackagerItemFlag"));
	}
	

	@Test(priority = 7, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the Notify Order Picked column is available while adding/editing a facility.")
	public void Test07_1054615(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Notify Order Picked column is available while adding/editing a facility.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Notify Order Picked"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("notifyOrderPickedFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("notifyOrderPickedFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("notifyOrderPickedFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("notifyOrderPickedFlag"));
	}
	
	
	@Test(priority = 8, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the Submit GL Info column is available while adding/editing a facility.")
	public void Test08_1054619(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Submit Gl Info column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Submit General Ledger Info"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("submitGeneralLedgerInformationFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("submitGeneralLedgerInformationFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("submitGeneralLedgerInformationFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("submitGeneralLedgerInformationFlag"));		
	}
	
	
	@Test(priority = 9, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the Validate that Causes Transaction Queue Alert column is available "
			+ "while adding/editing a facility.")
	public void Test09_1054749(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that list of the Transaction Priorities are visible under Transaction priority option tab.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Causes Transaction Queue Alert"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("causesTransactionQueueAlertFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("causesTransactionQueueAlertFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("causesTransactionQueueAlertFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("causesTransactionQueueAlertFlag"));
	}	
	
	
	@Test(priority = 10, description = "VPLX:Specific Facility Settings:[UI]:To verify that "
			+ "the Ignore Send to Check column is available while adding/editing a facility.")
	public void Test10_1054426(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]: Ignore Send to Check column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Ignore Send to Check"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("ignoreSendToCheckFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("ignoreSendToCheckFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("ignoreSendToCheckFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("ignoreSendToCheckFlag"));	
	}
	

	@Test(priority = 11, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the List of transaction priority displays correctly on searching transaction priority name."
			+ "\n&\n"
			+ "VPLX:Specific Facility Settings:[UI]:To verify that "
			+ "the Ignore Send to Check column is available while adding/editing a facility.")
	public void Test11_1054429_1054426(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]: List of transaction priority displays correctly on searching transaction priority name.");
		
		String value = test.siteConfigurationAction.returnTransactionPriorityName();
		test.siteConfigurationAction.enterSearchTermInSearchField(value, "search");
		String value1 = test.siteConfigurationAction.returnTransactionPriorityName();
		Assert.assertEquals(value, value1);
	}
	
	
	@Test(priority = 12, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the User is redirected to Facility Management page when cancel button is clicked.")
	public void Test12_1054726(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Verify that user is on Facility Management page when cancel button is clicked.");
		
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		Assert.assertEquals(test.siteConfigurationAction.getPageHeader(), "Facilities");
	}

}
