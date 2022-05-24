package com.org.tests.itemsparityapprovalqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IterationFourTests extends BaseTest {
	
	String External=TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
	ArrayList<String> previous_data, sorted_data;
	List<String> actualData, expectedData;
	
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
	
	@Test(priority = 1, description = "VPLX: Item Setup - ES Parity (Approval Queue)  [UI]: Support user is able to perform Sorting successfully")
	public void Test01_1152387() {
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		
		test.siteConfigurationAction.clickOnSortedIcon("Item", "asc");
		actualData = test.siteConfigurationAction.getColumnDataOfItems("2");
		expectedData = test.siteConfigurationAction.getColumnDataOfItems("2");
		Collections.sort(expectedData);
		Assert.assertEquals(actualData, expectedData);
		
		
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup - ES Parity (Approval Queue) : [UI] - The system is filter only those Items which have been deleted or rejected by the user.")
	public void Test02_1100747() {
		
		
	}

}
