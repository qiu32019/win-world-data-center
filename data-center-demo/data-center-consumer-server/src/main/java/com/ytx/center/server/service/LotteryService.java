package com.ytx.center.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glodphoenix.starter.mysql.utils.PageUtils;
import com.google.common.base.Optional;
import com.ytx.center.server.controller.model.LotteryMode;
import com.ytx.center.server.entity.Lottery;

import java.util.Collection;
import java.util.Map;


public interface LotteryService extends IService<Lottery> {

    /**
     * 更新彩票信息
     */
    public void update(Lottery lottery);

    /**
     * 根据key，更新value
     */
    public void updateIssueById(int id, String newIssue);

    public PageUtils queryPage(Map<String, Object> params);

    public Optional<Lottery> queryByPrams(String lotteryType, String issue);

    public  boolean  saveBatchNotRepeat(Collection<Lottery> entityList, int batchSize);

    public PageUtils queryPage1(LotteryMode mode);

}
