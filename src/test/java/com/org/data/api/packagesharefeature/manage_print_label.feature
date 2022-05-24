@ignore
Feature: Manage Print Label

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')
* def DbUtils = Java.type('com.bd.data.api.util.DbUtils')

@create_a_print_label
Scenario:

	* def dbConfigLabelTemplate = dbconfig
	* dbConfigLabelTemplate.url = dbConfigLabelTemplate.url + 'databaseName=LabelTemplateTenant1'
	* def dbUtils1 = new DbUtils(dbConfigLabelTemplate)

	
	* def dbConfigSiteConfiguration = dbconfig
	* dbConfigSiteConfiguration.url = dbConfigSiteConfiguration.url + 'databaseName=SiteConfigurationTenant1'
	* def dbUtils2 = new DbUtils(dbConfigSiteConfiguration)
		
	* def labelCategory = dbUtils1.readRow("exec sp_set_session_context @key=N'TenantKey', @value='" + tenantKey + "';SELECT LabelCategoryKey, LabelCategoryCode FROM LabelCategory WHERE LabelCategoryCode='destbinlabel'")
	* def transactionPriority = dbUtils2.readValue("exec sp_set_session_context @key=N'TenantKey', @value='" + tenantKey + "';SELECT TransactionPriorityKey FROM TransactionPriority WHERE facilitykey = '" + facilityKey + "' AND TRANSACTIONPRIORITYCODE = 'PKGSHARE';")
	
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path printLabelPath, facilityKey, 'printLabels'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/print_label.json')
		And set bodyOfRequest.categoryKey = labelCategory['LabelCategoryKey']
		And set bodyOfRequest.labelcategorycode = labelCategory['LabelCategoryCode']
		And set bodyOfRequest.priorityAssignment[0].transactionPriorityKey = transactionPriority
		And set bodyOfRequest.stockKey = stockDefinitionKey
		And set bodyOfRequest.name = '[API][karate]-PrintLabel-' + javaUtil.randomNum(10)
		And request bodyOfRequest
		When method post
			Then status 201			
			And match response == '#present'
			And match $.printLabelKey == '#present'
	* def printLabelKey = response.printLabelKey
	