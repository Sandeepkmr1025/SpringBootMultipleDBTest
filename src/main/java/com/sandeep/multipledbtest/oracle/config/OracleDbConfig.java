package com.sandeep.multipledbtest.oracle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "SecondEntityManagerFactoryBean",
        basePackages = {"com.sandeep.multipledbtest.oracle.repo"},
        transactionManagerRef = "SecondTransactionManager"
)
public class OracleDbConfig {


        @Autowired
        Environment environment;

        // Datasource
        @Bean(name = "SecondDataSource")
        @Primary
        public DataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("second.datasource.driver-class-name")));
        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));
        return dataSource;
    }

        // entityManagerFactory
        @Bean(name = "SecondEntityManagerFactoryBean")
        @Primary
        public LocalContainerEntityManagerFactoryBean entityMangerFactoryBean()
        {
            LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

            bean.setDataSource(getDataSource());

            JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            bean.setJpaVendorAdapter(adapter);

            Map<String, Object> props = new HashMap<>();
            props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.hbm2ddl.auto", "update");
            bean.setJpaPropertyMap(props);

            bean.setPackagesToScan("com.sandeep.multipledbtest.oracle.entities");
            return bean;
        }

        // platformTransactionManager

        @Primary
        @Bean(name="SecondTransactionManager")
        public PlatformTransactionManager transactionManager()
        {
            JpaTransactionManager manager = new JpaTransactionManager();
            manager.setEntityManagerFactory(entityMangerFactoryBean().getObject());
            return manager;
        }
}
