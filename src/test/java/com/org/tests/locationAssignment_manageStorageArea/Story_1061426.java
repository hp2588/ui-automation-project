package com.org.tests.locationAssignment_manageStorageArea;
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

public class Story_1061426 extends BaseTest {
	
	@Test(priority = 1, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset If the source shelf has less bins than the target shelf")
	public void Test01_1150230(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset If the source shelf has less bins than the target shelf");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(), "User is not able to apply offset properly.");

	}
	
	@Test(priority = 2, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset if the source shelf has more bins than the target shelf")
	public void Test02_11050227(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset if the source shelf has more bins than the target shelf");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.MinQtyvsMaxQty(), "User is not able to apply offset properly");

	}
	@Test(priority = 3, description = "VPLXLocation Assignment - Managing Storage Area : [UI]-Validation message is displayed when max value is entered less than min.")
	public void Test03_1044512(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLXLocation Assignment - Managing Storage Area : [UI]-Validation message is displayed when max value is entered less than min.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editQOH(), "User is not able to set values");

	}
	@Test(priority = 4, description = "VPLX:Location Assignment - Managing Storage Area : [UI]-Validation message is displayed when user enter value of min greater than max")
	public void Test04_1044511(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area : [UI]-Validation message is displayed when user enter value of min greater than max");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyItemsID_Facility_QOH(), "User is not able to set values");

	}
	
	@Test(priority = 5, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System allows a user to edit the replenishment options for CATO ISA.")
	public void Test05_1151355(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System allows a user to edit the replenishment options for CATO ISA.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editReplenish(), "User is not able to edit the replenish");
	}

	@Test(priority = 6, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays the ISA Type as Internal or External on edit location screen..")
	public void Test06_1151356(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays the ISA Type as Internal or External on edit location screen.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.getISAList(), "User is not able to see the ISa listed");
	}
	@Test(priority = 7, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to add upto 50 Raks/Shelves/Bins.")
	public void Test07_1151384(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to add upto 50 Raks/Shelves/Bins..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addSelf(), "User is not able to shelf");
	}
	
	@Test(priority = 8, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User is not allowed to remove vertical/horizontal dividers from the selected bin if there are items assigned to slots within the bin..")
	public void Test08_1151384(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User is not allowed to remove vertical/horizontal dividers from the selected bin if there are items assigned to slots within the bin.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.removeRack(), "User is not able to remove rack");
	}
	
	@Test(priority = 9, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - Action is only visible to the user  if it is applicable ..")
	public void Test09_1151390(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - Action is only visible to the user  if it is applicable ..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionsonSelfAction(), "User is not able to see the required options");
	}
	@Test(priority = 10, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User print bin labels for bin 4 when iem is assigned to a grouped bin 4,5 and 6..")
	public void Test10_1151391(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User print bin labels for bin 4 when iem is assigned to a grouped bin 4,5 and 6..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionson(), "User is not able to see the required options");
	}
	
	@Test(priority = 11, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - System prints a slot label for each slot for the grouped bin when item is assigned to grouped bin 4,5,6..")
	public void Test11_1151392(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - System prints a slot label for each slot for the grouped bin when item is assigned to grouped bin 4,5,6..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addSlotsOptions(), "User is not able to see the required options");
	}
	
	@Test(priority = 12, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User will not get option for print and will not be able to print for the slot which does not have item assigned to it.")
	public void Test12_1151393(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] - User will not get option for print and will not be able to print for the slot which does not have item assigned to it.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addSlotsOptions(), "User is not able to see the required options");
	}
	
	
	

}
