package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class ManageDistributorFeature extends BaseTest {
	
	String firstData, dataEnteredName, dataEnteredCode, dataEnteredName1, dataEnteredCode1, old_data, new_data;
	
	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]: User is able to add new Distributor")
	public void Test01_1130014(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]: User is able to add new Distributor");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
	}
	
	
	@Test(priority = 2, description = "VPLX: Distributors [UI]: Verify the \"*Indicates required fields\" "
			+ "message is displayed in the Add Distributor dialog")
	public void Test02_1032947(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Indicate required fields message on Add Distributor page.");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.supportDataActions.verifyRequiredField("* Indicates required fields");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}
	
	
	@Test(priority = 3, description = "VPLX: Distributors [UI]: The user is able to edit/update values "
			+ "on the Ordering and Contact tabs in the Distributor dialog")
	public void Test03_1130013(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]: User is able to edit/update value in any tab under Distributor");
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.verifyAndClickContactTab("Contact");
		test.supportDataActions.enterValueOnAddDistributorPage("cityName", "NoidaCity");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
	}

}
