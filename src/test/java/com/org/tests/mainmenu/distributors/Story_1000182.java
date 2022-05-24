package com.org.tests.mainmenu.distributors;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1000182 extends BaseTest {

	String firstData, dataEnteredName, dataEnteredCode, dataEnteredName1, dataEnteredCode1, old_data, new_data;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:User searches the list of distributors by distibutor Name.")
	public void Test01_1032752(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User searches the list of distributors by distibutor Name.");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		// test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
	}

	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:User searches the list of distributors by distibutor Type.")
	public void Test02_1032754(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User searches the list of distributors by distibutor Type.");
		test.supportDataActions.resetSearch();
		// if fails - remove resetSearch and add refresh page
		// test.supportDataActions.refreshPage();
		old_data = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField("Manual", "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(old_data, new_data);		
	}

	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:User verifies the reset search criteria of search for distributors textbox.")
	public void Test03_1032760(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the reset search criteria of search for distributors textbox.");
		test.supportDataActions.refreshPage();
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName1 = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode1 = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		
		test.supportDataActions.resetSearch();
		
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName1, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName1, new_data);
		
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 4, description = "VPLX:Manage Distributors:[UI]:User clicks on show Inactive toggle "
			+ "on distributors page.")
	public void Test04_1032806(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on show Inactive toggle on distributors page.");
		
		test.supportDataActions.refreshPage();
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatus());

	}

	@Test(priority = 5, description = "VPLX:Manage Distributors:[UI]:User switch off the show Inactive toggle on distributors page.")
	public void Test05_1032809(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User switch off the show Inactive toggle on distributors page.");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatusAsActive());
	}
	
	@Test(priority = 6, description = "VPLX:Manage Distributors:[UI]:Verify listing of Distributor displayed when entered Manual Order in search text box and edit the given distributor")
	public void Test06_1101725(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:Verify listing of Distributor displayed when entered Manual Order in search text box and edit the given distributor");
		test.supportDataActions.enterSearchTermInSearchField("Manual", "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		test.siteConfigurationAction.enterSearchTermInSearchField(new_data, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(new_data, "Edit Distributor");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}
	
	@Test(priority = 7, description = "VPLX:Manage Distributors:[UI]:User clicks on sort arrows after the search data on the columns display.")
	public void Test07_1032770(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on sort arrows after the search data on the columns display.");
		test.supportDataActions.refreshPage();
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.supportDataActions.verifyAndClickSortIcon("Name");
		test.supportDataActions.verifyAndClickSortIcon("Type");
		test.supportDataActions.verifyAndClickSortIcon("Status");
		
	}

}
