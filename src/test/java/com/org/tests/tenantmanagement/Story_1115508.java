package com.org.tests.tenantmanagement;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1115508 extends BaseTest {
	String app_url;
	@Override
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		 app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	@Test(priority = 1, description = "VPLX:Tenant Management and Device Provisioning through Tenant Portal:[UI]- Tenant Website Address field is accepting all cases upper and lower case on Add Tenant")
	public void Test01_1084509(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Tenant Management and Device Provisioning through Tenant Portal:[UI]- Tenant Website Address field is accepting all cases upper and lower case on Add Tenant");
		test.tenantManagementActions.NavigateToMultitenancyPortal(app_url+"/multitenancy");
		
		test.tenantManagementActions.clickButton("addTenantForm");
		test.tenantManagementActions.verifyPage("Add Tenant");
		test.tenantManagementActions.EnterRandomValueInInputFieldOnAddTenantMgtPage("customerContactWebsiteAddress",
				getData("TenantManagement.WebsiteURL").toLowerCase());
		test.tenantManagementActions.EnterRandomValueInInputFieldOnAddTenantMgtPage("customerContactWebsiteAddress",
				getData("TenantManagement.WebsiteURL").toUpperCase());
		 
	}
	@Test(priority = 2, description = "VPLX:Tenant Management and Device Provisioning through Tenant Portal:[UI]-User is not able to see the UI for the select ISA when no permission is assigned to user on Transaction Queue")
	public void Test02_1119872(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Tenant Management and Device Provisioning through Tenant Portal:[UI]-User is not able to see the UI for the select ISA when no permission is assigned to user on Transaction Queue");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.tenantManagementActions.verifyTQisNotAvailable("Transaction Queue");
		 
	}

}
