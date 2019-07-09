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

import com.ibm.demo1.domain.Student;
import com.ibm.demo1.repository.student.StudentRepository;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory", 
						transactionManagerRef = "secondTransactionManager",
//								 basePackages = "com.ibm.demo1.repository.student"
						basePackageClasses = StudentRepository.class
								 )
						
@EnableTransactionManagement
public class StudentDSConfig {

	@Value("${jndilook.name2}")
	String jndiName;

	@Bean(name = "second-db")
	public DataSource mySQLDataSource() {
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

	@Bean(name = "secondEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(final EntityManagerFactoryBuilder builder,
			final @Qualifier("second-db") DataSource dataSource) {
		return builder.dataSource(dataSource).packages(Student.class).persistenceUnit("secondDb").build();
	}

	@Bean(name = "secondTransactionManager")
	public PlatformTransactionManager secondTransactionManager(
			@Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
		return new JpaTransactionManager(secondEntityManagerFactory);
	}
}
