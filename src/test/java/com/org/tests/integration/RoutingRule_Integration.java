package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class RoutingRule_Integration extends BaseTest {
	String dataEnteredName, priorityName, code;

	@Test(priority = 1, description = "VPLX:Manage Routing Rules:[UI][Integration] - To verify When "
			+ "a new Routing rule is created, it gets populated under Rules dropdown "
			+ "on Location Management screen"
			+ "\n&\n"
			+ "VPLX: Manage Routing Rules:[UI]:[Integration]: To verify that that there is a Delete button "
			+ "corresponding to each routing rule and user is able to Delete "
			+ "(If not mapped to an Item in Locations)"
			+ "\n&\n"
			+ "VPLX: Manage Routing Rules:[UI]:[Integration]: -To verify Error message "
			+ "when trying to Delete Rule already associated with location of an item")
	public void Test01_1111567_1047842_1047882(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.verifyDeleteLinkUnderActionColumn();
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"), "search");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		
		test.landingPageActions.navigateToFeature("Main Menu");
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
	
	
	@Test(priority = 2, description = "Obsolete: VPLX:Manage Routing Rules:[UI][Integration] - "
			+ "When a new Routing rule is created, it gets displayed on Location Management screen")
	public void Test02_1111668(Method method) {
		test.supportDataActions.clickViewRuleItemLocation();
		test.supportDataActions.verifyNewlyAddedRecordNameInListRoutingRule(
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("close");
	}
	
	
	@Test(priority = 3, description = "VPLX:Manage Routing Rules:[UI][Integration] -To verify "
			+ "When a Routing rule name is updated, it gets populated under Rules dropdown "
			+ "on Location Management screen")
	public void Test03_1111568(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
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
		test.landingPageActions.navigateToFeature("Main Menu");
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
	
	
	@Test(priority = 4, description = "Obsolete: VPLX:Manage Routing Rules:[UI][Integration] - "
			+ "When a new Routing rule name is updated, it gets updated on Location Management screen"
			+ "\n&\n"
			+ "VPLX:Manage Routing Rules:[UI][Integration] - To verify When a Routing rule is updated "
			+ "against Trans Priority,Schedule or Destination, the details gets updated on View Rules screen")
	public void Test04_1111669_1111672(Method method) {
		test.supportDataActions.clickViewRuleItemLocation();
		test.supportDataActions.verifyNewlyAddedRecordNameInListRoutingRule(
				TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName2"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("close");
	}
	
	
	@Test(priority = 5, description = "VPLX: Manage Routing Rules: [UI][Integration]: To verify When a "
			+ "Destination status is changed to inactive, it is not displayed in Add/Edit Routing Rule screen"
			+ "\n&\n"
			+ "VPLX : Manage Transaction Priorities: [UI]: [Integration]: To verify that when a new "
			+ "Transaction Priority is added, the same Transaction Priority is displayed on "
			+ "Pick Routing rule Screen")
	public void Test05_1111464_1106947(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit-0");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.DosageForm"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.landingPageActions.navigateToFeature("Main Menu");
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
	
	
	@Test(priority = 6, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: "
			+ "To verify that when the Transaction Priority code or name is updated for User defined priority, "
			+ "the updated values get displayed on Pick Routing Rule screen")
	public void Test06_1106948(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
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
		
		test.landingPageActions.navigateToFeature("Main Menu");
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
	
	
	@Test(priority = 7, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: "
			+ "To verify that when a Transaction priority is made inactive, the Transaction priority "
			+ "gets removed from the already existing Routing rule")
	public void Test07_1106950(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), "search");
		test.siteConfigurationAction.clickButton("edit");
		test.siteConfigurationAction.clickCheckboxOfExistingRecord();
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Main Menu");
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
