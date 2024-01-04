package com.jomo.eorganism.metamodel.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jomo.eorganism.metamodel.repository")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PersistenceConfig {

    public static final String EORGANISM_PERSISTENCE_UNIT_NAME = "eorganism-pu";
    private static final String ENTITY_BASE_PACKAGE = "com.jomo.eorganism.metamodel.entity";

    private final JpaProperties jpaProperties;

    public PersistenceConfig(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_BASE_PACKAGE);
        entityManagerFactoryBean.setPersistenceUnitName(EORGANISM_PERSISTENCE_UNIT_NAME);
        //PostgreSQL Database
        //entityManagerFactoryBean.setJpaVendorAdapter(postgreSQLVendorAdapter());
        //H2 Database
        entityManagerFactoryBean.setJpaVendorAdapter(h2SQLVendorAdapter());
        if (additionalProperties() != null) {
            entityManagerFactoryBean.setJpaProperties(additionalProperties());
        }
        return entityManagerFactoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private HibernateJpaVendorAdapter postgreSQLVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        return vendorAdapter;
    }

    private HibernateJpaVendorAdapter h2SQLVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.H2);
        return vendorAdapter;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        jpaProperties.getProperties().forEach(properties::setProperty);
        return properties;
    }
}
