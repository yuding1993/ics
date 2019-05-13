package org.wheport.ics.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wheport.ics.util.VerifyCodeServlet;

@Configuration
public class ServletConfigure {


    /**
     * 验证码servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new VerifyCodeServlet(), "/VerifyCodeServlet");
    }
}
