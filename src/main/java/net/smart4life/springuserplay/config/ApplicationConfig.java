package net.smart4life.springuserplay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;


/**
 * Created by roman on 30.12.2014.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public CommonAnnotationBeanPostProcessor beanPostProcessor(){
        return new CommonAnnotationBeanPostProcessor();
    }
}
