@ignore
Feature: Manage Destination

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_destination
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	* def destinationCode = javaUtil.systemTimeInMills()
	
	Given headers req_headers
		And path destinationPostPath, facilityKey
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/destination.json')
		And set bodyOfRequest.destinationCode = destinationCode
		And set bodyOfRequest.descriptionText = descriptionText
		And set bodyOfRequest.facilityKey = facilityKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.destinationKey == '#present'
	* def destinationKey = response.destinationKey
	