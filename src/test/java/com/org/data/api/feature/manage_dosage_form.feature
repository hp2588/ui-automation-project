@ignore
Feature: Manage Dosage Form

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_dosage_form
Scenario:
	* def dosageFormCode = javaUtil.systemTimeInMills()
	* def dosageFormDesc = '[API][karate]-Desc.Text-' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path dosagePostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/dosage_form.json')
		And set bodyOfRequest.dosageFormCode = dosageFormCode
		And set bodyOfRequest.descriptionText = dosageFormDesc
		And set bodyOfRequest.externalSystemKey = externalSystemKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def dosageFormKey = response.key
	