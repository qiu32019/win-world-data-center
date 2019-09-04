package com.ytx.center.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ytx.center.demo.feign.HelloService;
import com.ytx.center.server.config.lottery.LotteryInfoConfig;
import com.ytx.center.server.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LotteryInfoConfig config;


    @RequestMapping(value = "/callHello")
    @SentinelResource("callHello")
    public String callHello(String name){
        logger.info("callHello接口被调用了....");
        String  helloStr = helloService.helloWorld();
        return name + " "+ helloStr;
    }

    @RequestMapping(value = "/jack")
    @SentinelResource("jack")
    public String helloWorld(){
        logger.info("jack -- 接口被调用了....");
        return "Jack hello world";
    }

    @GetMapping(value="/lc")
    @SentinelResource("lc")
    public String lotteryCentor(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "https://api.api861861.com/lottery/getList.do?name=&lotCode=&pageNo=&pageSize=100";
        String responseStr = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(responseStr, JsonObject.class);
        //JsonObject result =  Lists.newArrayList(gson.fromJson(responseStr, JsonObject.class));

        System.out.println(json);
        if("0".equals(json.get("errorCode").getAsString())){
            JsonArray asJsonArray = json.get("result").getAsJsonObject()
                                        .get("data").getAsJsonArray();
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println(asJsonArray.size());
        }
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("/config")
    public String getLotteryConfig(){
        Gson gson = new Gson();
        return gson.toJson(config);
    }

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/task")
    public String submit(){
        logger.info("start submit");

        //调用service层的任务
        //asyncService.executeAsync();

        logger.info("end submit");

        return "success";
    }
}
