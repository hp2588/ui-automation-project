@ignore
Feature: DbUtils

Background:
# Create JDBC connection with DbUtils java class

Scenario:
	* def config = { username: 'ruser', password: 'PnvrMDi7eARpmfQq3HEIlhpw2qiPYENw', url: 'jdbc:sqlserver://wus1-qa-bd-devops-vplx-sql.database.windows.net;databaseName=SiteConfigurationTenant1', driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver' }
	* def DbUtils = Java.type('com.bd.data.api.util.DbUtils')
	* def db = new DbUtils(config)
	* def test = db.readRows("exec sp_set_session_context @key=N'TenantKey', @value= 'c0b7444f-31e4-4f36-b875-b4eaf21b1ca1';SELECT *  FROM [dbo].[ScheduleTiming]")
	* print test