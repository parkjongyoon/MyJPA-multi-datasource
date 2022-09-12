package com.example.myjpa.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
	    basePackages = "com.example.myjpa2.repository",   //repository 경로
	    entityManagerFactoryRef = "secondEntityManagerFactory", 
	    transactionManagerRef = "secondTransactionManager"
	)
public class SecondDatasourceConfig {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource-second.hikari")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondDataSource());
        em.setPackagesToScan(new String[] { "com.example.myjpa2.entity" }); //entity 경로
        em.setPersistenceUnitName("secondEntityManager");
        
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(properties);		
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
	
	@Bean
	public PlatformTransactionManager secondTransactionManager() {	
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(secondEntityManagerFactory().getObject());
		return transactionManager;
	}
}