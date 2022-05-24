package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981975 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify enabled filter icon displayed on location management page")
	public void Test01_1059503(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify enabled filter icon displayed on location management page");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");

	}

	@Test(priority = 2, description = "VPLX:Location-Storage Area: UI:Verify filter items by pop up should be displayed on clicking  on filter icon")
	public void Test02_1059505(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify filter items by pop up should be displayed on clicking  on filter icon");
		test.siteConfigurationAction.clickButtonOnEditLocation("filter_location_btn");
		test.siteConfigurationAction.verifyFilterItemsPopup();

	}

	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify filter items by pop up containing the column as attribute,operator and value")
	public void Test03_1059508(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify filter items by pop up containing the column as attribute,operator and value");
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("field",
				getData("FilterItems.attribute"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("type",
				getData("FilterItems.operator"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("filter",
				getData("FilterItems.value"));

	}

	@Test(priority = 5, description = "VPLX:Location-Storage Area: UI:Verify User will have +  and - option "
			+ " for  adding a row and - for removing the  row")
	public void Test05_1059511(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify User will have +  and - option "
						+ " for  adding a row and - for removing the  row");
		test.siteConfigurationAction.verifyButtonOnEditLocation("list_addAction");
		test.siteConfigurationAction.verifyButtonOnEditLocation("list_deleteAction");

	}

	@Test(priority = 4, description = "VPLX:Location-Storage Area: UI:Verify User  able to filter based on the following fields: Attribute as active True or False,Dosage form,Dispense unit,medication class")
	public void Test04_1059517(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify User  able to filter based on the following fields: Attribute as active True or False,Dosage form,Dispense unit,medication class");
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("field",
				getData("FilterItemsLocation.attribute"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("type",
				getData("FilterItemsLocation.operator"));
		test.siteConfigurationAction.verifyDefaultValueinFiltersPopuponItemScreen("filter",
				getData("FilterItemsLocation.value"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("field", "1",
				getData("FilterItemsLocation.attribute1"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("type", "1",
				getData("FilterItemsLocation.operator2"));
		test.siteConfigurationAction.selectValueFromDropDownForFilterItemFields("filter", "1",
				getData("FilterItemsLocation.value3"));
		test.siteConfigurationAction.clickButton("add");

		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("field",
				getData("FilterItemsLocation.attribute1"));
		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("type",
				getData("FilterItemsLocation.operator2"));
		test.siteConfigurationAction.selectValueFromDropDownForMultipleFilterItemFields("filter",
				getData("FilterItemsLocation.value3"));

	}

	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify clear all filters and apply filters enabled button should be displayed on filter items by pop up")
	public void Test06_1059509(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify clear all filters and apply filters enabled button should be displayed on filter items by pop up");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button");

	}

	@Test(priority = 7, description = "VPLX:Location-Storage Area: UI:Verify count on filter icon when multiple filters are applied")
	public void Test07_1067762(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify count on filter icon when multiple filters are applied");
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifycountIsPresentonFiltericononLocation();

	}
}
