package com.ytx.center.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("lottery")
@Accessors(chain = true)
public class Lottery<T> {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private long id;

    //若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
    @TableField(value = "name",exist = true)
    private String name;

    @TableField(value = "type")
    private String type;

    @TableField(value = "issue")
    private String issue;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "open_win_time")
    private String openWinTimeStr;

    @TableField(value = "version")
    private int version;

    @TableField(value = "site")
    private SourceSiteEnum site;

    //@TableField(value="data", typeHandler="")
    //@TableField(value="data", el = "data, typeHandler=com.glodphoenix.starter.mysql.handler.JsonTypeHandler")
//    @TableField(value = "data")
//    private JSONObject data;

    @TableField(value = "data")
    private T data;
}
