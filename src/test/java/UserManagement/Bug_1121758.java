package UserManagement;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;

public class Bug_1121758 extends BaseTest{
	

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
	
	@Test(priority = 1, description = "VVPLX:Navigation from VPLX to Reports: [UI]:Support user has access for Report section")
	public void Test01_1159365() {
		
		test.landingPageActions.navigateToMenu("User Management");
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.verifyUserManagementPage("https://idm-test-uswest2-idmauthz.azurewebsites.net/config/choose-customer");
		test.supportDataActions.verifyDropdownOnUserManagement();
		
	}
		

}
