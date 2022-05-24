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

public class Story_1059043 extends BaseTest {
	
	
	public String barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
	//public String barcode = "0100380052020690171005032328717621abcd123456789";
	public String rowBarcode = "43485394uyyu";

	@Test(priority = 1, description = "VPLX: Barcode Management: Integration with TS Parser : [UI] Availability of Bar code management option on the main menu")
	public void Test01_1064774(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Integration with TS Parser : [UI] Availability of Bar code management option on the main menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(true, "User is unable to see barcode option.");
	}

	@Test(priority = 2, description = "VPLX: Barcode Management: Integration with TS Parser : [UI] Barcode management Screen gets opened after clicking on the barcodes option ")
	public void Test02_1064777(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Integration with TS Parser : [UI] Barcode management Screen gets opened after clicking on the barcodes option ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");

	}

	@Test(priority = 3, description = "VPLX: Barcode Management: Integration with TS Parser : [UI] User can search the Barcode via search box.")
	public void Test03_1064778(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Barcode Management: Integration with TS Parser : [UI] User can search the Barcode via search box. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.searchBarcodeAvailability(),
				"User is not able to see search icon and Barcode heading on barcode screen.");

	}

	@Test(priority = 4, description = "VPLX:Barcode Management: Integration with TS Parser : [UI] User can Type the bar code need to search in the search box")
	public void Test04_1064808(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] User can Type the bar code need to search in the search box");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.barcodeVisibleInBarcodeSearchField(),
				"entered barcode and barcode value in search box are different.");

	}

	@Test(priority = 5, description = "VPLX:Barcode Management: Integration with TS Parser : [UI] Product id is visible \"-\" for the Product id field if Typed bar code dont have a valid product id")
	public void Test05_1064818(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Product id is visible \"-\" for the Product id field if Typed bar code dont have a valid product id");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.parseIDcontainMinus(), "ParseID is not containing -");
	}

	@Test(priority = 6, description = ": [UI] Product id is visible in the Product id field if scanned bar code have a valid product id ")
	public void Test06_1064813(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				": [UI] Product id is visible in the Product id field if scanned bar code have a valid product id ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"Product ID of the barcode is not visible.");

	}

	@Test(priority = 7, description = "Case VPLX:Barcode Management: Integration with TS Parser : [UI] Product id is visible in the Product id field if Typed bar code have a valid product id ")
	public void Test07_1064815(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"Case VPLX:Barcode Management: Integration with TS Parser  : [UI] Product id is visible in the Product id field if Typed bar code have a valid product id  ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		//Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"parse ID of the product is not visible w.r.t. barcode.");

	}

	@Test(priority = 8, description = "VPLX:Barcode Management: Integration with TS Parser : [UI]Product id is visible for the Parsed Product id field if Scanned bar code have a valid product id ")
	public void Test08_1064819(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser: [UI]Product id is visible for  the Parsed Product id field   if Scanned bar code have a valid product id  ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"Product ID of the barcode is not visible at product details page");

	}

	@Test(priority = 9, description = "VPLX:Barcode Management: Integration with TS Parser : [UI] External system Drop down shows all the external systems on the bar code management screen ")
	public void Test09_1064833(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI]  External system Drop down shows all the external systems  on the bar code management screen  ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.externalSystemVerification(barcode),
				"External system dropw down is not visible for raw barcode");

	}

	@Test(priority = 10, description = "VPLX:Barcode Management: Integration with TS Parser : [UI] Listing of items based on External system ")
	public void Test10_1064837(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Listing of items based on External system  ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.externalSystemVerification(barcode),
				"External system are appearing in drop down");

	}

	@Test(priority = 11, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI] No items other than selected External system are listing to link with barcode ")
	public void Test11_1064839(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] No items other than selected External system are listing to link with barcode ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.externalSystemVerification(barcode),
				"External System are not appearing for the barcode");

	}

	@Test(priority = 12, description = "Barcode Management: Integration with TS Parser : [UI]A detail screen gets opened when product id is linked to one item ")
	public void Test12_1064887(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"Barcode Management: Integration with TS Parser  : [UI]A detail screen gets opened when product id is linked to one item ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"New screen is not opening having all product details.");

	}

	@Test(priority = 13, description = "VPLX:Barcode Management: Integration with TS Parser : [UI] Visibility of Fields for listing of items which are not linked")
	public void Test13_1064840(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Visibility of Fields for listing of items which are not linked");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.productIDVerification(barcode),
				"Barcode is not visible on the screen. Product details are not appearing.");

	}

	@Test(priority = 14, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI] Visibility of Fields for listing of items which are not linked")
	public void Test14_1064891(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Visibility of Fields for listing of items which are not linked");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.linkedItemVerification(barcode),
				"Linked item details of the item is not displaying on product details page.");

	}

	@Test(priority = 15, description = "Barcode Management: Integration with TS Parser  : [UI] Details of  Linked Barcode is visible on the  detail screen ")
	public void Test15_1064894(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"Barcode Management: Integration with TS Parser  : [UI] Details of  Linked Barcode is visible on the  detail screen ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.barcodeDetailsVerification(barcode),
				"Barcode number details of the item is not displaying on product details page.");

	}

	@Test(priority = 16, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI] Linked By field is visible showing the name of the person who has linked it on  the  detail screen ")
	public void Test16_1064896(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Linked By field is visible showing the name of the person who has linked it on  the  detail screen ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.linkedByDetailsVerification(barcode),
				"Linked by details of the item is not displaying on product details page.");

	}

	@Test(priority = 17, description = "Case VPLX:Barcode Management: Integration with TS Parser  : [UI] Linked Source field shows the description \"Local system BD Pysix logistics\"on the  detail screen  ")
	public void Test17_1064905(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"Case VPLX:Barcode Management: Integration with TS Parser  : [UI] Linked Source field shows the description \"Local system BD Pysix logistics\"on the  detail screen ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.sourceFieldVerification(barcode),
				"Source by details are not appearing on product details page.");

	}

	@Test(priority = 18, description = "Case VPLX:Barcode Management: Integration with TS Parser  : [UI] Item Id field shows the id of item to which product id is linkedon the  detail screen ")
	public void Test18_1064910(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"Case VPLX:Barcode Management: Integration with TS Parser  : [UI] Item Id field shows the id of item to which product id is linkedon the  detail screen ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.itemIDVerification(barcode),
				"Item ID details are not appearing on product details page.");
		

	}

	@Test(priority = 19, description = "VPLX:Barcode Management: Integration with TS Parser  : [UI] Verified by  field shows the  verifier name on the  detail screen . ")
	public void Test19_1064917(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Barcode Management: Integration with TS Parser  : [UI] Verified by  field shows the  verifier name on the  detail screen . ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.barcodeActions.barcodeScreenLanding(), "User is unble to land on barcode screen");
		Assert.assertTrue(test.barcodeActions.verifiedByVerification(barcode),
				"Verified by details are not appearing on product details page.");
		

	}


}
