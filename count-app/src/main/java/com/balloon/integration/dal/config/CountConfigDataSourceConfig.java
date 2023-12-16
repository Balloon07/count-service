package com.balloon.integration.dal.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 王思远
 * @date 2023-12-15 18:40
 */
@Configuration
@MapperScan(basePackages = {"com.balloon.integration.dal.count_config.mapper"},
        sqlSessionFactoryRef = "countConfigSqlSessionFactory")
public class CountConfigDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "count.config.mysql")
    public DataSource countConfigDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory countConfigSqlSessionFactory(@Qualifier("countConfigDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate countConfigSqlSessionTemplate(@Qualifier("countConfigSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager countConfigTransactionManager(@Qualifier("countConfigDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
