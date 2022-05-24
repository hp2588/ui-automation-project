package com.org.tests.mainmenu.externalsystems;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;

@Listeners(com.org.listeners.TestListener.class)

public class Story_977305 extends BaseTest {

	String[] external_sys_checkboxes_id = { /*"pisProvidesMedClassFlag",*/ "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };

	String external_System, external_System_new,app_url,systemType;
	
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
	
	@Test(priority = 1, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The default system type is PIS")
	public void Test01_1049425() {
				test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemSystemType");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType"));
	}

	@Test(priority = 2, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The checkboxes are enabled for default system type is PIS"
			+ ""
			+ "VPLX: Manage Heathcare System [UI]: External Systems-Add: The checkboxes available are Provide Medclass ,Provide Therapeutic Class,"
			+ " Allow Pharmacy Formulary Edit, Allow scan code item link edit, Ignore PIS Item ")
	public void Test02_Test03_1049430_1049454() {
		
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsEnabled(external_sys_checkboxes_id);
	}

	@Test(priority = 4, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The checkboxes can be unchecked for default system type is PIS")
	public void Test04_1049445() {
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsAreUnchecked(external_sys_checkboxes_id);

	}

	@Test(priority = 5, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The checkboxes are disabled for system type CCE")
	public void Test05_1049432() {
	
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType2"));
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);
	}

	@Test(priority = 6, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The default time zone is system time zone")
	public void Test06_1049436() {
				test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));
	}

	@Test(priority = 7, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: user is able to add External System successfully")
	public void Test07_1049445() {
		// System.out.println("Data from YAML::
		// "+getData("ExternalSystem.Name")+System.currentTimeMillis());
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		systemType=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("systemType", 0);
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);

		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		external_System_new = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System_new);

	}

	@Test(priority = 8, description = "VPLX: Manage Health Care System: Feature Testing: [UI]: Adding new External system with name space.")
	public void Test8_1053000() {
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("externalSystemName", " ");
		test.siteConfigurationAction.verifyInputFieldOnAddingSpaces("externalSystemName",
				getData("ErrorMessage.InputExternalSystemName"));
	}

	/*Bug logged : ID: Bug 1084337*/
	@Test(priority = 9, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: user is NOT able to add duplicate External Systems")
	public void Test09_1049455() {
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("externalSystemName",
				external_System);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingExternalSystem(getData("ErrorMessage.ESalreadyExist1"));

	}

	@Test(priority = 10, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add: The user is able to toggle to add active/ Inactive record")
	public void Test10_Test11_1039625_1049401() throws Throwable {
		
		//Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"), "[ASSERTION FAILED]: Toggle is inactive on Add/Edit External Screen");
		test.siteConfigurationAction.verifyToggleIsActive("isActive");
		test.siteConfigurationAction.clickActiveToggle("Active");
		//Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"), "[ASSERTION FAILED]: Toggle is active on Add/Edit External Screen");
		test.siteConfigurationAction.verifyToggleIsInActive("isActive");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}
	
	@Test(priority = 12, description = "VPLX: Manage Healthcare System [UI]: External Systems-Edit: The default system type is the one selected while adding External System")
	public void Test12_1049456() {
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		Assert.assertEquals(systemType, getData("ExternalSystem.SystemType"));
		
	}
	

	
	@Test(priority = 13, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: The checkboxes are enabled for default system type is PIS")
	public void Test13_1049458() {
				
		/*test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType"));*/
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsEnabled(external_sys_checkboxes_id);
	}

	@Test(priority = 14, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: The checkboxes are disabled for system type CCE")
	public void Test14_1049459() {
		
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType2"));
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);
	}

	@Test(priority = 15, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: The default time zone is system time zone")
	public void Test15_1049460() {
		
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));
	}

	@Test(priority = 16, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: The checkboxes can be unchecked for default system type is PIS")
	public void Test16_1049498() {
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsAreUnchecked(external_sys_checkboxes_id);
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}

	
	@Test(priority = 17, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: user is NOT able to edit with existing External Systems")
	public void Test17_1049499() {
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType"));
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("externalSystemName",
				external_System_new);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingExternalSystem(getData("ErrorMessage.ESalreadyExist"));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();

	}
	
	@Test(priority = 18, description = "VPLX: Manage Healthcare System [UI]: External Systems-Edit : Error message appears if user tries to add"
			+ " External System with existing Name is Upper Case")
	public void Test18_1130213() {
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("externalSystemName",
				external_System_new.toUpperCase());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingExternalSystem(getData("ErrorMessage.ESalreadyExist"));
		test.siteConfigurationAction.verifyAddPrinterPopupGetsClosedOnClickingCancelButton();
	}

	
	@Test(priority = 19, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: user is able to edit External System successfully")
	public void Test19_1049497() throws InterruptedException {
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);

	}

	
	@Test(priority = 20, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: The user is able to toggle to add active/ Inactive record")
	public void Test20_1049405() throws Throwable {
		
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		test.siteConfigurationAction.verifyToggleIsActive("isActive");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("isActive");
		test.siteConfigurationAction.clickActiveToggle("Active");
	}

}
