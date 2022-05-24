package com.org.tests.mainmenu.externalsystems;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

@Listeners(com.org.listeners.TestListener.class)

public class Story_977304 extends BaseTest {

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

	@Test(priority = 1, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The user is able to view the list of External Systems")
	public void Test01_1039568() {
		test.landingPageActions.navigateToFeature("External Systems");
		test.supportDataActions.verifyLabelIsPresent("External Systems");
		// test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.supportDataActions.codeListDosageForms("1");

	}

	@Test(priority = 2, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The list of External Systems displays only the active ones by default")
	public void Test02_1039569() {
		// test.supportDataActions.verifyDosageFormsStatusAsActive();
		Assert.assertTrue(test.siteConfigurationAction.verifyActiveExternalSystems("Active", "2"));

	}

	@Test(priority = 3, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The user is able to toggle to exclude the inactive External Systems in the listing")
	public void Test03_1039570() throws Throwable {

		//
		Assert.assertFalse(test.siteConfigurationAction.verifyActiveExternalSystems("Inactive", "2"));

	}

	@Test(priority = 4, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The  list of External Systems is sorted alphanumerically in ascending order of the field 'External Systems Name' by default")
	public void Test04_1039612() throws Throwable {
		//test.supportDataActions.pageRefresh();
		previous_data = test.supportDataActions.captureDataForParticularColumn("1");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrder("1");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}
	
	
	@Test(priority = 5, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The user is able to sort each column of list of External Systems")
	public void Test05_1039621() throws Throwable {
		
		
		test.siteConfigurationAction.clickOnSortedIcon("System Type", "asc");
		actualData = test.siteConfigurationAction.getColumnData("2");
		expectedData = test.siteConfigurationAction.getColumnData("2");
		Collections.sort(expectedData);
		Assert.assertEquals(actualData, expectedData);

		test.siteConfigurationAction.clickOnSortedIcon("Time Zone", "asc");
		actualData = test.siteConfigurationAction.getColumnData("3");
		expectedData = test.siteConfigurationAction.getColumnData("3");
		Collections.sort(expectedData);
		Assert.assertEquals(actualData, expectedData);

		test.siteConfigurationAction.clickOnSortedIcon("Status", "asc");
		actualData = test.siteConfigurationAction.getStatusColumnDataofExternalSystem("4");
		expectedData = test.siteConfigurationAction.getStatusColumnDataofExternalSystem("4");
		Collections.sort(expectedData);
		Assert.assertEquals(actualData, expectedData);

	}

	@Test(priority = 6, description = "VPLX: Manage Healthcare System [UI]: External Systems-View: The user is able to toggle and include the inactive External Systems in the listing")
	public void Test06_1039570() throws Throwable {
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		test.siteConfigurationAction.verifyActiveAndInactiveResults("Active", "Inactive", "2");

	}

	@Test(priority = 7, description = "VPLX: Carousel [UI]: The combined list of active & inactive carousels is displayed in alphanumeric order")
	public void Test07_1030183() {
		test.siteConfigurationAction.refreshPage();
		// test.supportDataActions.clickToggleButton("true",getData("ToggleValue.Carousel"));
		// test.supportDataActions.verifyCarouselStatus();
		previous_data = test.supportDataActions.captureDataForParticularColumn("1");
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrder("1");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	

}
