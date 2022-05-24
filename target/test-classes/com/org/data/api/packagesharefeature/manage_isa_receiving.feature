@ignore
Feature: Manage Computer

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_computer
Scenario:
	* def computerName = '[API][karate]-Computer-' + javaUtil.systemTimeInMills()
	* def ipAddress = javaUtil.ipAdderss()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
		
	Given headers req_headers
		And path iSAPostPath, facilityKey, 'isas'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/isa.json')
		And set bodyOfRequest.descriptionText = shortDescriptionText
		And set bodyOfRequest.shortDescriptionText = shortDescriptionText
		And set bodyOfRequest.facilityKey = facilityKey
		And set bodyOfRequest.logisticsLabelPrinterKey = logisticsLabelPrinterKey
		And set bodyOfRequest.workstationComputerKey = workstationComputerKey
		And set bodyOfRequest.computers[0].logisticsLabelPrinterKey = logisticsLabelPrinterKey
		And set bodyOfRequest.computers[0].workstationComputerKey = workstationComputerKey
		And request bodyOfRequest
	When method post	
			Then status 201
			And match response == '#present'
			And match $.key == '#present'
	* def isaKey = response.key['object']