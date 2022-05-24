package com.org.mainmenu.manualpick;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;


public class Story_977016 extends BaseTest{
	
	String firstname,middlename,lastname,comment,bed,room,order,mrn,accnum,destination,priority;
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

	}
*/
	@Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the Additional Information toggle on Add Pick page")
	public void Test01_1016383(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Additional Information toggle on Add Pick page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("AddPick.IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		/*test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0); */
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyAndClickAddPick();
	//	test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.verifyAdditionalToggle();
	    
	} 
	
	@Test(priority = 2, description = "VPLX:Manual Pick[UI]-User verifies the functionality of Additional Information toggle on Add Pick page")
	public void Test02_1016385(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the functionality of Additional Information toggle on Add Pick page");
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    test.transactionQueueActions.verifyInputfield("patient_last_name");
	    test.transactionQueueActions.verifyInputfield("patient_first_name");
	    test.transactionQueueActions.verifyInputfield("patient_middle_name");
	    test.transactionQueueActions.verifyInputfield("room");
	    test.transactionQueueActions.verifyInputfield("bed");
	    test.transactionQueueActions.verifyInputfield("comments");
	    test.transactionQueueActions.verifyInputfield("visit_number");
	    test.transactionQueueActions.verifyInputfield("mrn");
	    test.transactionQueueActions.verifyInputfield("order_number");
	    
	}
	
	@Test(priority = 3, description = "VPLX:Manual Pick[UI]-User verifies the field First Name on Add Pick page")
	public void Test03_1016386(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field First Name on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Patient First Name");
	    firstname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    
	}
	
	@Test(priority = 4, description = "VPLX:Manual Pick[UI]-User verifies the field Middle Name on Add Pick page")
	public void Test04_1016387(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Middle Name on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Patient Middle Name");
	    middlename=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_middle_name", middlename);
	    
	    
	    
	    
	} 
	
	@Test(priority = 5, description = "VPLX:Manual Pick[UI]-User verifies the field Last Name on Add Pick page")
	public void Test05_1016388(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Last Name on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Patient Last Name");
	    lastname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_last_name", lastname);
	    
	}

	@Test(priority = 6, description = "VPLX:Manual Pick[UI]-User verifies the field Visit number on Add Pick page")
	public void Test06_1016389(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Account Name on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Visit Number");
	    accnum=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("account_number", accnum);
	    
	}
	
	@Test(priority = 7, description = "VPLX:Manual Pick[UI]-User verifies the field MRN on Add Pick page")
	public void Test07_1016390(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field MRN on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("MRN");
	    mrn=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("MRN", mrn);
	    
	} 
	
	@Test(priority = 8, description = "VPLX:Manual Pick[UI]-User verifies the field Order Number on Add Pick page")
	public void Test08_1016391(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Order Number on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Order Number");
	    order=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("order_number", order);
	    
	  
	} 
	
	@Test(priority = 9, description = "VPLX:Manual Pick[UI]-User verifies the field Room on Add Pick page")
	public void Test09_1016392(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Room on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Room");
	    room=test.transactionQueueActions.getAlphaNumericString(15);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("room", room);
	    
	} 
	
	@Test(priority = 10, description = "VPLX:Manual Pick[UI]-User verifies the field Bed on Add Pick page")
	public void Test10_1016393(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Bed on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Bed");
	    bed=test.transactionQueueActions.getAlphaNumericString(15);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("bed", bed);
	    
	}
	
	@Test(priority = 11, description = "VPLX:Manual Pick[UI]-User verifies the field Comments on Add Pick page")
	public void Test11_1016397(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the field Comments on Add Pick page");
	    test.transactionQueueActions.verifylabelforAddPickFields("Comments");
	    comment=test.transactionQueueActions.getAlphaNumericString(1000);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("comments", comment);
	    
	} 
	
	// //Bug 1102724 logged for Search Transactions
	@Test(priority = 12, description = "VPLX:Manual Pick[UI]-User verifies the transaction in current pick with selected priority")
	public void Test12_1016400(Method method)
	
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the transaction in current pick with selected priority");
		  test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		   priority= test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
		  destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn","Save & Close");
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
	}

	 //Bug 1102724 logged for Search Transactions
	@Test(priority = 13, description = "VPLX:Manual Pick[UI]-User clicks on save & Close button without entering additional information on Add Pick page")
	public void Test13_1016399(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User clicks on save & Close button without entering additional information on Add Pick page");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage(),
				"[ASSERTION FAILED]: User is not navigated Transaction Queue page");
		test.transactionQueueActions.verifyAndClickAddPick();
	//	test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	   priority= test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	   destination= test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.verifyAdditionalToggle();
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
	} 
	
	
	@Test(priority = 14, description = "VPLX:Manual Pick[UI]-User verifies the confirm message on clicking cancel button")
	public void Test14_1016401(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the confirm message on clicking cancel button");
		test.transactionQueueActions.verifyAndClickAddPick();
	//	test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	    test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	    test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.verifyAdditionalToggle();
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
	    test.transactionQueueActions.verifyCancelPopupOnAddPick(getData("AddPick.ConfirmMessage"));
	    test.transactionQueueActions.clickConfirmPopupButton();
	    
	    
	}
	
	@Test(priority = 15, description = "VPLX:Manual Pick[UI]-User verifies the Save & Add button on Add Pick page")
	public void Test15_1016402(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Save & Add button on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
	//	test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	    test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	    test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.verifyAdditionalToggle();
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    lastname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_last_name", lastname);
	    middlename=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_middle_name", middlename);
	    accnum=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("visit_number", accnum);
	    mrn=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("MRN", mrn);
	    order=test.transactionQueueActions.getAlphaNumericString(20);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("order_number", order);
	    room=test.transactionQueueActions.getAlphaNumericString(15);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("room", room);
	    bed=test.transactionQueueActions.getAlphaNumericString(15);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("bed", bed);
	    comment=test.transactionQueueActions.getAlphaNumericString(1000);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("comments", comment); 
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_add_btn", "Save & Add");
	//	test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
	    
	}
	
	@Test(priority = 16, description = "VPLX:Manual Pick[UI]-User verifies the placeholders value with visual design")
	public void Test16_1028989(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Save & Add button on Add Pick page");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.verifyAdditionalToggle();
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	  test.transactionQueueActions.verifyPlaceholderValues("patient_first_name", getData("AddPick.Placeholder_FirstName"));
	    test.transactionQueueActions.verifyPlaceholderValues("patient_last_name", getData("AddPick.Placeholder_LastName"));
	    test.transactionQueueActions.verifyPlaceholderValues("patient_middle_name", getData("AddPick.Placeholder_MiddleName"));
	    test.transactionQueueActions.verifyPlaceholderValues("room", getData("AddPick.Placeholder_Room"));
	    test.transactionQueueActions.verifyPlaceholderValues("bed", "02");
	    test.transactionQueueActions.verifyPlaceholderValues("comments", getData("AddPick.Placeholder_Comments"));
	    test.transactionQueueActions.verifyPlaceholderValues("visit_number", getData("AddPick.Placeholder_Visit"));
	    test.transactionQueueActions.verifyPlaceholderValues("mrn", getData("AddPick.Placeholder_MRN"));
	    test.transactionQueueActions.verifyPlaceholderValues("order_number", getData("AddPick.Placeholder_Order"));

	    
	}
	
	

}


