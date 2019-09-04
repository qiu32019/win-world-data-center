package com.ytx.management.consumer.controller.model;

public class SiteInfo {
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

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    public String getSportSiteNo() {
        return sportSiteNo;
    }

    public void setSportSiteNo(String sportSiteNo) {
        this.sportSiteNo = sportSiteNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUppername() {
        return uppername;
    }

    public void setUppername(String uppername) {
        this.uppername = uppername;
    }

    public String getDeskey() {
        return deskey;
    }

    public void setDeskey(String deskey) {
        this.deskey = deskey;
    }

    public String getAllowedtype() {
        return allowedtype;
    }

    public void setAllowedtype(String allowedtype) {
        this.allowedtype = allowedtype;
    }

    public int getPerbatch() {
        return perbatch;
    }

    public void setPerbatch(int perbatch) {
        this.perbatch = perbatch;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public short getEnable() {
        return enable;
    }

    public void setEnable(short enable) {
        this.enable = enable;
    }

    public int getPtscore() {
        return ptscore;
    }

    public void setPtscore(int ptscore) {
        this.ptscore = ptscore;
    }

}
