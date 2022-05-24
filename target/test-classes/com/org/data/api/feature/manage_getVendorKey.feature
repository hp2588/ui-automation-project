@ignore
Feature: Get Vendor Key

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@get_vendor_key
Scenario:
	* def vendorName = 'Pkg-Sharing_' + facilityName
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path , facilityKey, 'workstationcomputers'
		And set bodyOfRequest.computerName = computerName
		And set bodyOfRequest.ipAddress = ipAddress
		And set bodyOfRequest.facilityKey = facilityKey
		And request bodyOfRequest
	When method get	
			Then status 201
			And match response == '#present'
			And match $.key == '#present'
	* def vendorKey = response.vendorKey