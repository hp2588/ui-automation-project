package com.org.tests.barcode_Integration_Parser;
import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1064278 extends BaseTest {
	public String barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
	//public String barcode = "0100380052020690171005032328717621abcd123456789";
	public String rowBarcode = "43485394uyyu";
	
	@Test(priority = 1, description = "VPLX: Barcode Management: Integration with TS Parser: [UI]: Feature testing -Layout of Barcode management screen is as per UX")
	public void Test01_1078885(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Integration with TS Parser: [UI]: Feature testing -Layout of Barcode management screen is as per UX");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(true, "User is unable land on barcode screen");
		Assert.assertTrue(test.barcodeActions.searchBarcodeAvailability(), "UI of the barcode screen is not as per UI given.");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "UI of the barcode screen is not as per given UI");
	}
	

	@Test(priority = 2, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI]  \"Product Id\" field shows  the Product id on the  detail screen ")
	public void Test2_1064924(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI]  \"Product Id\" field shows  the Product id on the  detail screen");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"Barcode is not visible on the screen. Product details are not appearing.");

	}

	@Test(priority = 3, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI] Matched on  field shows the Product id or raw scan code based on linking  on the  detail screen  ")
	public void Test3_1064961(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Matched on  field shows the Product id or raw scan code based on linking  on the  detail screen ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.matchOnVerification(barcode),
				"Barcode is not visible on the screen. Product details are not appearing.");

	}
	
}
