package com.steven.web.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库配置
 * Created by lanhaozhi on 2017/8/4.
 */
@Configuration
@EnableTransactionManagement //The code equals aop config or provider annotation transaction.
@PropertySource("classpath:game-jdbc.properties")
public class MyBatisConfiguration implements EnvironmentAware, TransactionManagementConfigurer {
    private Environment env;
    private DataSource dataSource ;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Bean(name="dataSource",destroyMethod = "close")
    public DataSource dataSource() {
        if ( null == this.dataSource ) {
            BasicDataSource ndataSource = new BasicDataSource();
            ndataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver.name"));
            ndataSource.setUrl(env.getRequiredProperty("jdbc.writedb.proxy.url"));
            ndataSource.setUsername(env.getRequiredProperty("jdbc.username"));
            ndataSource.setPassword(env.getRequiredProperty("jdbc.password"));
            ndataSource.setTestOnBorrow(true);
            ndataSource.setValidationQuery("select 1");

            this.dataSource = ndataSource ;
        }
        return this.dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource
            ,PageInterceptor pageInterceptor) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
            List<Interceptor> is = new ArrayList<>();
            is.add(pageInterceptor);
            bean.setPlugins(is.toArray(new Interceptor[]{}));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor() ;
        Properties ps = new Properties() ;
        /*
        helperDialect=mysql
　　　　　　　　reasonable=true
　　　　　　　　supportMethodsArguments=true
　　　　　　　　params=count=countSql
　　　　　　　　autoRuntimeDialect=true
         */
        ps.setProperty("helperDialect","mysql") ;
        ps.setProperty("reasonable","true") ;
        ps.setProperty("supportMethodsArguments","true") ;
        //ps.setProperty("params","count=countSql") ;
        ps.setProperty("autoRuntimeDialect","true") ;
        pageInterceptor.setProperties(ps);
        return pageInterceptor ;
    }

    @Bean
    @DependsOn("sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @DependsOn("dataSource")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.dataSource);
    }
}
