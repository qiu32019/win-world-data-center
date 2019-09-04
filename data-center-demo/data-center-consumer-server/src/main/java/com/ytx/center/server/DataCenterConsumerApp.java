package com.ytx.center.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 *-Dapp.nacos.register-ip=192.168.2.41 -Dapp.nacos.network-interface="WLAN 2"
 * -Dspring.cloud.inetutils.preferred-networks="192.168"
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ytx.center.demo.feign")
@ComponentScan(basePackages={"com.ytx","com.glodphoenix"})
@MapperScan(basePackages="com.ytx.center.server.dao")
@EnableCaching
public class DataCenterConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DataCenterConsumerApp.class, args);
    }

    @Bean
    public RestTemplate  initRestTemplate(){
        RestTemplate tmp = new RestTemplate();
        return tmp;
    }
}
