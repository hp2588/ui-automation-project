@ignore
Feature: Manage Location Assignment

Background:
* url baseURL

@assign_formulary_location
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path assignFormularyLocationPath, facilityKey, 'isas', isaKey, 'storagespaces', storageSpaceKey, 'assign'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/assign_formulary_location.json')
		And set bodyOfRequest.itemId = itemId
		And set bodyOfRequest.medItemKey = formularyKey
		And request bodyOfRequest
	When method post
			Then status 202
			And match response == '#present'
			