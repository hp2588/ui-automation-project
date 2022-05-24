package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class Story_975824 extends BaseTest {

String app_url;	
	
	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI] -User is able to apply filters by clicking  on the filter icon on Item Management screen."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:User is able to Filter items by clicking on pop-up.")
	public void Test01_Test02_1050028_AND_1062815(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -User is able to apply filters by clicking  on the filter icon on Item Management screen."
				+ ""
				+ "VPLX: Item Setup (System and Facility): [UI]:User is able to Filter items by clicking on pop-up.");
		
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		 
		test.landingPageActions.navigateToFeature("Facilities");
		
		String External = test.siteConfigurationAction.getExternalSystemMappedToFacility(facilityOnWFAScreen);
*/		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());	
		
		test.siteConfigurationAction.clickFilterButton();
		test.siteConfigurationAction.verifyFilterItemsPopup();
	}

	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI] -Default values when user clicks on the filter icons.")
	public void Test03_1050039(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] -Default values when user clicks on the filter icons.");
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("field",
				getData("FilterItems.attribute"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("type",
				getData("FilterItems.operator"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("filter",
				getData("FilterItems.value"));
	}

    @Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI] - User is able to clear the filters which are already set.")
	public void Test04_1051877(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] - User is able to clear the filters which are already set.");
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("field",
				getData("FilterItems.attribute2"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("type",
				getData("FilterItems.operator2"));
		test.siteConfigurationAction.selectValueForValueFromFilterDropdown("filter", 2);
		test.siteConfigurationAction.clickButton("cancel");
	}

	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when value is selected from Attibute dropdown as Dispensing Unit."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when value is selected from Operator dropdown."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when value is selected from Value dropdown."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when attribute active flag is set as Is Not")
	public void Test05_Test06_Test07_Test08_1050074_1050125_1050127_1050046(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when attribute dosageform is selected"
						+ ""
						+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when value is selected from Operator dropdown."
						+ ""
						+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when value is selected from Value dropdown."
						+ ""
						+ "VPLX: Item Setup (System and Facility): [UI] - User is able to set filters when attribute active flag is set as Is Not");
		test.siteConfigurationAction.clickFilterButton();
		test.siteConfigurationAction.selectValueFromDropDown("field", getData("FilterItems.attribute2"));
		//test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("field", 2);
		//test.siteConfigurationAction.selectValueFromDropDownForFilterItem("type", getData("FilterItems.operator2"));
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("type", 1);
		//test.siteConfigurationAction.selectValueFromDropDownForFilterItem("filter", getData("FilterItems.value2"));
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("filter", 1);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifycountIsPresentonFiltericon();
	}

	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI] - User is able to set multiple filters at a time.")
	public void Test09_10501873(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI] - User is able to set multiple filters at a time.");
		test.siteConfigurationAction.clickcountonfilterButton();
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("field", "Dosage Form");
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("field", "Dispensing Unit");
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("field", "Medication Class");
		//test.siteConfigurationAction.clickButton("add");
		/*test.siteConfigurationAction.selectValueFromDropDownForFilterItem("field", "Dosage Form");
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("type", "Is");
		
		test.siteConfigurationAction.selectValueFromDropDownForFilterItem("filter","False");*/
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifycountIsPresentonFiltericon();
	}
	
	@Test(priority = 10, description = "Admin User Login Page Test")
	public void Test04_Admin_Login_Test() {
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	}
	
	@Test(priority = 11, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to view 'Copy Facility' option in the Action tab.")
	public void Test10_1125257(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to view \"Copy Facility\" option in the Action tab.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Copy Facility");
	}

	@Test(priority = 12, description = "VPLX: Item Setup (System & Facility) : [UI] -User clicks on copy facility and a pop-up will be displayed with the list of facilities.")
	public void Test11_1125258(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User clicks on copy facility and a pop-up will be displayed with the list of facilities.");
		test.siteConfigurationAction.clickActionbutton("Copy Facility");
		test.siteConfigurationAction.verifyoptionHeaderonItemscreen("Copy Facility");
		test.siteConfigurationAction.verifyFacilityListonItemScreen();
		
	}
	
	@Test(priority = 13, description = "VPLX: Item Setup (System & Facility) : [UI] -User can only select one Facility from the list.")
	public void Test12_Test13_1125259_AND_1125560(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User can only select one Facility from the list.");
		 test.siteConfigurationAction.clickcopyfacilitylist();
		 test.siteConfigurationAction.clickActionbutton("Cancel");
	}
	@Test(priority = 14, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to find \"Set Participating Facility\" option in the Action tab.")
	public void Test14_1125210(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to find \"Set Participating Facility\" option in the Action tab.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.supportDataActions.verifyCopyFacilityIsPresentonItemscreen("Set Participating Facilities");
		
	}
	@Test(priority = 15, description = "VPLX: Item Setup (System & Facility) : [UI] -User clicks on Set Participating Facility\"  a pop will be displayed with the list of facilities.")
	public void Test15_1125211(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User clicks on Set Participating Facility\"  a pop will be displayed with the list of facilities.");
		test.siteConfigurationAction.clickActionbutton("Set Participating Facilities");
		test.siteConfigurationAction.verifyoptionHeaderonItemscreen("Set Participating Facilities");
		test.siteConfigurationAction.verifyoptionHeaderonItemscreen(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	
		
	}
	
	@Test(priority = 16, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to select the cancel button on popup displayed .")
	public void Test16_1125213(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to select the cancel button on popup displayed .");
		test.siteConfigurationAction.clickCheckboxSetParticipatingFacilty();
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.siteConfigurationAction.verifyItemsTabonItemscreen("Items");
		
	}
	@Test(priority = 17, description = "VPLX: Item Setup (System & Facility) : [UI] -User is able to select facility and click on save button.")
	public void Test17_1125212(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System & Facility) : [UI] -User is able to select facility and click on save button.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Set Participating Facilities");
		test.siteConfigurationAction.clickCheckboxSetParticipatingFacilty();
		test.siteConfigurationAction.clickActionbutton("Save");
	}
	
	/*@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI]:User is able to Filter items by clicking on pop-up.")
	public void Test13_1062815(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]:User is able to Filter items by clicking on pop-up.");
	test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
	test.siteConfigurationAction.clickFilterButton();
	test.siteConfigurationAction.verifyFilterItemsPopup();
	} */
	
	
}