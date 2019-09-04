package com.ytx.center.demo;

import com.glodphoenix.starter.web.xss.XssFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 -Dapp.nacos.register-ip=192.168.2.41 -Dapp.nacos.network-interface="WLAN 2"
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ytx.center.demo.feign")
public class DataCenterDemoApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DataCenterDemoApp.class, args);
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        return registration;
    }
}
