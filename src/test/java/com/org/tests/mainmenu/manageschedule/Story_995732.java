package com.org.tests.mainmenu.manageschedule;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_995732 extends BaseTest {
	String scheduleName;
	String[] columnHeaders = { "Name", "Facilities", "Hours", "Days", "Action" };
	List<String> actualData, expectedData;
	String facilityname;
	String facilityDropdownName = "defaultFacilityKey";
	
	
	@Test(priority = 1, description = "VPLX: Manage Schedules: [UI]: Add Schedule")
	public void Test00_Add_Shedule(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Add Schedule");
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("facilityModelKey");
		facilityname = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.siteConfigurationAction.selectValueForDropDown(facilityDropdownName, facilityname);
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}
	
	@Test(priority = 2, description = "VPLX: Manage Schedules: [UI]: The schedules are displayed on the last updated time basis by default"
			+ "VPLX: Manage Schedules: [UI]: Feature Testing-  Recently created schedule is  appearing on top of the list")
	public void Test01_Test02_1014629_1017084(Method method) {
	
		test.siteConfigurationAction.verifyScheduleDisplayedOnBasisOfLastUpdate("1",scheduleName);
	}
	
	@Test(priority = 3, description = "VPLX: Manage Schedules: [UI]: Acceptance of invalid Schedule Name more than 50 chars")
	public void Test03_995166(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of invalid Schedule Name  more than 50 chars");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("facilityModelKey");
		//test.siteConfigurationAction.selectValueForDropDown(facilityDropdownName, facilityname);
		test.siteConfigurationAction.selectValueForDropDown(facilityDropdownName, facilityname);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("scheduleName"), 50,
				"[ASSERTION FAILED]: Max Length for input field Schedule Name is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("scheduleName", DateUtil.getAlphaNumericString(51),50);
		
		test.siteConfigurationAction.clickButton("cancel");
		
	}
	
	@Test(priority = 4, description = "VPLX: Manage Schedules: [UI]: Adding Duplicate Schedule Name")
	public void Test04_Test05_995173_996023(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX: Manage Schedules: [UI]: Adding Duplicate Schedule Name");
		
		scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		String facilityName = test.siteConfigurationAction.getColumnFirstData("2");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyAddSchedulePopup("Add Schedule");
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		
		test.siteConfigurationAction.selectValueForDropDown(facilityDropdownName, facilityName);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName", scheduleName);
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingRecord(
				getData("SchedulePicksDetails.ErrorMsg_DuplicateScheduleName").trim());
		
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	@Test(priority = 5, description = "VPLX: Manage Schedules: [UI]: Appropriate warning message for Duplicate schedule")
	public void Test06_Test07_995175_996076(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Appropriate warning message for Duplicate schedule");
		String scheduleNameOld = test.siteConfigurationAction.getColumnFirstData("1");
		String facilityName = test.siteConfigurationAction.getColumnFirstData("2");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyAddSchedulePopup("Add Schedule");
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		
		test.siteConfigurationAction.selectValueForDropDown(facilityDropdownName, facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName", scheduleNameOld);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingRecord(
				getData("SchedulePicksDetails.ErrorMsg_DuplicateScheduleName"));
		
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	@Test(priority = 6, description = "VPLX: Manage Schedules: [UI]: Pick edit schedule page gets redirected to listing page of pick schedule After Edit and save")
	public void Test08_Test09_995176_996097(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Pick edit schedule page gets redirected to listing page of pick schedule After Edit and save");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}
	
	@Test(priority = 7, description = "VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page")
	public void Test10_995176(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchFieldonPickSchedulePage());
		Assert.assertTrue(test.supportDataActions.verifyButtons("add"));
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(columnHeaders)));
		Assert.assertTrue(test.supportDataActions.verifyButtons("delete"));
	}
	
	@Test(priority = 8, description = "VPLX: Manage Schedules: [UI]: The default add schedule page fields")
	public void Test11_995975(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: The default add schedule page fields");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("scheduleName"));
		
		test.siteConfigurationAction.verifyFieldIsMandatory("pickScheduleDays", "Days");
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");

	}
	
	@Test(priority = 9, description = "VPLX: Manage Schedules: [UI]: Acceptance of Blank in field Schedule Name of Add a New Pick Schedule")
	public void Test12_Test13_995993_996104(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Blank in field Schedule Name of Add a New Pick Schedule");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName", 
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clearInputBox("scheduleName");
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_EmptyScheduleName"));
		
	}
	
	@Test(priority = 10, description = "VPLX: Manage Schedules: [UI]: Acceptance of special characters in field Schedule Name of Add a New Pick Schedule page")
	public void Test14_996004(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Blank in field Schedule Name of Add a New Pick Schedule");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis() + "@#");
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction
				.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_ScheduleNameWithSpecialChars"));
		//test.siteConfigurationAction.verifyErrorMessage(getData("SchedulePicksDetails.ErrorMsg_ScheduleNameWithSpecialChars"));
		test.siteConfigurationAction.clickButton("cancel");
		
	}
	
	@Test(priority = 11, description = "VPLX: Manage Schedules: [UI]: Acceptance of schedule without selecting field Days of Add a New Pick Schedule page"
			+ "VPLX: Manage Schedules: [UI]: Acceptance of Only valid 'Schedule Name'")
	public void Test15_Test16_996006_995976(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of schedule without selecting field Days of Add a New Pick Schedule page"
				+ "VPLX: Manage Schedules: [UI]: Acceptance of Only valid 'Schedule Name'");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		Assert.assertTrue(test.siteConfigurationAction.verifyErrorMessage(getData("SchedulePicksDetails.ErrorMsg_EmptyDays")));
		
	}
	
	@Test(priority = 12, description = "VPLX: Manage Schedules: [UI]: Adding a schedule with valid schedule Name/Days and Hour"
			+ "VPLX: Manage Schedules: [UI]: Acceptance of 'space' in  'Schedule Name'")
	public void Test17_Test18_996010_995985(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Adding a schedule with valid schedule Name/Days and Hours"
				+ "VPLX: Manage Schedules: [UI]: Acceptance of 'space' in  'Schedule Name'");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				"Schedule Name " + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}
	
	@Test(priority = 13, description = "VPLX: Manage Schedules: [UI]: Acceptance of schedule with similar "
			+ "Start Hours and End Hours On Add a New Pick Schedule popup page")
	public void Test19_996013(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Adding a schedule with valid schedule Name/Days and Hours");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		 test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
			test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.siteConfigurationAction.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_InvalidHours"));
		test.siteConfigurationAction.clickButton("cancel");
		
	}
	
	
	@Test(priority = 14, description = "VPLX: Manage Schedules: [UI]: Feature Testing- "
			+ "Warning is appearing when the End hour is Below the Start hour while creating new schedule")
	public void Test20_1017086() {
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		 test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
			test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", "00:30");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime","00:15");
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.siteConfigurationAction.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_InvalidHours"));
		test.siteConfigurationAction.clickButton("cancel");
		
	}
	
	@Test(priority = 15, description = "VPLX: Manage Schedules: [UI]: Default Headers of Pick Schedules listing table")
	public void Test21_996085(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Default Headers of Pick Schedules listing table");
		
		test.siteConfigurationAction.verifyColumnHeaders(Arrays.asList(columnHeaders));
	}
	
	
	@Test(priority = 16, description = "VPLX: Manage Schedules: [UI]: The Availability of default fields "
			+ "on Edit Pick Schedule popup")
	public void Test22_996092(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Default Headers of Pick Schedules listing table");
		
		scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		
		test.siteConfigurationAction.deselectDays();
		test.siteConfigurationAction.clickToSetDays();
		
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 17, description = "VPLX: Manage Schedules: [UI]: Acceptance of  "
			+ "Duplicate Schedule Name on Edit mode")
	public void Test23_996101(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of  Duplicate Schedule Name on Edit mode");
		
		String scheduleNameOld = test.siteConfigurationAction.getColumnFirstData("1");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName", 
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleNameOld);
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", facilityname);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName", scheduleName);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingRecord(
				getData("SchedulePicksDetails.ErrorMsg_DuplicateScheduleName"));
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 18, description = "VPLX: Manage Schedules: [UI]: Acceptance of special chars in Schedule Name on edit mode"
			+ "VPLX: Manage Schedules: [UI]: Pick edit schedule page gets redirected to listing page of pick schedule After Edit and save")
	public void Test24_Test25_996110_996856(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of special chars in Schedule Name on edit mode"
				+ "VPLX: Manage Schedules: [UI]: Pick edit schedule page gets redirected to listing page of pick schedule After Edit and save");
		
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis() + "@#");
		test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.siteConfigurationAction.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_ScheduleNameWithSpecialChars"));		
	}
	
	
	@Test(priority = 19, description = "VPLX: Manage Schedules: [UI]: Acceptance of schedule without selecting any Days  on edit mode")
	public void Test26_996113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of schedule without selecting any Days  on edit mode");
		//test.siteConfigurationAction.clickEditScheduleLink(scheduleName,"Edit");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		test.siteConfigurationAction.deselectDays();
		//test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		Assert.assertTrue(test.siteConfigurationAction.verifyErrorMessage(getData("SchedulePicksDetails.ErrorMsg_EmptyDays")));
		
	}
	
	@Test(priority = 20, description = "VPLX: Manage Schedules: [UI]: Acceptance of schedule Days  on edit mode")
	public void Test27_996114(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of schedule Days  on edit mode");
		
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		scheduleName=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis() );
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		
		test.siteConfigurationAction.clickSaveButton();
//		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}
	
	@Test(priority = 21, description = "VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with all valid schedule fields i.e Name/Days/Start  and End Hours")
	public void Test28_996838(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with all valid schedule fields i.e Name/Days/Start  and End Hours");
		
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", "23:59");
		test.siteConfigurationAction.clickSaveButtonWithoutWait();
		
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}
	
	
	@Test(priority = 22, description = "VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with similar Start Hours and End Hours")
	public void Test29_	(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with similar Start Hours and End Hours");
		
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.deselectDays();
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.siteConfigurationAction.verifyErrorMessageForBlankField(getData("SchedulePicksDetails.ErrorMsg_InvalidHours"));
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 23, description = "VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with last time slot of Start Hours and End Hours")
	public void Test30_996844(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Edit schedule with last time slot of Start Hours and End Hours");
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", "23:45");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("endTime");
		test.siteConfigurationAction.selectValueForDropDown("endTime", "23:59");
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}
	
	
	@Test(priority = 24, description = "VPLX: Manage Schedules: [UI]: Cancel button cancels the edited schedule")
	public void Test31_996845(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Cancel button cancels the edited schedule");
		
		String scheduleName_old = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName_old);
		
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("cancel");
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName_old);
	}
	
	@Test(priority = 25, description = "VPLX: Manage Schedules: [UI]: Acceptance of Deleting an existing Schedule Name"
			+ "VPLX: Manage Schedules: [UI]: Feature Testing- Confirmation Delete message is appearing with details")
	public void Test32_Test33_996852_1017088(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Deleting an existing Schedule Name"
				+ "VPLX: Manage Schedules: [UI]: Feature Testing- Confirmation Delete message is appearing with details");
		
		String scheduleNameOld = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickDeleteScheduleLink(scheduleNameOld, "Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are you sure you want to delete the schedule?");
		test.siteConfigurationAction.clickButtonByTextWithoutWait("Confirm");
		test.siteConfigurationAction.verifySuccessMessageOnDeleteSchedule("The schedule" +" "+ scheduleNameOld +" "+"has been deleted successfully");
		test.siteConfigurationAction.verifyScheduleIsNotPressent(scheduleNameOld);
		
	}
	
	@Test(priority = 26, description = "VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page")
	public void Test25_995925(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page");
		
		String scheduleNameOld = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.verifySearchFieldonPickSchedulePage();
		test.siteConfigurationAction.verifyButtonIsPresent("Add");
		test.siteConfigurationAction.enterSearchTermInSearchField(scheduleNameOld, "search");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleNameOld);
		test.supportDataActions.resetSearch();
		
	}
	
	// duplicate test
	// @Test(priority = 25, description = "VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page")
	public void Test34_995925(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Availability of default fields in Pick Schedules Page");
		
		String scheduleNameOld = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.verifySearchFieldonPickSchedulePage();
		test.siteConfigurationAction.verifyButtonIsPresent("add");
		test.siteConfigurationAction.enterSearchTermInSearchField(scheduleNameOld, "search");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleNameOld);
		test.supportDataActions.resetSearch();
		
	}
	
	@Test(priority = 27, description = "VPLX: Manage Schedules: [UI]: On successful edit schedule, page reloads the listings of pick schedule"
			+ "VPLX: Manage Schedules: [UI]: Feature Testing- Click on Edit button on any schedule to Check Pop-up appears")
	public void Test35_Test36_996855_1017007(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: On successful edit schedule, page reloads the listings of pick schedule"
				+ "VPLX: Manage Schedules: [UI]: Feature Testing- Click on Edit button on any schedule to Check Pop-up appears");
		String scheduleName = test.siteConfigurationAction.getColumnFirstData("1");
		
		/*=======================TC: 1017007================*/
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		
		//test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("defaultFacilityKey");
		//test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.selectValueForDropDown("endTime", getData("SchedulePicksDetails.EndHour"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		test.siteConfigurationAction.clickSaveButton();
		
		if(test.siteConfigurationAction.isButtonDisplayedUsingId("primary")) {
			test.siteConfigurationAction.clickButtonUsingId("primary");
		}
		
		//test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}
	
	
	@Test(priority = 28, description = "VPLX: Manage Schedules: [UI]: The Pick Schedule gets sorted in Descending order of Schedule name"
			+ " when sorting arrow icon is clicked if names are arranged in Ascending order already")
	public void Test37_1014629() {
		
		test.siteConfigurationAction.clickOnSortedIcon("Name", "asc");
		actualData = test.siteConfigurationAction.captureDataForParticularColumnOnSchedule("1");
		expectedData = test.siteConfigurationAction.sortDataForParticularColumnInAscendingOrderOnSchedule("1");
		Assert.assertEquals(actualData, expectedData);
		
		test.siteConfigurationAction.clickOnSortedIcon("Name", "desc");
		actualData = test.siteConfigurationAction.captureDataForParticularColumnOnSchedule("1");
		expectedData = test.siteConfigurationAction.sortDataForParticularColumnInDescendingOrderOnSchedule("1");
		Assert.assertEquals(actualData, expectedData);
		
	}
	
	@Test(priority = 29, description = "VPLX: Manage Schedules: [UI]: Acceptance of Add schedule with last time slot of \"Start Hours\" and \"End Hours\"" 
			+ "&"
			+ "")
	public void Test38_Test39_995169_996051(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Acceptance of Add schedule with last time slot of Start Hours and End Hours");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",facilityname);
		String scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("startTime", "23:45");
		test.siteConfigurationAction.selectValueForDropDown("endTime", "23:59");
		test.siteConfigurationAction.clickToSetDays();
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}
	
	@Test(priority = 30, description = "VPLX: Manage Schedules: [UI]: Availability and working of Search Box")
	public void Test40_995165(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Schedules: [UI]: Availability and working of Search Box");
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", facilityname);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		String scheduleName_delete = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName_delete);
		
		test.siteConfigurationAction.enterSearchTermInSearchField(scheduleName_delete, "search");
		String searchedRecord = test.siteConfigurationAction.getColumnFirstData("1");
		Assert.assertEquals(searchedRecord, scheduleName_delete);
		
		test.siteConfigurationAction.clickDeleteScheduleLink(scheduleName_delete, "Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are you sure you want to delete the schedule?");
		test.siteConfigurationAction.clickButton("primary");
		
		//test.siteConfigurationAction.verifySuccessMessageOnDeleteSchedule("The schedule" +" "+ scheduleName_delete +" "+"has been deleted successfully");
		test.siteConfigurationAction.verifyScheduleIsNotPressent(scheduleName_delete);
		
	}
	
	
}
