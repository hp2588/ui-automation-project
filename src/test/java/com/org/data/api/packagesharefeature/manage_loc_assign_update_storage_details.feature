@ignore
Feature: Manage Location Assignment

Background:
* url baseURL

@update_storage_details
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path updateStorageDetailsPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/update_storage_details.json')
		And set bodyOfRequest.assignmentItems[0].facilityKey = facilityKey
		And set bodyOfRequest.assignmentItems[0].isaKey = isaKey
		And set bodyOfRequest.itemKey = formularyKey
		And set bodyOfRequest.assignmentItems[0].storageSpaceKey = storageSpaceKey
		And set bodyOfRequest.assignmentItems[0].storageSpaceItemKey = storageSpaceItemKey
		And set bodyOfRequest.assignmentItems[0].storageSpaceInventoryKey = storageSpaceInventoryKey
		And set bodyOfRequest.assignmentItems[0].location = itemLocation
		And request bodyOfRequest
	When method put
			Then status 200
			And match response == '#present'