package com.ytx.center.server.extension.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum SourceSiteEnum implements IEnum<Integer> {
    KJW168(1, "168开奖网"),
    CPK(2, "彩票控");

    //@EnumValue
    private final int code;


    private final String descp;

    SourceSiteEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
