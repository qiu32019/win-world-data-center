package com.ytx.center.server.extension.handler;

import com.baomidou.mybatisplus.extension.handlers.EnumTypeHandler;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import org.apache.ibatis.type.MappedTypes;

//单个操作会自动使用com.baomidou.mybatisplus.extension.handlers.EnumTypeHandler
//批量操作会自动使用org.apache.ibatis.type.EnumTypeHandler
//由于数据库我们设置的类型是int，所以在这里统一处理子类实现com.baomidou.mybatisplus.extension.handlers.EnumTypeHandler
//并处理我们需要映射成int数据类型的Enum对象
@MappedTypes(value = {SourceSiteEnum.class, LotteryTypeEnum.class})
public class YtxEnumTypeHandler extends EnumTypeHandler {

    public YtxEnumTypeHandler(Class type) {
        super(type);
    }
}
