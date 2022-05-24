
Feature: Manage Facility Vendor Option

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_facility_vendor_option
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	* def vendorShortCode = javaUtil.systemTimeInMills()
	* def vendorAccountCode = javaUtil.systemTimeInMills()
	* def descriptionText = '[API][karate]-Desc.Text-' + javaUtil.systemTimeInMills()
	
	Given headers req_headers
		And path facilityVendorOptionPostPath, facilityKey, 'vendors', 'options'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/facility_vendor_option.json')
		And set bodyOfRequest[0].shortCode = vendorShortCode
		And set bodyOfRequest[0].descriptionText = descriptionText
		And set bodyOfRequest[0].vendorAccountCode = vendorAccountCode
		And set bodyOfRequest[0].vendorKey = vendorKey
		And request bodyOfRequest
	When method post
	Then status 200
		And match response == '#present'
		And match $[0].facilityVendorOptionKey == '#present'
	* def facilityVendorOptionKey = $[0].facilityVendorOptionKey