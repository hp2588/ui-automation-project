@ignore
Feature: Manage Printer

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')
* def DbUtils = Java.type('com.bd.data.api.util.DbUtils')

@create_a_printer
Scenario:
	* def printerName = 'PrinterRec' + javaUtil.systemTimeInMills()

	* def dbConfigSiteConfiguration = dbconfig
	* dbConfigSiteConfiguration.url = dbConfigSiteConfiguration.url + 'databaseName=SiteConfigurationTenant1'
	 
	* def dbUtils = new DbUtils(dbConfigSiteConfiguration)
	* def printerModelKey = dbUtils.readValue("exec sp_set_session_context @key=N'TenantKey', @value='" + tenantKey + "';SELECT top 1 LogisticsLabelPrinterModelKey from LogisticsLabelPrinterModel")

	
	* def serverPrinterName = '[API][karate]-ServerPrinterName-' + javaUtil.systemTimeInMills()
	* def ipAddress = javaUtil.ipAdderss()
	* def ipPort = javaUtil.randomNum(4)
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path printerPostPath, facilityKey, 'printers'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/printer.json')
		And set bodyOfRequest.printerName = printerName
		And set bodyOfRequest.facilityKey = facilityKey
		And set bodyOfRequest.printerModelKey = printerModelKey
		And set bodyOfRequest.serverPrinterName = serverPrinterName
		And set bodyOfRequest.ipAddress = ipAddress
		And set bodyOfRequest.ipPort = ipPort
		And request bodyOfRequest
	When method post
	Then status 201
		And match $.key == '#present'
	* def printerKey = response.key