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

public class Story_1001699 extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;

	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		
		  
		  FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
	}
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-Authorization: A validation message must be display if user is not authorized and try to login in Remote order application")
	public void Test01_1092493(Method method)  {
		test.loginPageAction.LoginToTheBDApplication(getData("InvalidEmail.invalid1").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		test.loginPageAction.verifyErrorMessageForWrongCredentials();

	}

	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-Authorization: Remote order authorized user must have all associated permissions and roles")
	public void Test02_1092527(Method method)  {
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		

	}
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]: Login with user which is not mapped to any destination")
	public void Test03_1135058(Method method)  {
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUserNameWihoutDest").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		test.remoteWebOrderActions.verifyMessageWhenUserMappingIsMissing();
		

	}
}
