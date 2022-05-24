package com.org.test.autocreatePO;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1059354_1064496 extends BaseTest {

	String facility = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(), hour, min, ampm, day;
	String[] Days = { "M", "W", "F" };

	@Test(priority = 1, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: Auto Create Schedule Settings link on Buyer Dashboard")
	public void Test01_1091288() {

		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.purchaseDashboardActions.clickRefreshOrder("Refresh Order");
//		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	//	test.purchaseDashboardActions
		//		.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifySchedulelink("Off"),
				"[Assertion Failed]: Schedule Link is not present");

	}

	@Test(priority = 2, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: 'Auto Create POs' screen on clicking Auto Create Schedule Settings link "
			+ " on Buyer Dashboard")
	public void Test02_1091292() {

		Assert.assertTrue(test.purchaseDashboardActions.verifyModalScreen("modal-body"),
				"[ASSERTION FAILED]: Modal Screen is not displayed");

	}

	@Test(priority = 3, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: Facility name on 'Auto Create POs' screen.")
	public void Test03_1091298() {
		Assert.assertTrue(test.purchaseDashboardActions.verifyFacilityNameOnModalScreen(facility),
				"[ASSERTION FAILED]: Facility on Modal Screen is not displayed");

	}

	@Test(priority = 4, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: 'Create Manually' button on  Purchase Order Auto-Create Schedule")
	public void Test04_1091298() {

		Assert.assertFalse(test.purchaseDashboardActions.verifyRadioButton("Create manually", "createManually"),
				"[ASSERTION FAILED]: Create manually radio button does not exist on modal screen.");
	}

	@Test(priority = 5, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]:  'Notes' present on 'Auto Create POs'")
	public void Test05_1091316() {

		Assert.assertTrue(
				test.purchaseDashboardActions.verifyFacilityNameOnModalScreen(
						"To have the system periodically update the items in created purchase orders, add additional schedules."),
				"[ASSERTION FAILED]: Facility on Modal Screen is not displayed");

	}

	@Test(priority = 6, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: Fields are disabled by default when 'Auto Create POs' pop-up is disabled")
	public void Test06_1091331() {

		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("hourId"),
				"[ASSERTION FAILED]: Hour DropDown is Enabled.");

		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("minuteId"),
				"[ASSERTION FAILED]: Minutes DropDown is Enabled.");
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("amPmId"),
				"[ASSERTION FAILED]: AM/PM DropDown is Enabled.");

		/*
		 * Assert.assertFalse(test.purchaseDashboardActions.
		 * verifyButtonOnModalScreenIsDisabled("secondary","S"),
		 * "[ASSERTION FAILED]: Day Button is Enabled.");
		 */

		Assert.assertFalse(test.purchaseDashboardActions.verifyButtonOnModalScreenIsDisabled("tertiary", "M"),
				"[ASSERTION FAILED]: Day Button is Enabled.");
		/*
		 * Assert.assertFalse(test.purchaseDashboardActions.
		 * verifyButtonOnModalScreenIsDisabled("tertiary","T"),
		 * "[ASSERTION FAILED]: Day Button is Enabled.");
		 */
		Assert.assertFalse(test.purchaseDashboardActions.verifyButtonOnModalScreenIsDisabled("tertiary", "W"),
				"[ASSERTION FAILED]: Day Button is Enabled.");

	}

	@Test(priority = 7, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]:  'Time' Format present on 'Auto Create POs'")
	public void Test07_1091321() {

		Assert.assertFalse(
				test.purchaseDashboardActions.verifyRadioButton("Create automatically based on schedule",
						"createAutomaticallyBasedOnSchedule"),
				"[ASSERTION FAILED]: Create automatically based on schedule radio button does not exist on modal screen.");

		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("hourId");
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("minuteId");
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("amPmId");
	}

	@Test(priority = 8, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: When user switches from Automatic to Manual schedule")
	public void Test08_1091314() {

		hour = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("hourId", 1);
		min = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("minuteId", 1);
		ampm = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("amPmId", 1);

		day = test.purchaseDashboardActions.selectDayOnModalScreen("btn btn-day", "W");

	}

	@Test(priority = 9, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]: When user switches from Automatic to Manual schedule")
	public void Test09_1091308() {

		Assert.assertFalse(test.purchaseDashboardActions.verifyRadioButton("Create manually", "createManually"),
				"[ASSERTION FAILED]: Create manually radio button does not exist on modal screen.");
		
	}

	@Test(priority = 10, description = "VPLX: Auto Create Purchase Orders (PO's): [UI]:  'Date' Format present on 'Auto Create POs'")
	public void Test10_1091322() {
		Assert.assertFalse(
				test.purchaseDashboardActions.verifyRadioButton("Create automatically based on schedule",
						"createAutomaticallyBasedOnSchedule"),
				"[ASSERTION FAILED]: Create automatically based on schedule radio button does not exist on modal screen.");
		test.purchaseDashboardActions.verifyDaysButtonOnModalScreen(Days, "btn btn-day");

	}
}
