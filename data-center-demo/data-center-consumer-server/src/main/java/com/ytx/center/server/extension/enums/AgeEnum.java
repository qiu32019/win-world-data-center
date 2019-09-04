package com.ytx.center.server.extension.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "两岁"),
    THREE(3, "三岁");

    private int value;
    private String desc;

    AgeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 注意修改，默认return null
     * @return value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
