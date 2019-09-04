package com.ytx.center.server.entity;

import com.ytx.center.server.extension.enums.AgeEnum;
import com.ytx.center.server.extension.enums.GenderEnum;
import com.ytx.center.server.extension.enums.GradeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private String email;

    /**
     * IEnum接口的枚举处理
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举（带{@com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;
}
