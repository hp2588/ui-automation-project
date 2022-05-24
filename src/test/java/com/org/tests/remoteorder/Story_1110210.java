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

public class Story_1110210 extends BaseTest{
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
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		  
		FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		itemQuantity = getData("RemoteWebOrder.itemQuantity");
		
	}
	
	
	@Test(priority = 1, description = "VPLX: Remote Ordering: [UI]:The Destination dropdown is displayed  value in dropdown \"<facility name>-<destination name>\" on home page of remote web application")
	public void Test01_1087264(Method method)  {
		
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", "Select Facility - Destination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote Ordering: [UI]:The Destination dropdown on top left corner is displayed the \"<facility name>-<destination name>\" on ViewAllOrder page of remote web application")
	public void Test02_1087264(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		test.remoteWebOrderActions.verifyDropDownFieldOnViewAllOrderPage("selectDestination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		
		
	}

}
