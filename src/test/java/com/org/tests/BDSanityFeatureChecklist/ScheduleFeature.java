package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ScheduleFeature extends BaseTest {

	String scheduleName,day;
	String[] columnHeaders = { "Name", "Facilities", "Hours", "Days", "Action" };
	String facilityName;

	/*==========Automated=======================*/
	@Test(priority = 1, description = "VPLX: Manage Schedules: [UI]: To verify that user "
			+ "is able to navigate to the schedule page.")
	public void Test01_1129434(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: To verify that user is able to navigate to the schedule page.");

		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");
		
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 2, description = "VPLX: Manage Schedules: [UI]: To verify that My Facility drop down "
			+ "enables to view all schedules in facilities that the user is authorized to access")
	public void Test02_1129436(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"To verify that My Facility drop down enables to view all schedules in facilities that the user is authorized to access");
		
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewFacility("FacilityDropdown", "My Facilities");
		
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 3, description = "VPLX: Manage Schedules: [UI]: To verify that user has an option "
			+ "to view a list of all Schedules created under different facilities or a specific facility")
	public void Test03_1129439(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"To verify that user has an option to view a list of all Schedules created under different facilities or a specific facility");
		
		//test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		facilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityName);
		Assert.assertTrue(test.siteConfigurationAction.captureDataForParticularColumnOnSchedule("1").size() > 0);
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 4, description = "VPLX: Manage Schedules: [UI]: To verify the default Headers "
			+ "of \"Pick Schedules\" listing table")
	public void Test04_996085(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: To verify the default Headers of \"Pick Schedules\" listing table");
		
		test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(columnHeaders));
	}
	
	
	@Test(priority = 5, description = "Test Case 1129432:Obsolete : VPLX: Manage Schedules: [UI]: "
			+ "To verify that user is able to set up and configure schedules for the facility - "
			+ "specific for routing rules")
	public void Test05_1129432(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: User is able to set up and configure schedules for destinations within the facility - specific for routing rules");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.clickToSetDays();
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", facilityName);
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());

		test.supportDataActions.clickButtonWithOutAnyWait("save");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}
	
	
	@Test(priority = 6, description = "VPLX: Manage Schedules: [UI]: To verify the availability "
			+ "and working of Search Box")
	public void Test06_995165(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"There is an option to search a specific Scheduler on name in search box available on right top corner");
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(scheduleName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsForSchedules(scheduleName, "1"));
		test.supportDataActions.clearSearchBoxField("search");
	}
	
	
	@Test(priority = 7, description = "VPLX: Manage Schedules: [UI]: To verify the acceptance of Edit "
			+ "with valid( Alphanumeric characters) in text field \"Schedule Name\"")
	public void Test07_996097(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To verify the acceptance of Edit with valid( Alphanumeric characters) "
				+ "in text field \"Schedule Name\"");
		
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);	
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 8, description = "VPLX: Manage Schedules: [UI]: To verify the acceptance "
			+ "of schedule \"Days\" on edit mode")
	public void Test08_996114(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: To verify the acceptance of schedule \"Days\" on edit mode");
		
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		day=test.siteConfigurationAction.clickToSetNewDays();
		test.siteConfigurationAction.clickSaveButton();
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		test.siteConfigurationAction.verifyNewlySelectedDayInList(scheduleName, day);
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 9, description = "VPLX: Manage Schedules: [UI]: To verify the acceptance "
			+ "of Edit schedule with all valid schedule fields i.e Name/Days/Start and End Hours")
	public void Test09_996838(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: To verify the acceptance of Edit schedule with all valid schedule fields i.e Name/Days/Start and End Hours");
		
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickSaveButton();
		
		//test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 10, description =
			  "VPLX: Manage Schedules: [UI]: To verify that screen allows user to delete schedules "
			  + "provided it’s not being used within a routing rule under same facility.") 
	public void Test10_1129443(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To verify that screen allows user to delete schedules provided it’s not being used "
				+ "within a routing rule under same facility."); 
		
		test.siteConfigurationAction.clickDeleteScheduleLink(scheduleName, "Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are you sure you want to delete the schedule?");
		test.siteConfigurationAction.clickButton("primary");
		
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("The schedule " + scheduleName + " has been deleted successfully");
		test.siteConfigurationAction.verifyScheduleIsNotPressent(scheduleName);
	}
	
	
	/*==========Automated=======================*/
	@Test(priority = 11, description =
	  "VPLX: Manage Schedules: [UI]: To verify that user can add a new Schedule under a facility "
	  + "by defining Schedule name, Days and time duration..") 
	public void Test11_1129444(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: To verify that user can add a new Schedule under a facility "
				+ "by defining Schedule name, Days and time duration."); 
		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityName);
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", facilityName);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
	
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		//test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	  
	  }
	
}
