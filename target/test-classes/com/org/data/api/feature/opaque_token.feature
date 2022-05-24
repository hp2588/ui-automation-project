@ignore
Feature: Generate IDM Opaque Token

Background:
* url apiMgmtBaseURL

Scenario:
	* def filename_with_scenario = (for_user == 'support' ? 'refresh_token.feature@support_user_token' : 'refresh_token.feature@admin_user_token')
	* def accessToken = call read(filename_with_scenario)
	
	* def req_headers = { Authorization: '#("Bearer " + accessToken.accessToken)' }
	Given headers req_headers
	And path '/idmproxy/opaquetoken'
	When method get
	Then status 200
	* def opaqueToken = response.opaqueToken
