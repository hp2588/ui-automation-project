package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982049 extends BaseTest {

	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	ArrayList<String> restocktransdetail1 = new ArrayList<String>();

/*	@Override
	@Test(priority = 0, description = "Login.")
	public void Open_Browser_Window() {

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameTenant1").trim(),
				getData("Auth.passwordTenant1").trim(), getData("Auth.ip3").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	} */

	@Test(priority = 1, description = "Making a single transaction active under Restock Queue")
	public void Test01_1010010(Method method)

	{
		ExtentTestManager.startTest(method.getName(), "Making a single transaction active under Restock Queue");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("AddRestock.IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
	/*	test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0); */
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyRestock();

		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		test.transactionQueueActions.selectRestockItem();
		//test.transactionQueueActions.selectRestockItem();
		restocktransdetail1 = test.transactionQueueActions.getFirstRestockTransactionDetails();
		
		System.out.println(restocktransdetail.equals(restocktransdetail1));
		

	}
	
	@Test(priority = 3, description = "VPLX:Transaction queue actions-Pick/Re-stock Now: [UI]: Active transaction becomes 'Pending' and 'Pending' Transaction becomes 'Active")
	public void Test04_1010031(Method method) {

		test.transactionQueueActions.selectRestockItem();
		restocktransdetail1 = test.transactionQueueActions.getFirstRestockTransactionDetails();
		System.out.println(restocktransdetail.equals(restocktransdetail1));
	}
	
	
	@Test(priority = 4, description = "Restock button becomes disable when selecting multiple transactions")
	public void Test02_1010030(Method method) {
		test.transactionQueueActions.verifyrestockButtonDisabled("Restock");

	}

	@Test(priority = 2, description = "Verify Active Restock Transaction")
	public void Test03_1010015(Method method) {

		test.transactionQueueActions.verifyActiveRestockTransaction();

	}

}
