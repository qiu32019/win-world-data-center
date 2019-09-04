package com.ytx.management.consumer.controller;

import com.ytx.management.consumer.dao.SiteInfoDao;
import com.ytx.management.consumer.entity.SiteInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sitInfo")
@Data
public class SiteInfoController {
    @Autowired
    private SiteInfoDao dao;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/save")
    public String save(@RequestBody SiteInfo siteInfo){
        //System.out.println(JSONUtils.toJSONString(siteInfo));
        int save = dao.save(siteInfo);
        return "success";
    }
}
