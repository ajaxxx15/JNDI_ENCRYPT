package com.ibm.demo1.configuration;





import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.repository.employee.EmployeeRepository;



@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager",
//        basePackages = "com.ibm.demo1.repository.employee"
        basePackageClasses = EmployeeRepository.class
)
@EnableTransactionManagement
public class EmployeeDSConfig {
	
	
	   @Value("${jndilook.name}")
	    String jndiName;

	    @Bean(name = "first-db")
	    @Primary
	    public DataSource OracleDataSource() {
	        DataSource ds = null;
	        JndiTemplate jndi = new JndiTemplate();
	        try {
	            ds = jndi.lookup(jndiName, DataSource.class);
	            System.out.println(ds);

	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	        return ds;
	    }

	   
	    @Bean(name = "firstEntityManagerFactory")
	    @Primary
	    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(final EntityManagerFactoryBuilder builder,
	                                                                            final @Qualifier("first-db") DataSource dataSource) {
	        return builder
	                .dataSource(dataSource)
	                .packages(Employee.class)
	                .persistenceUnit("firstDb")
	                .build();
	    }

	   
	    @Bean(name = "firstTransactionManager")
	    @Primary
	    public PlatformTransactionManager firstTransactionManager(@Qualifier("firstEntityManagerFactory")
	                                                              EntityManagerFactory firstEntityManagerFactory) {
	        return new JpaTransactionManager(firstEntityManagerFactory);
	    }


}
