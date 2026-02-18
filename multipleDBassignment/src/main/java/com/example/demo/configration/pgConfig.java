package com.example.demo.configration;



import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.pgrepo",
        entityManagerFactoryRef = "pgEntityManager",
        transactionManagerRef = "pgTransactionManager"
)
public class pgConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource pgDataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean pgEntityManager(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(pgDataSource())
                .packages("com.example.demo.entity")
                .persistenceUnit("pg")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager pgTransactionManager(
            @Qualifier("pgEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
