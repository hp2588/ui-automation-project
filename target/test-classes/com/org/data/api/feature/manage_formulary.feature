@ignore
Feature: Manage Formulary

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_formulary
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + adminUserOpaqueToken)' }
	* def itemId = javaUtil.systemTimeInMills()
	
	Given headers req_headers
		And path formularyPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/formulary.json')
		And set bodyOfRequest.itemId = itemId
		And set bodyOfRequest.dispensingFormKey = dispensingFormKey
		And set bodyOfRequest.dispensingUnitKey = dispensingUnitKey
		And set bodyOfRequest.medicationClassKey = medicationClassKey
		And set bodyOfRequest.externalSystemKey = externalSystemKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def formularyKey = response.key