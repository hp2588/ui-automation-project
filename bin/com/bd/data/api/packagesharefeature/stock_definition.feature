# Author: ashis.raj@globallogic.com
@ignore
Feature: Manage Stock Definition

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_stock_definition
Scenario:
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	* def descriptionText = '[API][karate]-Desc.Text-' + javaUtil.systemTimeInMills()
		
	Given headers req_headers
		And path stockDefPostpath
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/stock_definition.json')
		And set bodyOfRequest.descriptionText = descriptionText
		And request bodyOfRequest
	When method post
	Then status 201
		And match $.key == '#present'
	* def stockDefinitionKey = response.key