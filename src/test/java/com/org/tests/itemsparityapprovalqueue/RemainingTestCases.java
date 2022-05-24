package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RemainingTestCases extends BaseTest {
	
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

	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.")
	public void Test01_1090588_1152290_1152291_1152292_1152371_1152377_1152385(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.enterFaciltyValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.verifyItemsTabonItemscreen("Items");
		test.siteConfigurationAction.actionButtonIsDisabled("Actions");
		test.siteConfigurationAction.navigationButtonOnItemManagement("nextbtn");
		test.siteConfigurationAction.navigationButtonOnItemManagement("prevbtn");
		test.siteConfigurationAction.verifyItemsTabonItemscreen("Pending Approval");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("approve"));
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("reject"));
		test.siteConfigurationAction.selectAllCheckboxesonItemScreen();
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.")
	public void Test02_1152385(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Approval Queue) [UI] : The user is able to select all the approved items at a time.");
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID5").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(TestDataPropertyReaderAndWriter.getProperty("ItemID5").trim());
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
	}

}
