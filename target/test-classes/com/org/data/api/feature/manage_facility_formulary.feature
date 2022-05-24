@ignore
Feature: Manage Facility Formulary

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_facility_formulary
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + adminUserOpaqueToken)' }
	
	Given headers req_headers
		And path facilityFormularyPostPath, formularyKey, 'facilities'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/facility_formulary.json')
		And set bodyOfRequest.facilitiesKeys[0] = facilityKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $[0].facilityKey == '#present'