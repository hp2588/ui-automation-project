package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1059012 extends BaseTest{

	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Items","Item Id","Package Size","Daily Max","Ordered Today"};
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
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-Two tabs"
			+ "\"Create New Order\" and \"View All Orders\" are display at Landing page.")
	public void Test01_1066757(Method method)  {
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		Assert.assertTrue(test.remoteWebOrderActions.verifyTabs("Create New Order"));
		Assert.assertTrue(test.remoteWebOrderActions.verifyTabs("View All Orders"));
		test.remoteWebOrderActions.verifyTabs("Discrepancies Report");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		
	}

	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]- User select any destination by click on Drop down button at \"Select Facility - Destination\" box.")
	public void Test02_1066776(Method method)  {
		
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);	
		
	}
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]- User is not able to write from keyboard in \"select a destination\" text box.")
	public void Test03_1066789(Method method)  {
		
		test.remoteWebOrderActions.verifySelectDestinationIsADropdown("selectDestination");	
		
	}
}
