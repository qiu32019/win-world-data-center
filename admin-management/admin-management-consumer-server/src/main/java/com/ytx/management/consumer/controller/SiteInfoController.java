package com.ytx.management.consumer.controller;

import com.ytx.management.consumer.controller.model.SiteInfo;
import com.ytx.management.consumer.controller.model.ZRSite;
import com.ytx.management.consumer.utils.JacksonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/jobgroup")
public class SiteInfoController {
    @RequestMapping
    public String index(Model model) {
        System.out.println("---------list----------");
        System.out.println("---------list----------");
        System.out.println("---------list----------");
        System.out.println("---------list----------");
        //List<SiteInfo> list = xxlJobGroupDao.findAll();
        List<SiteInfo> list = new ArrayList<>();
        SiteInfo info1 = new SiteInfo();
        info1.setSiteNo("site203");
        info1.setSportSiteNo("site203");
        info1.setIp("|103.53.7.134|103.53.7.130|");
        info1.setUppername("dfrsite203");
        info1.setDeskey("BTYJ5637");
        info1.setAllowedtype("BG|BBIN|PT|XTC|GG|GD|DS|OG|MG|KY|VR|HG|AG|SLW|FY");
        info1.setPerbatch(3000);
        info1.setPrefix("FR");
        info1.setPtscore(54301);
        info1.setEnable((short) 0);

        SiteInfo info2 = new SiteInfo();
        info2.setSiteNo("site204");
        info2.setSportSiteNo("site204");
        info2.setIp("|101.53.7.134|101.53.7.130|");
        info2.setUppername("dfrsite203");
        info2.setDeskey("BTYJ5638");
        info2.setAllowedtype("BG|BBIN|PT|XTC|GG|GD|DS|OG|MG|KY|VR|HG|AG|SLW|FY");
        info2.setPerbatch(3000);
        info2.setPrefix("50");
        info2.setPtscore(54301);
        info1.setEnable((short) 1);

        list.add(info1);
        list.add(info2);
        model.addAttribute("list", list);

        model.addAttribute("ZRSites",composeZRSite());
        return "index";
    }

    @PostMapping("/save")
    @ResponseBody
    public ReturnT<String> save(SiteInfo siteInfo){
        System.out.println("---------save----------");
        System.out.println("---------save----------");
        System.out.println("---------save----------");
        System.out.println("---------save----------");

        System.out.println(JacksonUtil.writeValueAsString(siteInfo));
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(SiteInfo siteInfo){
        System.out.println("---------update----------");
        System.out.println("---------update----------");
        System.out.println("---------update----------");
        System.out.println("---------update----------");
        System.out.println(JacksonUtil.writeValueAsString(siteInfo));
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(String siteNo){
        System.out.println("---------remove----------");
        System.out.println("---------remove----------");
        System.out.println("---------remove----------");
        System.out.println("---------remove----------");
        System.out.println(siteNo);
        return ReturnT.SUCCESS;
    }

    private List<ZRSite> composeZRSite(){
        ZRSite a1 = new ZRSite(1,"AG");
        ZRSite a2 = new ZRSite(2,"HG");
        ZRSite a3 = new ZRSite(1,"BBIN");
        ZRSite a4 = new ZRSite(1,"MG");
        ZRSite a5 = new ZRSite(1,"DS");
        ZRSite a6 = new ZRSite(1,"PT");
        ZRSite a7 = new ZRSite(1,"VR");
        ZRSite a8 = new ZRSite(1,"OG");
        ZRSite a9 = new ZRSite(1,"BG");
        ZRSite a10 = new ZRSite(1,"GD");
        ZRSite a11 = new ZRSite(1,"GG");
        ZRSite a12 = new ZRSite(1,"XTC");
        ZRSite a13 = new ZRSite(1,"KY");
        ZRSite a14 = new ZRSite(1,"SLW");
        ZRSite a15 = new ZRSite(1,"FY");
        ZRSite a16 = new ZRSite(1,"TH");
        return Stream.of(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16)
                .collect(Collectors.toList());
    }
}
