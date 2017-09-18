package com.steven.web.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by lanhaozhi on 2017/8/5.
 */
@Configuration
public class MyBatisMapperScannerConfig {

    @Bean
    @DependsOn(value="sqlSessionFactory")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.steven.game.dao");
        return mapperScannerConfigurer;
    }
}
