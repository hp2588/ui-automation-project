package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982022 extends BaseTest {
	String[] listSortColumns = { "Name", "Facility", "Status" };
	String[] listButtons = { "add", "edit" };
	String facilitydropdown;
	String destinationName, facility_Name, destinationCode;
	List<String> actualData, expectedData;

	@Test(priority = 1, description = "VPLX: Manage Destinations - General: [UI]: Feature Testing: Destination is visible on Main Menu Screen."
			+ ""
			+ "VPLX:Manage Destinations-General:[UI]:User verifies the destination list screen on load")
	public void Test01_Test02_1045168_1037733(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - General: [UI]: Feature Testing: Destination is visible on Main Menu Screen."
				+ ""
				+ "VPLX:Transaction Queue -Restock: PUT:  Sorting of restock transactions.");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		Assert.assertTrue(test.siteConfigurationAction.verifydestinationNamesContainsOnlyAlphanumericCharacter());
		Assert.assertTrue(test.siteConfigurationAction.verifydestinationStatus());
	}

	@Test(priority = 3, description = "VPLX:Manage Destinations-General:[UI]:User selects the toggle show Inactive/Active on destinations page")
	public void Test03_1037729(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User selects the toggle show Inactive/Active on destinations page");
		Assert.assertTrue(test.siteConfigurationAction.verifyUserIsAbleToSelectDestinationShowInactive());
		Assert.assertTrue(test.siteConfigurationAction.verifyDestinationActiveInactiveStatus());

	}

	@Test(priority = 4, description = "VPLX:Manage Destinations-General:[UI]:User verifies the search for destination textbox on destinations page")
	public void Test04_1038397(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the search for destination textbox on destinations page");
		Assert.assertTrue(test.siteConfigurationAction.verifyUserIsAbleToClickSearchDestinationTextbox());

	}

	@Test(priority = 5, description = "VPLX:Manage Destinations-General:[UI]:User verifies the column Name on Destinations screen")
	public void Test05_1038729(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the column Name on Destinations screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnNameonDestinationPage());

	}

	@Test(priority = 6, description = "VPLX:Manage Destinations-General:[UI]:User verifies the column Facility on Destinations screen.")
	public void Test06_1038730(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the column Facility on Destinations screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnFacilityonDestinationPage());

	}

	@Test(priority = 7, description = "VPLX:Manage Destinations-General:[UI]:User verifies the column Status on Destinations screen.")
	public void Test07_1038732(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the column Status on Destinations screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnStatusonDestinationPage());

	}

	@Test(priority = 8, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Add button on the destination list screen")
	public void Test08_1038720(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Add button on the destination list screen");
		Assert.assertTrue(test.siteConfigurationAction.verifyAddButtonOnDestinationPage());

	}

	@Test(priority = 9, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Edit button on Destinations screen.")
	public void Test09_1038743(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Edit button on Destinations screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyEditButtonOnDestinationPage());

	}

	@Test(priority = 10, description = "VPLX:Manage Destinations-General:[UI]:User verifies the My Facility dropdown on the destinations page")
	public void Test10_1038369(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the My Facility dropdown on the destinations page");
		Assert.assertTrue(test.siteConfigurationAction.verifyFacilitydropdownonDestinationPage());
	}

	@Test(priority = 11, description = "VPLX:Manage Destinations-General:[UI]:User clicks on sort arrows on the column 'Name' ,'Type' and 'Status' on Destinations screen.")
	public void Test11_1038735(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User clicks on sort arrows on the column 'Name' ,'Type' and 'Status' on Destinations screen");
		test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(listSortColumns));
	}

	@Test(priority = 12, description = "VPLX:Manage Destinations-General:[UI]:User searches the list of destinations by destination Name")
	public void Test12_1038739(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User searches the list of destinations by destination Name");
		// test.siteConfigurationAction.verifyDestinationDataOptions(getData("SupportDataList.Item2"));
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		facility_Name = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("costCenterCode",
				"CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyDestinationPopupForClosingProcessOnClickingCancelButton(
				"Are you sure you want to cancel this process?");
		 test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(destinationName);
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(destinationName, "1"));
	}

	@Test(priority = 13, description = "VPLX:Manage Destinations-General:[UI]:User searches the list of destinations by facility Name")
	public void Test13_1038742(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User searches the list of destinations by facility Name");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(facility_Name);
		// script update
//		test.supportDataActions.resetSearch();
	}

	@Test(priority = 14, description = "VPLX:Manage Destinations-General:[UI]:User verifies the list of active destinations on the destinations page")
	public void Test14_1038362(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the list of active destinations on the destinations page.");
		Assert.assertTrue(test.siteConfigurationAction.verifyDestinationActiveInactiveStatus());

	}

	@Test(priority = 15, description = "VPLX:Manage Destination:Feature Testing: Sorting of Name, Facility and Status.")
	public void Test15_1045306(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destination:Feature Testing: Sorting of Name, Facility and Status.");

		actualData = test.siteConfigurationAction.getColumnData("1");
		test.siteConfigurationAction.clickOnSortedIcon("Name", "asc");
		expectedData = test.siteConfigurationAction.getColumnData("1");
//		Collections.sort(expectedData);
		Collections.sort(actualData);
		Assert.assertEquals(actualData, expectedData);

		actualData = test.siteConfigurationAction.getColumnData("2");
		test.siteConfigurationAction.clickOnSortedIcon("Facility", "asc");
		expectedData = test.siteConfigurationAction.getColumnData("2");
//		Collections.sort(expectedData);
		Collections.sort(actualData);
		Assert.assertEquals(actualData, expectedData);

		actualData = test.siteConfigurationAction.getColumnData("3");
		test.siteConfigurationAction.clickOnSortedIcon("Status", "asc");
		expectedData = test.siteConfigurationAction.getColumnData("3");
//		Collections.sort(expectedData);
		Collections.sort(actualData);
		Assert.assertEquals(actualData, expectedData);

	}

	@Test(priority = 16, description = "VPLX:Manage Destination:Feature Testing: Facility drop down box for selecting facility.")
	public void Test16_1045177(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destination:Feature Testing: Facility drop down box for selecting facility.");
		Assert.assertTrue(test.siteConfigurationAction.verifyFacilitydropdownonDestinationPage());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		/*test.siteConfigurationAction.selectValueForFacilityDropDown("facilitydropdown",
				getData("DestinationTab.FacilityName"));
*/
	}
	
	/*Functionality does not exist anymore*/

	/*@Test(priority = 16, description = "User clicks on Site Configuration breadcrumb so Computer screen is displayed")
	public void Test16_1038737(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"User clicks on Site Configuration breadcrumb so Computer screen is displayed");
		test.siteConfigurationAction.clickSiteConfigBreadCrumbonDestination();
		test.siteConfigurationAction.verifyPageTitleContains("Computers");
	}*/

}
