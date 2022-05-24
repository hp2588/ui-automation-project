package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class HSDM_Test extends BaseTest {
	
	String migrationName, idnKey;
	
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
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	}

	@Test(priority = 1, description = "VPLX:Add Faciity")
	public void Test01_HSDM_Test(Method method) {
		test.landingPageActions.navigateToFeature("Data setup");
		test.siteConfigurationAction.clickOnAddButtonToAddMigration();
		test.siteConfigurationAction.selectValueForDropDown("pisKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("formularyKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("migrationName");
		migrationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"migrationName", "AutoMig" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("idnKey");
		idnKey = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("idnKey",
				getData("HSDM.idnKey").trim());
		test.siteConfigurationAction.clickStartMigrationButton(migrationName);
		TestDataPropertyReaderAndWriter.setProperty("MigrationName", migrationName);
		test.siteConfigurationAction.verifyCompletedStatusForMigrationAfterTwoMin(TestDataPropertyReaderAndWriter.getProperty("MigrationName").trim(),"Completed");
	}

}
