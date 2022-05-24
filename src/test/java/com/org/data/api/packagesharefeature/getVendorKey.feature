@ignore
Feature: Get Vendor Key

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@get_vendor_key
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path vendorPostPath
		And param isActive = false
		And param searchTerm = FacilityName
	When method get	
			Then status 200
			And match response == '#present'
			And match $.[0] == '#present'
	* def vendorKey = response[0].vendorKey