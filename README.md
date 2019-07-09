# JNDI_ENCRYPT
1.Tomcat tomcat/conf/server.xml modification in GlobalNamingResources
```xml
  <Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" 
			factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl" maxActive="20" 
			maxIdle="0" maxWait="10000" name="jdbc/Mysql" password="Your_Encrpted_Password_" 
			singleton="false" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/testdb" 
			username="Your_User_name" />

<Resource auth="Container"
			driverClassName="oracle.jdbc.OracleDriver"
			factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl"
			maxActive="20" maxIdle="0" maxWait="10000" name="jdbc/Oracle"
			password="Your_Encrpted_Password_" singleton="false"
			type="javax.sql.DataSource"
			url="jdbc:oracle:thin:@//localhost:1521/XE" username="Your_User_name" />

```
            
2.Tomcat  tomcat/conf/context.xml modification
```xml
  <ResourceLink auth="Container" 
                global="jdbc/Mysql" 
                name="jdbc/Mysql" 
                type="javax.sql.DataSource"/>

 <ResourceLink auth="Container" 
                global="jdbc/Oracle" 
                name="jdbc/Oracle" 
                type="javax.sql.DataSource"/>
```
