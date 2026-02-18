package com.example.demo.configration;


import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;  // ✅ CORRECT
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.*;


import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.h2repo",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager"
)
public class h2Config {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource h2DataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManager(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(h2DataSource())
                .packages("com.example.demo.entity")
                .persistenceUnit("h2")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
