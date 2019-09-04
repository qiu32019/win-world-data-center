package com.ytx.management.consumer.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminManagementConfig implements InitializingBean {
    private static AdminManagementConfig adminManagementConfig = null;

    @Value("${admin.manage.i18n:}")
    private String i18n;

    public static AdminManagementConfig getInstance(){
        return adminManagementConfig;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        adminManagementConfig = this;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }
}
