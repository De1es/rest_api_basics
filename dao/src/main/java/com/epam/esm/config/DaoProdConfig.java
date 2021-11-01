package com.epam.esm.config;

import com.epam.esm.giftdao.mapper.GiftRowMapper;
import com.epam.esm.tagdao.mapper.TagRowMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
@EnableTransactionManagement(proxyTargetClass = true)
@Profile("prod")
public class DaoProdConfig {

  @Bean
  DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/rest_api_basics_task");
    dataSource.setUsername("postgres");
    dataSource.setPassword("Seta11921");
    return dataSource;
  }


  @Bean
  JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public PlatformTransactionManager transactionManager () {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource());
    return transactionManager;
  }

  @Bean
  GiftRowMapper giftRowMapper() {
    return new GiftRowMapper();
  }

  @Bean
  TagRowMapper tagRowMapper() {
    return new TagRowMapper();
  }

}
