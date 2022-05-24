package com.org.tests.mainmenu.externalsystems;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1000040 extends BaseTest {
	
	String[] external_sys_checkboxes_id = { /*"pisProvidesMedClassFlag",*/ "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem, externalSystemUpdated,itemID,es_updated,Inactive_code,InactiveExternalSystem;
	String app_url;
	
	@BeforeClass
	public void Open_Browser_Window() {
	test = new TestSessionInitiator(this.getClass().getSimpleName());
	String app_url = getYamlValue("app_url");
	test.launchApplication(app_url);
	test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
	getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
	test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
	test.loginPageAction.clickNextButton();
	test.landingPageActions.navigateToMenu("Main Menu");
	Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
	"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	
	
	@Test(priority = 1, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The user searches External System on basis of columns External System Name")
	public void Test01_1039194() {
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add External System");

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("externalSystemSystemType", 1);
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);

		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));

		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "ES" + System.currentTimeMillis());
		
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		//test.supportDataActions.verifyNewlyAddedRecordNameInList();
		test.supportDataActions.enterSearchTermInSearchFieldGl(externalSystem, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(externalSystem, "1"));
		test.supportDataActions.clearSearchBoxField("search");
		
	}
	
	@Test(priority = 2, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The user is able to search External System by copy & pasting External System Name")
	public void Test02_1039239() {
		
		 es_updated=externalSystem.substring(0,6);
		 
			test.supportDataActions.enterSearchTermInSearchFieldGl(es_updated, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(es_updated, "1"));
		test.supportDataActions.clearSearchBoxField("search");
		
	}
	
	@Test(priority = 3, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The External Systems search "
			+ "displays the message 'No matches found' if the search does not return any results")
	public void Test03_1039226() {
				test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		
	}
	
	@Test(priority = 4, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: Sort icon disappears"
			+ " when no records are found as per searched text")
	public void Test04_1039244() {
		
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled("1"));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled("2"));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled("3"));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled("4"));
		
	}
	
	@Test(priority = 5, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The External Systems search is applied on the change")
	public void Test05_1039198() {
		test.supportDataActions.clearSearchBoxField("search");
		 es_updated=externalSystem.substring(0,6);
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(es_updated, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(es_updated, "1"));
		//test.supportDataActions.clearSearchBoxField("search");
	
	}
	
	@Test(priority = 6, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The option to reset External Systems search box appears when user types the text")
	public void Test06_1039203() {
		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());
	
	}
	
	@Test(priority = 7, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The External System search resets on clicking 'x' inside search box")
	public void Test07_1039213() {
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.verifyRecordList();
	}
	
	@Test(priority = 8, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The External Systems list gets updated"
			+ " on clicking toggle to exclude"
			+ " Inactive records provided the searched text is present in the search box")
	public void Test08_1039218() throws Throwable {
		test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add External System");

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("externalSystemSystemType", 1);
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);

		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));

		InactiveExternalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "UI-ES" + System.currentTimeMillis());
		 test.siteConfigurationAction.verifyToggleIsActive("isActive");
			test.siteConfigurationAction.clickActiveToggle("Active");
			
			test.siteConfigurationAction.verifyToggleIsInActive("isActive");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Status"));
		 test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Status"));
		Inactive_code = test.supportDataActions.getFirstInactiveExternalSystem();
		test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor(Inactive_code,"search");
		//test.supportDataActions.enterSearchTermInSearchFieldGl(Inactive_code, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(InactivedosageCode, "1"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		//Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
Assert.assertFalse(test.siteConfigurationAction.verifyInactiveAddedRecordNameInList(Inactive_code), "[Assertion Failed]: Inactive record is displayed though Inacitve toggle is off.");

}
	
	@Test(priority = 9, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The user is able to search External Systems "
			+ "description by typing spaces.")
	public void Test09_1039232() {
				test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchField("", "search");

	}

	
	
	
	@Test(priority = 10, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: The External Systems typed "
			+ "search works on basis of ShowInactive toggle button")
	public void Test10_1039249() throws Throwable {
	
		 test.siteConfigurationAction.verifyToggleIsInActive("toggle");
			test.siteConfigurationAction.clickActiveToggle("Show Inactive");
			
			test.siteConfigurationAction.verifyToggleIsActive("toggle");
			test.siteConfigurationAction.verifyActiveAndInactiveResults("Active", "Inactive", "2");
	}
	
	@Test(priority = 11, description = "VPLX: Manage Healthcare System [UI]: External Systems-Search: Typing in External System search box is restricted while results are being loaded")
	public void Test11_1040827() {
		
		test.supportDataActions.refreshPageWithoutLoader();
		test.supportDataActions.enterSearchTermInSearchFieldGl(externalSystem, "search");

	
	}
	
	
}
