package com.org.tests.mainmenu.holdreasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.automation.utils.YamlReader;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998000 extends BaseTest {

	String holdReasonName_old, holdReasonName_new, holdReasonName;
	ArrayList<String> previous_data, sorted_data;
	String[] columnHeaders = { "Name", "Description", "Status" };
	String destination, firstname, priority, location;
	String app_url;
	
	
	@Test(priority = 1, description = "VPLX: Hold Reason UI: View: Hold reasons are ordered "
			+ "as per creation time in descending order when a new hold reason is added")
	public void Test01_1063448(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI: Feature Testing- Hold reasons are ordered as per creation time in descending order when new hold reason is added");
		
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
//		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
//		test.supportDataActions.verifyDataIsAddedAsPerTimeStamp(holdReasonName);
		
		String firstRecordName = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(firstRecordName, holdReasonName);
	}
	
	
	@Test(priority = 2, description = "VPLX: Hold Reason UI: View: Verify the Hold Reason screen elements")
	public void Test02_1029036(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To Verify, Hold Reason List UI is visible according to VD.");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Hold Reasons"),
				"[ASSERTION FAILED]: Hold Reason Label are Not Present");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Carousel")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifySearchBoxIsPresent("search"),
				"[ASSERTION FAILED]: Search Box is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyButtons("add"), "[ASSERTION FAILED]: Buttons Not Present");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaders(columnHeaders),
				"[ASSERTION FAILED]: Coulum Headers are Not Present");
		
		//Assert.assertTrue(test.supportDataActions.verifyLinksOnUI("edit"), "[ASSERTION FAILED]: Links are Present");
		//Assert.assertTrue(test.supportDataActions.verifyBreadCrumb("Hold Reason"),"[ASSERTION FAILED]: BreadCrumb Is Not Present");
		test.supportDataActions.verifySortIcon(getData("HoldReasonColumnName.Name"));
		test.supportDataActions.verifySortIcon(getData("HoldReasonColumnName.Description"));
		test.supportDataActions.verifySortIcon(getData("HoldReasonColumnName.Status"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Hold Reason UI: View: The user is able to sort "
			+ "by Name in ascending and descending order.")
	public void Test03_1029349(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to sort Name column in ascending and descending order.");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyHoldReasonStatus();
		
		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("HoldReasonColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		
		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("HoldReasonColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in descending order");
	}
	
	
	@Test(priority = 4, description = "VPLX: Hold Reason UI: View: The user is able to sort the list "
			+ "by Description in ascending and descending order.")
	public void Test04_1029350(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to sort Description column in ascending and descending order.");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyHoldReasonStatus();
		
		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Description"));
		previous_data = test.supportDataActions
				.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Description"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("HoldReasonColumnNumber.Description"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Description column data is not sorted in ascending order");
		
		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Description"));
		previous_data = test.supportDataActions
				.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Description"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("HoldReasonColumnNumber.Description"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Description column data is not sorted in ascending order");
	}
	
	
	@Test(priority = 5, description = "VPLX: Hold Reason UI: View: The user is able to sort the list "
			+ "by Status in ascending and descending order.")
	public void Test05_1029351(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to sort Status column in ascending and descending order.");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Status"));
		previous_data = test.supportDataActions
				.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Status"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("HoldReasonColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");

		test.supportDataActions.verifyAndClickSortIcon(getData("HoldReasonColumnName.Status"));
		previous_data = test.supportDataActions
				.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Status"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("HoldReasonColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
	}
	
	
	@Test(priority = 6, description = "VPLX: Hold Reason UI: View: The user is able to "
			+ "show the active hold reason list by disabling the Show Inactive toggle button."
			+ "\n&\n"
			+ "VPLX: Hold Reason UI: View: The user is able to view the inactive and active "
			+ "hold reason list by enabling the Show Inactive toggle button.")
	public void Test06_Test07_1029352_1029353(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to get the active hold reason list by disabling show inactive toggle button.");
		//test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyHoldReasonStatusAsActive());
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertFalse(test.supportDataActions.verifyHoldReasonStatusAsActive());
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
	}
	
	
	@Test(priority = 8, description = "VPLX: Hold Reason UI: View: The Hold Reason list is displayed "
			+ "when the page is opened.")
	public void Test08_1029487(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason list is coming as soon as the page is opened.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.verifyRecordList();
	}
	
	
	@Test(priority = 9, description = "VPLX: Hold Reason UI: View: Sorting is not done when "
			+ "only active/inactive records are present.")
	public void Test09_1029580(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Sorting is not done when only active/inactive records are present.");
		
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		
		previous_data = test.supportDataActions
				.captureDataForParticularColumn(getData("HoldReasonColumnNumber.Status"));
		test.supportDataActions.VerifySortIconIsDisabled(getData("HoldReasonColumnName.Status"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("HoldReasonColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
	}
	
	// TODO - Yugal - Obsolete
	@Test(priority = 10, description = "Test Case 1040400:OBSOLETE VPLX:Hold Reason: UI: "
			+ "Feature Testing- JS/HTML/CSS is not executed on Hold Reason UI")
	public void Test10_1040400(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI: Feature Testing- JS/HTML/CSS is not executed on Hold Reason UI");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterValueInInputField("descriptionText","alert("+System.currentTimeMillis()+")");
		test.supportDataActions.EnterValueInInputField("holdReasonSummaryText","alert("+System.currentTimeMillis()+")");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
	}
	
	
	@Test(priority = 11, description = "VPLX:Hold Reason: UI: Feature Testing - Keyboard events are supported for Hold Reason UI")
	public void Test11_1040377(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI: Feature Testing - Keyboard events are supported for Hold Reason UI");
		
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName, "search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		String holdNameSearched = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(holdNameSearched, holdReasonName);
		test.supportDataActions.resetSearch();
		
	}
	
}
