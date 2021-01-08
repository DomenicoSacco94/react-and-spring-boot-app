package com.doodle.chatchallenge.configurations;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * Class initializing the Hibernate <code>Datasource</code> and its properties
 */
@Configuration
@EnableTransactionManagement
public class HibernateConf {

    @Autowired
    private Environment environment;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.host}")
    private String host;

    @Value("${spring.datasource.port}")
    private String port;

    @Value("${spring.datasource.name}")
    private String name;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        List<String> profiles = Arrays.asList(this.environment.getActiveProfiles());

        //if tests are started, the DB connection through localhost is used, otherwise connects to the postgres-db set up by docker-composer
        if (profiles.contains("test")) {
            this.host = "localhost";
        }

        dataSource.setUrl("jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.name);

        dataSource.setUsername(this.user);
        dataSource.setPassword(this.password);

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/chat_schema_initializer.sql"));
        resourceDatabasePopulator.setContinueOnError(true);
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }


}

