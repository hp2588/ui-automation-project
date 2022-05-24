@ignore
Feature: Manage Location Assignment

Background:
* url baseURL

@get_layout
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path getLayOutPath, facilityKey, 'isas', isaKey, 'storagespaces', 'layout'
	When method get	
			Then status 200
			And match response == '#present'
			* def storageSpaceKey = response['storageSpaces'][0]['childStorageSpaces'][0]['childStorageSpaces'][0]['childStorageSpaces'][0]['storageSpaceKey']