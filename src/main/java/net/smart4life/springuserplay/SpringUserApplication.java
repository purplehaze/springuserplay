package net.smart4life.springuserplay;

import net.smart4life.springuserplay.config.ApplicationConfig;
import net.smart4life.springuserplay.config.ValidationConfig;
import net.smart4life.springuserplay.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"net.smart4life"})
public class SpringUserApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringUserApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Spring4jsfApplication.class);
        return application.sources(new Class[]{SpringUserApplication.class, WebConfig.class, ValidationConfig.class, ApplicationConfig.class});
    }

//    @SuppressWarnings("serial")
//    @Bean public org.springframework.beans.factory.config.CustomScopeConfigurer customScopeConfigurer(){
//        CustomScopeConfigurer csc = new CustomScopeConfigurer();
//        csc.setScopes(new HashMap<String, Object>() {{
//            put("view", new ViewScope());
//        }});
//        return csc;
//    }
//
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        FacesServlet servlet = new FacesServlet();
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml", "*.jsf");
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletCustomizer() {
//        return new Port80ServletContainerCustomizer();
//    }


}
