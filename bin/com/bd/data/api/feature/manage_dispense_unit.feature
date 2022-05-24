@ignore
Feature: Manage Dispense Unit

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_dispense_unit
Scenario:
	* def dispenseUnitName = '[API][karate]-Dispense Unit Name-' + javaUtil.systemTimeInMills()
	* def dispenseUnitCode = javaUtil.systemTimeInMills()
	* def dispenseUnitDesc = '[API][karate]-Dispense Unit Desc-' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path dispenseUnitPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/dispense_unit.json')
		And set bodyOfRequest.name = dispenseUnitName
		And set bodyOfRequest.dispenseUnitCode = dispenseUnitCode
		And set bodyOfRequest.descriptionText = dispenseUnitDesc
		And set bodyOfRequest.externalSystemKey = externalSystemKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def dispenseUnitKey = response.key
	