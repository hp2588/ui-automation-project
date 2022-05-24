package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1110814 extends BaseTest{

	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Items","Item Id","Package Size","Daily Max","Ordered Today"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;

	// TODO - yugal
	@Test(priority = 1, description = "VPLX: Remote Ordering: [UI]: The 'Remote Orders' is removed as a category from the display page when user selects \"Group By Type\" on the Buyer's Dashboard.")
	public void Test01_1087264(Method method)  {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy","Group By Type");
		
		//code from hitesh
	}

}
