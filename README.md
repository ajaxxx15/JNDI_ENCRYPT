# JNDI_ENCRYPT
1.Tomcat tomcat/conf/server.xml modification in GlobalNamingResources:-

 a.for ORACLE database
```xml
 <Resource auth="Container"
			 singleton="false" driverClassName="oracle.jdbc.OracleDriver"
			factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl"
			maxActive="20" maxIdle="10" maxWait="-1" name="jdbc/j4s2"
			username="DatabaseUserName" password="YourEncrptedPassword"
			type="javax.sql.DataSource" url="jdbc:oracle:thin:@//localhost:1521/XE"/>
```
b.for MYSQL database
```xml
<Resource auth="Container"
			singleton="false" driverClassName="com.mysql.cj.jdbc.Driver"
			factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl"
			maxActive="20" maxIdle="0" maxWait="10000" name="jdbc/j4s"
			username="DatabaseUserName" password="YourEncrptedPassword" 
			type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/testdb"/>
```
            
2.Tomcat  tomcat/conf/context.xml modification
```xml
  <ResourceLink auth="Container" 
                global="jdbc/j4s" 
                name="jdbc/j4s" 
                type="javax.sql.DataSource"/>
```

