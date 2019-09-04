package com.ytx.center.server.controller.model;

import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class LotteryMode {
    @NotNull(message="时间不能为空")
    private Date start;
    private String end;
    private SourceSiteEnum siteEnum;
    @NotNull(message="彩种不能为空")
    private LotteryTypeEnum lotteryTypeEnum;
}
