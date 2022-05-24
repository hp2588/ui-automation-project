@ignore
Feature: Manage Computer

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_computer
Scenario:
	* def computerName = 'ComputerRec' + javaUtil.systemTimeInMills()
	* def ipAddress = javaUtil.ipAdderss()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
		
	Given headers req_headers
		And path computerPostPath, facilityKey, 'workstationcomputers'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/computer.json')
		And set bodyOfRequest.computerName = computerName
		And set bodyOfRequest.ipAddress = ipAddress
		And set bodyOfRequest.facilityKey = facilityKey
		And request bodyOfRequest
	When method post	
			Then status 201
			And match response == '#present'
			And match $.key == '#present'
	* def computerKey = response.key