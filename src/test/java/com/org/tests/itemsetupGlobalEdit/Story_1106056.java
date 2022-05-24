package com.org.tests.itemsetupGlobalEdit;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1106056 extends BaseTest {
	String app_url;
	
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] All other options instead of Edit selected  are visible under actions tab if no item get selected from the items tab.")
	public void Test01_1106555(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] All other options instead of Edit selected  are visible under actions tab if no item get selected from the items tab.");
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.ClickOnExternalSystemRichTextbox();
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Add New Item");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Add From PIS");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Copy Facility");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Set Participating Facilities");
		}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit Selected option get displayed under Actions menu after selecting the  items under the facility")
	public void Test02_1106554(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Edit Selected option get displayed under Actions menu after selecting the  items under the facility");
		test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
		//test.supportDataActions.selectMultipleCheckboxes(getData("ExternalSystem.multicheckbox2"));
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Edit Selected");
	}
	@Test(priority = 3, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] All options are visible under actions tab if no item get selected from the Pending Approval tab.")
	public void Test03_1106557(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] All options are visible under actions tab if no item get selected from the Pending Approval tab.");
		test.siteConfigurationAction.clickPendingApprovalTabonItemScreen("Pending Approval");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Add New Item");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Add From PIS");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Copy Facility");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Set Participating Facilities");
		}
	@Test(priority = 4, description = "VPLX: Item Setup - ES Parity (Global Edit) : [UI] Approved selected and Reject selected options Are visible under the Actions tab after selecting the multiple items from Pending Approval tab.")
	public void Test04_1106560(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup - ES Parity (Global Edit) : [UI] Approved selected and Reject selected options Are visible under the Actions tab after selecting the multiple items from Pending Approval tab.");
		test.supportDataActions.selectMultipleCheckboxes("1");
		test.supportDataActions.selectMultipleCheckboxes("2");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Approve Selected");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Reject Selected");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Add From PIS");
	}
}
		
	