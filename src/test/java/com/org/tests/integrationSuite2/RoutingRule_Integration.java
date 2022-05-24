package com.org.tests.integrationSuite2;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class RoutingRule_Integration extends BaseTest {
	String dataEnteredName, priorityName, code;

	@Test(priority = 1, description = "VPLX:Manage Routing Rules:[UI][Integration] - When a new Routing rule is created, it gets populated under Rules dropdown on Location Management screen")
	public void Test01_1111567_1047842_1047882(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.verifyDeleteLinkUnderActionColumn();
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"), "search");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"search");
		test.supportDataActions
				.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.supportDataActions.verifyDropdownElementsDefaultRule("pickRoutingRuleKey_1",
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName")));
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRule("pickRoutingRuleKey_0",
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName1")));
	}

	@Test(priority = 2, description = "VPLX:Manage Routing Rules:[UI][Integration] - When a Routing rule is updated against Trans Priority,Schedule or Destination, the details gets updated on View Rules")
	public void Test02_1111668(Method method) {
		test.supportDataActions.clickViewRuleItemLocation();
		test.supportDataActions.verifyNewlyAddedRecordNameInListRoutingRule(
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("close");
	}

	@Test(priority = 3, description = "VPLX:Manage Routing Rules:[UI][Integration] - When a new Routing rule name is updated, it gets populated under Rules dropdown on Location Management screen")
	public void Test03_1111568(Method method) {
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"), "search");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		String routingRuleName = test.siteConfigurationAction
				.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName2", routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName2", routingRuleName);
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"search");
		test.supportDataActions
				.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.supportDataActions.verifyDropdownElementsDefaultRule("pickRoutingRuleKey_0",
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2")));
	}

	@Test(priority = 4, description = "VPLX:Manage Routing Rules:[UI][Integration] - When a new Routing rule name is updated, it gets updated  on Location Management screen")
	public void Test04_1111669_1111672(Method method) {
		test.supportDataActions.clickViewRuleItemLocation();
		test.supportDataActions.verifyNewlyAddedRecordNameInListRoutingRule(
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("close");
	}

	@Test(priority = 5, description = "VPLX: Manage Routing Rules: [UI][Integration]: When a Destination status is changed to inactive, it is not displayed in Add/Edit Routing Rule screen")
	public void Test05_1111464_1106947(Method method) {
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit-0");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.DosageForm"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"), "search");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.supportDataActions
				.verifyNewlyRemovedRecord(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.supportDataActions
		.verifyNewlyAddedRecordNameInListRoutingRule(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions
				.verifyNewlyRemovedRecord(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.supportDataActions
		.verifyNewlyAddedRecordNameInListRoutingRule(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
	}
	
	@Test(priority = 6, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: When the Transaction Priority code or name is updated for User defined priority, the updated values get displayed on Pick Routing Rule screen")
	public void Test06_1106948(Method method) {
		
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), "search");
		test.siteConfigurationAction.clickButton("edit");
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"), "search");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.supportDataActions
				.verifyNewlyAddedRecordNameInListRoutingRule(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions
				.verifyNewlyAddedRecordNameInListRoutingRule(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
	}
	
	@Test(priority = 7, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: When a Transaction priority is made inactive, the Transaction priority gets removed from the already existing Routing rule")
	public void Test07_1106950(Method method) {
		
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.clickCheckboxOfExistingRecord();
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"), "search");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.supportDataActions
				.verifyNewlyRemovedRecord(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions
				.verifyNewlyRemovedRecord(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
	}
}
