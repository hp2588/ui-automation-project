@ignore
Feature: Manage Routing Rule

Background:
* url baseURL
* def javaUtil = Java.type('com.bd.data.api.util.Util')

@create_a_routing_rule
Scenario:
	* def routingRuleName = '[API][karate]-Routing Rule-' + javaUtil.systemTimeInMills()
	* def actorKey = javaUtil.uuidStr()
	* def req_headers = { Authorization: '#("Bearer " + opaqueToken)' }
	
	Given headers req_headers
		And path routingRulePostPath, facilityKey, 'routingrule'
		And def bodyOfRequest = read('classpath:com/bd/data/api/json/routing_rule.json')
		And set bodyOfRequest.routingRuleName = routingRuleName
		And set bodyOfRequest.routingRuleSchedules[0].scheduleKey = scheduleKey
		And set bodyOfRequest.actorKey = actorKey
		And request bodyOfRequest
	When method post
	Then status 201
		And match response == '#present'
		And match $.key == '#present'
	* def routingRuleKey = response.key