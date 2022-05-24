function fn(){
	
	var env=karate.env; // get java system property 'karate.env'
	karate.log('karate.env system property was:', env);
	
	if(!env){
		env=uat; // a custom 'intelligent' default
	}
	
	var config = { //base config JSON
		
		idmBasURL: 'https://idm-test.net',
		apiMgmtBaseURL:'https://url-azzure-api.net',
		postpath: '/api/v1/resource'
	};
	
	dbconfig = {
		driverClassName: 'com.microsoft.sqlserver.jdbc.SWLServerDriver'
	};
	
	//over-ride only those that need to be
	if(env=='qa'){
		config.baseRL = 'https://services.io';
		dbconfig.username='ruser'
		dbconfig.password='T43r43r43r34r3'
		dbconfig.url='jdbc:sqlserver;//dburl.database.windows.net'
	}else if(env=='stage'){
		config.baseRL = 'https://services.io';
		dbconfig.username='ruser'
		dbconfig.password='T43r43r43r34r3'
		dbconfig.url='jdbc:sqlserver;//dburl.database.windows.net'
	}
	
	
	// don't waste time waiting for a connection or if servers don't respond within 5 seconds
	karate.configure('connectTimeout',10000);
	karate.configure('readTimeout',10000);
	return config;
}