package com.ytx.center.server.extension.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GradeEnum {

    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    @EnumValue
    private final int code;

    private final String descp;

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
