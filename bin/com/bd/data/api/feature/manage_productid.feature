@ignore
Feature: Manage Product ID

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')
* def DbUtils = Java.type('com.bd.data.api.util.DbUtils')

@create_a_product_id
Scenario:

	* def productId = javaUtil.randomNum(10)
	* def scanCode = '01003' + productId + '0172410032321234521abcd123456789'

	* def dbConfigFormulary = dbconfig
	* dbConfigFormulary.url = dbConfigFormulary.url + 'databaseName=FormularyTenant1'
	* def dbUtils = new DbUtils(dbConfigFormulary)

	* def manufacturerKey = dbUtils.readValue("exec sp_set_session_context @key=N'TenantKey', @value='" + tenantKey + "';SELECT TOP 1 ManufacturerKey as ManufacturerKey FROM Manufacturer where ActiveFlag=1 order by ManufacturerKey desc;")

	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path productIdPostPath, formularyKey, 'productids'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/product_id.json')
		And set bodyOfRequest[0].productId = productId
		And set bodyOfRequest[0].scanCode = scanCode
		And set bodyOfRequest[0].manufacturerKey = manufacturerKey
		And set bodyOfRequest[0].manufacturerName = '[API][karate]-ManufacturerName-' + javaUtil.randomNum(10)
		And request bodyOfRequest
		When method put
			Then status 200
			And match response == '#present'