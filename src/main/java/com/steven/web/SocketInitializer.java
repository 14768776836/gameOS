package com.steven.web;

import com.steven.web.config.MyBatisConfiguration;
import com.steven.web.config.MyBatisMapperScannerConfig;
import com.steven.web.config.WebSocketConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web socket的测试版本
 * Created by lanhaozhi on 2017/8/3.
 */
public class SocketInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebSocketConfiguration.class,MyBatisConfiguration.class, MyBatisMapperScannerConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
