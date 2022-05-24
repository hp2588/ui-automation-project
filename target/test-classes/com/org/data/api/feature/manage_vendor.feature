@ignore
Feature: Manage Vendor

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_vendor
Scenario:
	* def vendorShortCode = javaUtil.systemTimeInMills()
	* def vendorDesc = '[API][karate]-Desc.Text-' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path vendorPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/vendor.json')
		And set bodyOfRequest.shortCode = vendorShortCode
		And set bodyOfRequest.descriptionText = vendorDesc
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.vendorKey == '#present'
	* def vendorKey = response.vendorKey