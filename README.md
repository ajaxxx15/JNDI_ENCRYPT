# JNDI_ENCRYPT
1.Tomcat server.xml modification in <GlobalNamingResources>
  <Resource auth="Container"
            signleton="false"
		        factory="com.ibm.demo1.configuration.SecureTomcatDataSourceImpl"
			      driverClassName="com.mysql.cj.jdbc.Driver" global="jdbc/j4s"
			      maxActive="20" maxIdle="0" maxWait="10000" name="jdbc/j4s"
			      password="CO8rJE8dAuycKFSLxT/Seg==" type="javax.sql.DataSource"
			      url="jdbc:mysql://localhost:3306/testdb" username="root" />
            
2.Tomcat context.xml modification
  <ResourceLink auth="Container" 
                global="jdbc/j4s" 
                name="jdbc/j4s" 
                type="javax.sql.DataSource"/>
