@ignore
Feature: Manage Facility

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_facility
Scenario:
	* def facilityName = 'FacRec' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path facilityPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/packagesharejson/facility.json')
		And set bodyOfRequest.facilityName = facilityName
		And set bodyOfRequest.facilityCode = facilityCode
		And set bodyOfRequest.pharmacyInformationSystemKey = externalSystemKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def facilityKey = response.key