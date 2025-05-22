package com.example.demoExamen.config;

import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class grabs the appropriate values for OracleDataSource, The method that
 * uses env, grabs it from the environment variables set in the docker
 * container. The method that uses dbSettings is for local testing
 */
@Configuration
public class OracleConfiguration {

    Logger logger = LoggerFactory.getLogger(DbSettings.class);

    @Autowired
    private DbSettings dbSettings;

    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();

        // Set connection properties
        Properties props = new Properties();
        props.setProperty("oracle.net.ssl_server_dn_match", "false");
        props.setProperty("oracle.net.ssl_version", "1.2");
        props.setProperty("oracle.jdbc.fanEnabled", "false");

        // For local testing
        ds.setDriverType("thin");
        ds.setURL(dbSettings.getUrl());
        logger.info("Using URL: " + dbSettings.getUrl());
        ds.setUser(dbSettings.getUsername());
        logger.info("Using Username: " + dbSettings.getUsername());
        ds.setPassword(dbSettings.getPassword());
        ds.setConnectionProperties(props);

        return ds;
    }
}
