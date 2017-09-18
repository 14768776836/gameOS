package com.steven.web.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by lanhaozhi on 2017/8/5.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.steven.web","com.steven.game"})
@PropertySource("classpath:game-config.properties")
public class WebSocketConfiguration  extends WebMvcConfigurerAdapter implements EnvironmentAware {
    private Environment env ;

    @Override
    public void setEnvironment(Environment environment) {
        env = environment ;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        //娉ㄥ唽闈欐�佽祫婧愮洿鎺ュ紩鐢�
        /*registry.addResourceHandler("/js/**")
                .addResourceLocations("/static/javascripts/") ;
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/static/css/") ;*/

        return viewResolver;
    }

    /**
     * 娉ㄥ叆鑷繁瀹炵幇鐨勬嫤鎴櫒
     * @param registry 鎷︽埅娉ㄥ唽鍣�
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(loginInterceptor()) ;
    }
    /**
     * 闈欐�佽祫婧愯闂櫒
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/") ;
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/") ;
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/static/fonts/") ;

        registry.addResourceHandler("/pages/**").addResourceLocations("/WEB-INF/views/") ;
    }
}
