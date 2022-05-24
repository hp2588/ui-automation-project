@ignore
Feature: Manage Location Assignment

Background:
* url baseURL

@get_storage_details
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path getStorageDetailsPath, facilityKey, 'inventory'
		And param medItemKey = formularyKey
	When method get
			Then status 200
			And match response == '#present'
			* def storageSpaceInventoryKey = response[0].storageSpaceInventoryKey
			* def storageSpaceItemKey = response[0].storageSpaceItemKey
			* def location = response[0].location
			* def medItemName = response[0].medItemName
			
			