package com.cc.dugumashen.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.cc.dugumashen.util.ApachMd5Util;
import com.cc.dugumashen.util.RSAEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Description：
 * Date: 2019/9/3
 * Author：
 */
@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@PropertySource("classpath:dev/securityManage.properties")
public class DruidConfig {

    @Value("${druid.manage.username}")
    private String username;

    @Value("${druid.manage.password}")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServle() {

        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "");
        servletRegistrationBean.addInitParameter("loginUsername", username);//
        servletRegistrationBean.addInitParameter("loginPassword", RSAEncryptUtil.decrypt(password));//
        servletRegistrationBean.addInitParameter("resetEnable", "false");

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
