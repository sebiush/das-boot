package com.boot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

@Bean
@Primary
@ConfigurationProperties("spring.datasource")
public DataSourceProperties firstDataSourceProperties() {
return new DataSourceProperties();
}

@Bean
@Primary
@ConfigurationProperties("spring.datasource")
public DataSource firstDataSource(DataSourceProperties properties) {
return properties.initializeDataSourceBuilder()
.type(BasicDataSource.class).build();
}

@Bean
@ConfigurationProperties("datasource.flyaway")
@FlywayDataSource
public DataSourceProperties secondDataSourceProperties() {
return new DataSourceProperties();
}

@Bean
@ConfigurationProperties("datasource.flyway")
public HikariDataSource secondDataSource(DataSourceProperties properties) {
return (HikariDataSource) properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
}

}