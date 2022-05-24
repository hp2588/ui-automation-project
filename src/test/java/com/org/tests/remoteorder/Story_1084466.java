package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1084466 extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;

	@BeforeClass
	// @Test(priority = 1, description = "VPLX: Remote ordering:[UI]- User is navigated to IDM login screen page while open remote order application.")
	public void Open_Browser_Window() 
	{
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());
		
		FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		itemQuantity = getData("RemoteWebOrder.itemQuantity");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-  User is navigated to IDM home screen page while logout from the application")
	public void Test02_1089108(Method method)  {
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserNameForLogout"), "Logout", "Confirm");
		
		// logging out takes time, so we need to wait for 30 seconds at least
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			
		}
		
		test.loginPageAction.verifyUserIsOnBDRemoteWebOrderLoginPage();
		
	}
	
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]- Validation message is displayed on providing invalid user or incorrect password while login from IDM")
	public void Test03_1089168(Method method)  {
		/*
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		*/
		
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUserInvalid").trim(), getData("Auth.ip").trim());
		test.loginPageAction.verifyErrorMessageForWrongCredentials();
		
	}
	

}
