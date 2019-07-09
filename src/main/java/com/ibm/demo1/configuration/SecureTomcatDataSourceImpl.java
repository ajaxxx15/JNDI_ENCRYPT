package com.ibm.demo1.configuration;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecureTomcatDataSourceImpl extends DataSourceFactory {	 
		

    private static final Log log = LogFactory.getLog(SecureTomcatDataSourceImpl.class);

    @Autowired
    private CipherEncrypte encryptor;
    
    

    public SecureTomcatDataSourceImpl() {
    	
    
     try {
            encryptor = new CipherEncrypte(); // If you've used your own secret key, pass it in...
        } catch (Exception e) {
            log.fatal("Error instantiating decryption class.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DataSource createDataSource(Properties properties, Context context, boolean XA) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, SQLException, NoSuchAlgorithmException,
            NoSuchPaddingException {
        // Here we decrypt our password.
   
        PoolConfiguration poolProperties = SecureTomcatDataSourceImpl.parsePoolProperties(properties);
        System.out.println(poolProperties.getPassword());
        poolProperties.setPassword(encryptor.decrypt(poolProperties.getPassword()));

        // The rest of the code is copied from Tomcat's DataSourceFactory.
        if (poolProperties.getDataSourceJNDI() != null && poolProperties.getDataSource() == null) {
            performJNDILookup(context, poolProperties);
        }

        org.apache.tomcat.jdbc.pool.DataSource dataSource =  new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
        
        String Name = SecureTomcatDataSourceImpl.getProperties("name").toString();
        String URL = poolProperties.getUrl();
        String username = poolProperties.getUsername();
        Logger.getLogger(SecureTomcatDataSourceImpl.class.getName()).log(Level.INFO, "Creating a New Connection Pool for DataSource "+Name+" with URL "+URL+" and the  username "+username);
        
        dataSource.createPool();

        return dataSource;
    }

}