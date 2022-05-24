@ignore
Feature: Manage Medication Class

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_medication_class
Scenario:
	* def medicationClassCode = javaUtil.systemTimeInMills()
	* def medicationClassDesc = '[API][karate]-Desc.Text-' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path medClassPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/med_class.json')
		And set bodyOfRequest.medicationClassCode = medicationClassCode
		And set bodyOfRequest.descriptionText = medicationClassDesc
		And set bodyOfRequest.externalSystemKey = externalSystemKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
	* def medicationClassKey = response