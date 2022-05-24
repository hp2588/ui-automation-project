@ignore
Feature: Manage Schedule

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_schedule
Scenario:
	* def scheduleName = 'API karate Schedule ' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path schedulePostPath, facilityKey, 'pickschedules'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/schedule.json')
		And set bodyOfRequest.name = scheduleName
		And set bodyOfRequest.facilityKey = facilityKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def scheduleKey = response.key