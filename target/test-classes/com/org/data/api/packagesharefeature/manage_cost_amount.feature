@ignore
Feature: Manage Product ID

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')
* def DbUtils = Java.type('com.bd.data.api.util.DbUtils')

@create_a_cost_amount
Scenario:

	* dbconfig.url = dbconfig.url + 'databaseName=FormularyTenant1'
	* def dbUtils = new DbUtils(dbconfig)
	* def productIdKey = dbUtils.readValue("exec sp_set_session_context @key=N'TenantKey', @value='" + tenantKey + "';SELECT ProductIDKey FROM ProductID WHERE ItemKey='" + formularyKey + "' order by ProductIDKey desc")

	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path costAmountPostPath, facilityKey, 'facilityformularies', formularyKey, 'productids'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/cost_amount.json')
		And set bodyOfRequest[0].vendorKey = vendorKey
		And set bodyOfRequest[0].productIDKey = productIdKey
		And request bodyOfRequest
		When method put
			Then status 200
			And match response == '#present'
			

