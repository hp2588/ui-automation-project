package com.org.tests.mainmenu.wastereasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997995 extends BaseTest {

	String wasteReasonName;
	String wasteReasonName_old;

	@Test(priority = 1, description = "VPLX: Waste Reason: [UI]- User is able to type in search box.")
	public void Test01_1028324(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason: [UI]- User is able to type in search box.");
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reason");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.clearSearchBoxField("search");
	}

	@Test(priority = 2, description = "VPLX: Waste Reason:[UI]- Search is displaying active records when toggle is off")
	public void Test02_1028329(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason:[UI]- Search is displaying active records when toggle is off");
		
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Active");
	}
	
	@Test(priority = 3, description = "VPLX: Waste Reason: [UI]- Search rules defined for contains search.")
	public void Test03_1028333_1028349(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason: [UI]- Search rules defined for contains search.");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
	}
	
	@Test(priority = 4, description = "VPLX: Waste Reason:[UI]- Validate 'No matching Results' message when no results is found.")
	public void Test04_1028341(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason:[UI]- Validate 'No matching Results' message when no results is found.");
		test.supportDataActions.clearSearchBoxField("search");
		wasteReasonName_old = getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis();
		System.out.println(wasteReasonName_old);
		test.supportDataActions.enterSearchTerm("-------","search");
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), getData("WasteReasonDetails.ErrorMsg_NoData"));
	}
	
	@Test(priority = 5, description = "VPLX: Waste Reason: [UI]-Search for Waste Reason text field is accept up to 50 characters.")
	public void Test05_1028344_1028355(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason: [UI]-Search for Waste Reason text field is accept up to 50 characters.");
		test.supportDataActions.clearSearchBoxField("search");
		wasteReasonName_old=test.supportDataActions.generatingRandomStringForWasteReasonName(100);
		test.supportDataActions.enterSearchTermInSearchBox(wasteReasonName_old,"search");
		Assert.assertEquals(test.supportDataActions.verifyLengthOfWasteReasonSearchField("search"), 50,
				"[ASSERTION FAILED]: Length for input field Waste Reason Name is greater than 50 characters");
		test.supportDataActions.clearSearchBoxField("search");
	}
	
	@Test(priority = 6, description = "VPLX: Waste Reason:[UI]- Reset functionality when click on close(X) button.")
	public void Test06_1028346(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason:[UI]- Reset functionality when click on close(X) button.");

		wasteReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.resetSearch();
		wasteReasonName_old = test.supportDataActions.getColumnFirstData("1");
		System.out.println("wasteReasonName_old");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
	}
	
	@Test(priority = 7, description = "VPLX: Waste Reason:[UI]- Search result should be displayed, when user enters non existant random value and enable/disable toggle button")
	public void Test07_1071869(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Waste Reason:[UI]- Search result should be displayed, when user enters non existant random value and enable/disable toggle button");
		


		test.supportDataActions.enterSearchTermInSearchField("new","search");
		Assert.assertTrue(test.supportDataActions.verifyDataContainsRandomString(getData("WasteReasonColumnNumber.Name"),"New"),
				"[ASSERTION FAILED]: Random String is not contained in the waste reason name column data");
		Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatusAsActive());
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatus());
	
		//test.supportDataActions.resetSearch();
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
	}
}
