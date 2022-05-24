package com.org.tests.mainmenu.labelsizedefinitions;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975753 extends BaseTest {
	
	String placeHolder, name, labelWidth, labelHeight, firstData;
	
	@Test(priority = 1, description = "VPLX:Define Label Stock:UI:Verify enable edit option available on the home page of stock definition for each item active/inactive when the toggle button turned on")
	public void Test01_1039111(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify enable edit option available on the home page of stock definition for each item active/inactive when the toggle button turned on");
		test.landingPageActions.navigateToFeature("Label Size Definitions");
		test.supportDataActions.verifyAddButtonOnPage();
		test.supportDataActions.clickAddButtonOnDistributor("add");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		//Assert.assertTrue(test.supportDataActions.verifyEditLinkOnLabel());
	}
	
	@Test(priority = 2, description = "VPLX:Define Label Stock:UI:Verify enable edit option available on the home page of stock definition for each item active when the toggle button turned off")
	public void Test02_1039112(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify enable edit option available on the home page of stock definition for each item active when the toggle button turned off");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		//Assert.assertTrue(test.supportDataActions.verifyEditLinkOnLabel());
	}
	
	@Test(priority = 3, description = "VPLX:Define Label Stock:UI:Verify edit functionality for active stock definition")
	public void Test03_1039117(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify edit functionality for active stock definition");
		test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelName"));
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelWidth"));
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelHeight"));
	}
	
	@Test(priority = 4, description = "VPLX:Define Label Stock:UI:Verify edit functionality for inactive stock definition")
	public void Test04_1039118(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify edit functionality for inactive stock definition");
		//test.supportDataActions.clickAddButtonOnDistributor("add");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickToggleButton("true", "isActive");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelName"));
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelWidth"));
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefilledValueForAnInputField("stockLabelHeight"));
	}
	
	@Test(priority = 5, description = "VPLX:Define Label Stock:UI:Verify editing functionality for full transaction of stock definition active/inactive")
	public void Test05_1039135(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify editing functionality for full transaction of stock definition active/inactive");
		test.supportDataActions.clickButton("edit");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(name, firstData);
	}

}
