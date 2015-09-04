package eu.onepay.db;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("db.properties")
@EnableTransactionManagement
public class DBConfiguration {

    @Autowired private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.pass"));

        return dataSource;
    }

    @Bean
    public SessionFactory dbSessionFactory() {
        LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource());

        sessionFactory.scanPackages(env.getProperty("db.data"), "eu.onepay.db.util");

        sessionFactory.setProperty("hibernate.dialect", env.getProperty("db.dialect"));
        sessionFactory.setProperty("hibernate.jdbc.batch_size", env.getProperty("db.batch.size"));
        sessionFactory.setProperty("hibernate.show_sql", env.getProperty("db.show.sql"));
        sessionFactory.setProperty("hibernate.format_sql", env.getProperty("db.format.sql"));
        sessionFactory.setProperty("hibernate.default_schema", env.getProperty("db.schema"));

        return sessionFactory.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(dbSessionFactory());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
