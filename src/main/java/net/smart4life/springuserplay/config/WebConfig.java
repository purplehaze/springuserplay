package net.smart4life.springuserplay.config;

import net.smart4life.springuserplay.Port80ServletContainerCustomizer;
import net.smart4life.springuserplay.scope.ViewScope;
import net.smart4life.springuserplay.scope.viewaccess.ViewAccessScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import java.util.HashMap;

/**
 * Created by roman on 29.01.2015.
 */
@Configuration
public class WebConfig {
    @SuppressWarnings("serial")
    @Bean
    public static CustomScopeConfigurer customScopeConfigurer(){
        CustomScopeConfigurer csc = new CustomScopeConfigurer();
        csc.setScopes(new HashMap<String, Object>() {{
            put("view", new ViewScope());
            put(ViewAccessScope.NAME, new ViewAccessScope());
        }});
        return csc;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml", "*.jsf");
        return servletRegistrationBean;
    }

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletCustomizer() {
        return new Port80ServletContainerCustomizer();
    }
}
