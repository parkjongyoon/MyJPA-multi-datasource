package com.example.myjpa.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {

    @PersistenceContext(unitName = "defaultEntityManager")
    private EntityManager defaultEntityManager;
    
    @PersistenceContext(unitName = "secondEntityManager")
    private EntityManager secondEntityManager;

    @Bean
    public JPAQueryFactory defaultJpaQueryFactory() {
        return new JPAQueryFactory(defaultEntityManager);
    }
    
    @Bean
    public JPAQueryFactory secondJpaQueryFactory() {
    	return new JPAQueryFactory(secondEntityManager);
    }
}