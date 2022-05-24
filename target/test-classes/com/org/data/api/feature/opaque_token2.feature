@ignore
Feature: Generate IDM Opaque Token

Background:
* url 'https://idm-test-uswest2-idmsts.azurewebsites.net'

Scenario:
	* configure followRedirects = true
	
	* def req_headers = { Content-Type: 'application/x-www-form-urlencoded' }
	* def credentials = { Username: 'testvanguard01@vanguard.com', __RequestVerificationToken: 'CfDJ8IQofqSqNxpDv_L60_38A3Q_kcZvtpWx-fYgcYhvC39ZDQRe0Svxghsl53V4l9ZpQ0VXh-0dq7m0bv_xqZoGyRguc2lGbEpSFw0oT_iErO9cLWQOI6CDGJ2CR6vlmfh-tV4a0WK4DVwt1wnlobirdzA', ReturnUrl: '/ids/connect/authorize/callback?response_type=code&redirect_uri=https://logistics.qa.logistics.bdservices.io&client_id=pyxisone_logistics_hybrid_qa&scope-openId= openid profile idmauthz.readuserpermissions idmauthz.access idmuserapi.access idmuserapi.readusers ReadClient offline_access', ClientSignInUrl: 'https://idm-test-uswest2-idmsts.azurewebsites.net/ids/Login?ReturnUrl=/ids/connect/authorize/callback?response_type=code&redirect_uri=https://logistics.qa.logistics.bdservices.io&client_id=pyxisone_logistics_hybrid_qa&scope-openId= openid profile idmauthz.readuserpermissions idmauthz.access idmuserapi.access idmuserapi.readusers ReadClient offline_access'}
	
	Given headers req_headers
	And path 'ids/Login?ReturnUrl=/ids/connect/authorize/callback?response_type=code&redirect_uri=https://logistics.qa.logistics.bdservices.io&client_id=pyxisone_logistics_hybrid_qa&scope-openId= openid profile idmauthz.readuserpermissions idmauthz.access idmuserapi.access idmuserapi.readusers ReadClient offline_access'
	And params {ReturnUrl: '/ids/connect/authorize/callback?response_type=code&redirect_uri=https://logistics.qa.logistics.bdservices.io&client_id=pyxisone_logistics_hybrid_qa&scope-openId= openid profile idmauthz.readuserpermissions idmauthz.access idmuserapi.access idmuserapi.readusers ReadClient offline_access'}
	And form fields credentials
	
	             			 
	When method post
		Then status 200
		* def location = responseHeaders['Location']
		* karate.log('******************************')
		* karate.log(location)
	
	