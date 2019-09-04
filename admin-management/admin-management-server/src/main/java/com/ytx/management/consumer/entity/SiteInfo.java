package com.ytx.management.consumer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SiteInfo implements Serializable {
    private int id;
    //真人siteNo
    private String siteNo;
    //体育SiteNo
    private String sportSiteNo;
    //ip地址
    private String ip;
    //BBIN上级账号
    private String uppername;
    //AG秘钥
    private String deskey;
    //真人开通种类
    private String allowedtype;
    //接水单批
    private int perbatch;
    //前缀
    private String prefix;
    //是否停用
    private short enable;
    //PT点数
    private int ptscore;
}
