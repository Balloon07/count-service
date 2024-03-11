package com.balloon.integration.dal.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 王思远
 * @date 2023-12-15 18:40
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.balloon.integration.dal.count_config",
        sqlSessionTemplateRef = "countConfigSqlSessionTemplate")
public class CountConfigDataSourceConfig {

    /*
        DataSource主要用于获取数据库连接，
        SqlSessionFactory用于创建SqlSession对象，SqlSession是与数据库交互的主要对象，
        而DataSourceTransactionManager用于管理数据库事务。
     */

    @Bean(name = "countConfigDataSource")
    @ConfigurationProperties(prefix = "mysql.count-config")
    public DataSource countConfigDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "countConfigSqlSessionFactory")
    public SqlSessionFactory countConfigSqlSessionFactory(@Qualifier("countConfigDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/count_config/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "countConfigSqlSessionTemplate")
    public SqlSessionTemplate countConfigSqlSessionTemplate(@Qualifier("countConfigSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "countConfigTransactionManager")
    public DataSourceTransactionManager countConfigTransactionManager(@Qualifier("countConfigDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
