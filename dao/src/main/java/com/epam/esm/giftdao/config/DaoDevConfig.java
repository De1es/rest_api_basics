package com.epam.esm.giftdao.config;

import com.epam.esm.giftdao.mapper.GiftRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
@EnableTransactionManagement(proxyTargetClass = true)
@Profile("dev")
public class DaoDevConfig {


  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setName("testdb")
        .addScript("classpath:testdb/create.sql")
        .addScript("classpath:testdb/init.sql")
        .build();
  }


  @Bean
  JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource());
    return transactionManager;
  }

  @Bean
  GiftRowMapper giftRowMapper() {
    return new GiftRowMapper();
  }
}
