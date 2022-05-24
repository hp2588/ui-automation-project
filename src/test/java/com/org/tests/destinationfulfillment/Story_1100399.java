package com.org.tests.destinationfulfillment;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1100399 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Facility selection on Destination Fulfillment.")

	public void Test01_1121083(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: User permission to Destination Fulfillment.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");

		Assert.assertTrue(test.destinationFulfillmentActions.verifyDestinationFulfillmentHeading(),
				"User is not successfully landed on Destination Fulfillment page");

	}

	@Test(priority = 2, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Facility selection on Destination Fulfillment.")

	public void Test02_1121099(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Facility selection on Destination Fulfillment.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		Assert.assertTrue(test.destinationFulfillmentActions.verifyFacilitySectionOnDestinationFulfillment(),
				"Facility selection on Destination Fulfillment is not displayed.");

	}

	@Test(priority = 3, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Report and destination drop down selection on Destination Fulfillment.")

	public void Test03_1121106(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Report and destination drop down selection on Destination Fulfillment.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		Assert.assertTrue(test.destinationFulfillmentActions.verifyReportSectionOnOnDerstinationFulfillment(),
				"Report section on Destination Fulfillment is not found.");
		// Destination dropdown verification has been already done in Test02_1121099.

	}

	@Test(priority = 4, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Dates field with respect to facility on destination fulfillment screen.")
	public void Test04_1121395(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Dates field with respect to facility on destination fulfillment screen.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		Assert.assertTrue(test.destinationFulfillmentActions.verifyDateFieldAppear(),
				"Date field is not appreard for report discrepancies. ");

	}

	@Test(priority = 5, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: View report button on selecting discrepancy report type.")
	public void Test05_1121398(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: View report button on selecting discrepancy report type.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		Assert.assertTrue(test.destinationFulfillmentActions.verifyViewReportButton(),
				"View report button is not found on Destination fulfilment for reporting.");

	}

	@Test(priority = 6, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: View report button enabled and disabled functionality.")
	public void Test06_1121401(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: View report button enabled and disabled functionality.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		Assert.assertTrue(test.destinationFulfillmentActions.reportvViewButtonEnableCheck(),
				"view report button is found disabled");

	}

	

	@Test(priority = 7, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Total number of discrepancies available to be displayed.")
	public void Test07_1121415(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Total number of discrepancies available to be displayed.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		assertTrue(test.destinationFulfillmentActions.getDiscrepanciesReport(),
				"Report module is working fine.Case gets fail due to data unavailability");

	}

	@Test(priority = 8, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Actions of  view, print and save discrepancy.")
	public void Test08_1121416(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Actions of  view, print and save discrepancy.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		assertTrue(test.destinationFulfillmentActions.getDiscrepanciesReport(),
				" Reporting functiona is working fine, however reports are not available for this particular facility and Destination.");

	}

	

	@Test(priority = 9, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: From-date can be set to the past 7 days before the current date.")
	public void Test09_1121422(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: From-date can be set to the past 7 days before the current date.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Discrepancies();
		assertTrue(test.destinationFulfillmentActions.start_endDateMatch(),
				" Start date and end date are matching by default.");

	}
}
