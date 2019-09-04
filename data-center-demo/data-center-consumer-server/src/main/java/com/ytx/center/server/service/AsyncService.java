package com.ytx.center.server.service;

import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;

public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(SourceSiteEnum siteEnum, LotteryTypeEnum lotteryTypeEnum, Node node, String date);
}
