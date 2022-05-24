Page Title: Pyxis Logistics

#Object Definitions
======================================================================================================================================
inp_username:css:#email
inp_password:css:#Password
inp_ip:id:ipAddress
btn_signin:xpath:.//button[@type='submit']	
link_username:xpath:.//a[contains(text(), '${username}')]	
button_logout:xpath:.//button[contains(text(), '${button}')]	
select_tenant:xpath://select[@name='Tenant']	
button_next:xpath://button[@type='submit']
second_sort:xpath:.//select[@name='${fieldName}']	 
wrong_credentials:xpath:.//div[contains(@class,'error')]//*[text()='Invalid Email/Password.']  
======================================================================================================================================