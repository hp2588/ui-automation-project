package com.org.mainmenu.manualpick;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982180 extends BaseTest {

	String itemid;
	
/*
	@Override
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

	}*/
	

	@Test(priority = 1, description = "VPLX:Manual Pick[UI]-User enters the item name in search textbox so item is displayed in grid")
	public void Test01_1016464(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User enters the item name in search textbox so item is displayed in grid");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("AddPick.IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		//test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		//test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		//test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
	//    test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
		//test.transactionQueueActions.clickCancelButton();
	}

	
	 @Test(priority = 2, description ="VPLX:Manual Pick[UI]-User verifies the reset search criteria on Add Pick page")
	 public void Test02_1016536(Method method) {
	 
	 ExtentTestManager.startTest(method.getName(),
	 "VPLX:Manual Pick[UI]-User verifies the reset search criteria on Add Pick page"
	 ); 
	 test.transactionQueueActions.verifyAndClickAddPick();
	 test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName")); 
	 test.transactionQueueActions.clearText("search_item");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
	  }
	 
	
	@Test(priority = 4, description = "VPLX:Manual Pick[UI]-User verifies the \"Add Pick Transaction\" pop up message on Add Pick page")
	public void Test04_1016543(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the \"Add Pick Transaction\" pop up message on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
	  //  test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.clickCancelButton();
	}

	@Test(priority = 5, description = "VPLX:Manual Pick[UI]-User verifies the fields of search grid on Add Pick page")
	public void Test05_1016545(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the fields of search grid on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
	 //   test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchItemFields(getData("AddPick.ExpectedSearchHeader"));
		test.transactionQueueActions.clickCancelButton();
	}

	@Test(priority = 6, description = "VPLX:Manual Pick[UI]-User verifies the Pop Up message \"NO Result Found\" on Add Pick Page")
	public void Test06_1016552(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Pop Up message \"NO Result Found\" on Add Pick Page");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(getData("AddPick.IncorrectItemName"));
		test.transactionQueueActions.verifyNoResultFound(getData("AddPick.NoItemFoundMessage"));
		test.transactionQueueActions.clickCancelButton();
	}

	@Test(priority = 7, description = "VPLX:Manual Pick[UI]-User verifies the cancel button on Add Pick page")
	public void Test07_1025962(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the cancel button on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.clickCancelButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage(),
				"[ASSERTION FAILED]: User is not navigated Transaction Queue page");
	}

	@Test(priority = 8, description = "VPLX: [Feature Testing]: UI: Manual Pick: The items are searched correctly on the basis of item, item ID, brand name, Prefered NDC")
	public void Test08_1042858(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: [Feature Testing]: UI: Manual Pick: The items are searched correctly on the basis of item, item ID, brand name, Prefered NDC");
		test.transactionQueueActions.verifyAndClickAddPick();
	 //   test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage(),
				"[ASSERTION FAILED]: User is not navigated Transaction Queue page");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
		test.siteConfigurationAction.clearText("search_item");
		test.transactionQueueActions.searchItemValue(getData("AddPick.ItemID"));
		test.transactionQueueActions.verifySearchedResult("Item ID", getData("AddPick.ItemID"));
		test.siteConfigurationAction.clearText("search_item");
		test.transactionQueueActions.searchItemValue(getData("AddPick.PreferredNDC"));
		test.transactionQueueActions.verifySearchedResult("Preferred NDC", getData("AddPick.PreferredNDC"));
	} 

}
