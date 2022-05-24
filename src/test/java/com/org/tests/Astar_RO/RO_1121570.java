package com.org.tests.Astar_RO;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RO_1121570 extends BaseTest {

	String OrderName_1,OrderName_2,itemName,FacilityName,DestinationName,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName,barcode,productID,OrderName1;
	ArrayList<String> previous_data, sorted_data;

	
	@Test(priority = 4, description = "VPLX : User can log in to Remote Order Form after UserID change.")
	public void Test04_1121570(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : User can log in to Remote Order Form after UserID change.");
		
		itemName= TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickRecordNameToEdit(DestinationName);
		
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.NewUserName"));
		test.siteConfigurationAction.clickButton("save");
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(15);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUserName2"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(10);
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		
	}
	
}
