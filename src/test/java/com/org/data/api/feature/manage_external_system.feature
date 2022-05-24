@ignore
Feature: Manage External System

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_an_external_sysytem
Scenario:
	* def externalSystemName = '[API][karate]-ES-' + javaUtil.systemTimeInMills()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path esPostPath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/external_system.json')
		And set bodyOfRequest.externalSystemName = externalSystemName
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'	
	* def externalSystemKey = response