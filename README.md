# JNDI_ENCRYPT
1.Tomcat tomcat/conf/server.xml modification in GlobalNamingResources
```xml
  <Resource auth="Container"
            signleton="false"
		        factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl"
			      driverClassName="YourDATBASEDriver" global="jdbc/j4s"
			      maxActive="20" maxIdle="0" maxWait="10000" name="jdbc/j4s"
			      password="yourEncryptedPassword" type="javax.sql.DataSource"
			      url="yourDATABASEURL" username="root" />
```
            
2.Tomcat  tomcat/conf/context.xml modification
```xml
  <ResourceLink auth="Container" 
                global="jdbc/j4s" 
                name="jdbc/j4s" 
                type="javax.sql.DataSource"/>
```

3.To get the encrypted password call this API
	[URL]/api/getencryption/{yourpassword}
