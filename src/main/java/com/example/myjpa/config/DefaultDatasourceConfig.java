package com.example.myjpa.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
	    basePackages = "com.example.myjpa.repository",   //repository 경로
	    entityManagerFactoryRef = "defaultEntityManagerFactory", 
	    transactionManagerRef = "defaultTransactionManager"
	)
public class DefaultDatasourceConfig {
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix="spring.datasource-default.hikari")
	public DataSource defaultDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(defaultDataSource());
        em.setPackagesToScan(new String[] { "com.example.myjpa.entity" }); //entity 경로
        em.setPersistenceUnitName("defaultEntityManager");
	
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(properties);		
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager defaultTransactionManager() {	
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(defaultEntityManagerFactory().getObject());
		return transactionManager;
	}
}