package com.ibm.demo1.configuration;


import javax.naming.NamingException;
import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.jndi.JndiTemplate;




//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class EmployeeDSConfig {
//	@Bean(destroyMethod="") 
//    public DataSource dataSource() {
//	  final DataSource ds = new JndiDataSourceLookup().getDataSource("jdbc/j4s");
//        return ds;
//    } 
//	
	@Bean
	public DataSource dataSource() {
	    DataSource ds = null;
	    JndiTemplate jndi = new JndiTemplate();
	    try {
	        ds = jndi.lookup("java:comp/env/jdbc/j4s", DataSource.class);
	        System.out.print("ds"+" "+ds);
	    } catch (NamingException e) {
	       e.printStackTrace();
	    }
	    return ds;
	}
//	
//	@Bean
//	public AnnotationMBeanExporter annotationMBeanExporter() {
//	    AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
//	    annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
//	    return annotationMBeanExporter;
//	}

}
