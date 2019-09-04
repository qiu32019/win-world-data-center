package com.ytx.center.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Jack On 2019/08/12
 **/
@FeignClient(value = "data-center.demo-server") // 通过该注解指定要调用的服务名 （spring.application.name）
public interface HelloService {

    /**
     * @return
     */
    @RequestMapping(value = "/hello")
    String helloWorld();
}
