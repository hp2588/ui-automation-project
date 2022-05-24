package com.org.tests.integrationSuite1;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class GeneralLedger_Integration extends BaseTest {
	String glAccountName, glAccountNumber, externalSystem;
	
	@Test(priority = 1, description = "VPLX: GL Account: [UI] : User is able to see the update GL Account name ans same updated name gets populated under GL Account dropdown at Facility Level for already mapped item.")
	public void Test01_1040296(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : User is able to see the update GL Account name ans same updated name gets populated under GL Account dropdown at Facility Level for already mapped item.");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("GlAccountName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("GlAccountName"));
		test.supportDataActions.verifyInputFieldOnAddPopup("description");
		test.supportDataActions.verifyInputFieldOnAddPopup("number");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.resetSearch();
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(glAccountName);
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.siteConfigurationAction
		.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyDropdownElementsDefaultRule("glAccountKey",
				TestDataPropertyReaderAndWriter.getProperty("GlAccountName"));
	
	}
}
