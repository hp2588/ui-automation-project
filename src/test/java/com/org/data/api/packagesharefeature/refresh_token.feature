@ignore
Feature: Generate IDM Refresh Token

Background:
* url idmBaseURL

@support_user_token
Scenario:
	* path '/ids/connect/token' 
	* form field grant_type = 'refresh_token' 
	* def oldRefreshToken = read('classpath:com/bd/data/api/json/support_user_refresh_token.txt')
	* form field refresh_token = oldRefreshToken
	* form field client_id = 'pyxisone_logistics_hybrid_qa'
	* form field client_secret = 'secret' 
	* method post
	* status 200 
	* def accessToken = response.access_token 
	* def refreshToken = response.refresh_token
	* def fileUtils = Java.type('com.intuit.karate.FileUtils')
	* fileUtils.writeToFile(new java.io.File('src/test/java/com/bd/data/api/json/support_user_refresh_token.txt'), refreshToken)

@admin_user_token
Scenario:
	* path '/ids/connect/token' 
	* form field grant_type = 'refresh_token' 
	* def oldRefreshToken = read('classpath:com/bd/data/api/json/admin_user_refresh_token.txt')
	* form field refresh_token = oldRefreshToken
	* form field client_id = 'pyxisone_logistics_hybrid_qa'
	* form field client_secret = 'secret' 
	* method post
	* status 200
	* def accessToken = response.access_token 
	* def refreshToken = response.refresh_token
	* def fileUtils = Java.type('com.intuit.karate.FileUtils')
	* fileUtils.writeToFile(new java.io.File('src/test/java/com/bd/data/api/json/admin_user_refresh_token.txt'), refreshToken)
	