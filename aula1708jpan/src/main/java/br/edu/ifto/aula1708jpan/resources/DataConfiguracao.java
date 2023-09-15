//package br.edu.ifto.aula1708jpan.resources;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataConfiguracao {
//
//    @Bean
//    public DataSource dataSource()
//    {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3307/aula3?createDatabaseIfNotExist=true");
//        dataSource.setUsername("root");
//        dataSource.setPassword("asferna123");
//        return dataSource;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter()
//    {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase(Database.MYSQL);
//        adapter.setShowSql(true);
//        adapter.setGenerateDdl(true);
//        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
//        adapter.setPrepareConnection(true);
//        return adapter;
//    }
//}
