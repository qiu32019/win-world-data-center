package com.ytx.center.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glodphoenix.starter.mysql.utils.PageUtils;
import com.glodphoenix.starter.mysql.utils.Query;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ytx.center.server.controller.model.LotteryMode;
import com.ytx.center.server.dao.LotteryDao;
import com.ytx.center.server.entity.Lottery;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import com.ytx.center.server.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("lotteryService")
@Slf4j
public class LotteryServiceImpl extends ServiceImpl<LotteryDao, Lottery> implements LotteryService {

    private Cache<String,Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    private static Object obj = new Object();

    @Override
    @Cacheable(value = LotteryDao.CACHE_NAME, key = "'lottery_'+ #id")
    public Lottery getById(Serializable id) {
        log.info("[LotteryServiceImpl.getById] id:"+id);
        log.info("[LotteryServiceImpl.getById] id:"+id);
        log.info("[LotteryServiceImpl.getById] id:"+id);
        log.info("[LotteryServiceImpl.getById] id:"+id);
        log.info("[LotteryServiceImpl.getById] id:"+id);
        return baseMapper.selectById(id);
    }

    @CacheEvict(value = LotteryDao.CACHE_NAME, key = "'lottery_'+ #id")
    public void evictLottery(Serializable id){
        log.info("[LotteryServiceImpl.getById] evict ..............");
        log.info("[LotteryServiceImpl.getById] evict ..............");
        log.info("[LotteryServiceImpl.getById] evict ..............");
    }

    @Override
    @CachePut(value=LotteryDao.CACHE_NAME, key="'lottery_'+#lottery.id")
    public void update(Lottery lottery) {
        log.info("[LotteryServiceImpl.update] id:"+lottery.getId());
        log.info("[LotteryServiceImpl.update] id:"+lottery.getId());
        log.info("[LotteryServiceImpl.update] id:"+lottery.getId());
        log.info("[LotteryServiceImpl.update] id:"+lottery.getId());
        baseMapper.updateById(lottery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value=LotteryDao.CACHE_NAME, key="'lottery_'+#lottery.id")
    public void updateIssueById(int id, String newIssue) {
        baseMapper.updateIssueById(id, newIssue);
        //sysConfigRedis.delete(key);
    }

    /**
     * 测试用
     * @return
     */
    public List<Lottery> list() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //String now = (String)params.get("now");
        Date now = new Date();

        IPage<Lottery> page = this.page(
                new Query<Lottery>().getPage(params),
                new QueryWrapper<Lottery>().le(null!=now, "create_time", now)
                .eq("issue", "1946921900")
        );
        return new PageUtils(page);
    }


    public PageUtils queryPage1(LotteryMode mode) {
        QueryWrapper<Lottery> wrapper = new QueryWrapper();
        wrapper.ge(null!=mode.getStart(),"create_time", mode.getStart())
                .lt(null!=mode.getEnd(), "create_time", mode.getEnd())
                .eq(null!=mode.getLotteryTypeEnum(),"type", mode.getLotteryTypeEnum())
                .eq("site", SourceSiteEnum.KJW168.getCode());

        Page<Lottery> page = new Page<>(1,10);

        IPage<Lottery> userIPage = baseMapper.selectPage(page, wrapper);

        //IPage<Map<String, Object>> mapIPage = baseMapper.selectMapsPage(page, wrapper);


//        System.out.println("总页数"+mapIPage.getPages());
//        System.out.println("总记录数"+mapIPage.getTotal());
//        List<Map<String, Object>> records = mapIPage.getRecords();
//        records.forEach(System.out::println);
        return new PageUtils(userIPage);
    }

    public Optional<Lottery> queryByPrams(String lotteryType, String issue){
        List<Lottery> lotteries = baseMapper.queryByParams(lotteryType, issue);
        if(null==lotteries || 0==lotteries.size()){
            return Optional.absent();
        }
        DateFormat instance = SimpleDateFormat.getInstance();
        lotteries = lotteries.stream()
                .sorted((o1,o2)-> o1.getType().compareTo(o2.getType()))
                .collect(Collectors.toList());
        return Optional.of(lotteries.get(0));
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatchNotRepeat(Collection<Lottery> entityList, int batchSize) {
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (Lottery anEntityList : entityList) {
                String type = anEntityList.getType();
                String issue = anEntityList.getIssue();
                int code = anEntityList.getSite().getCode();
                String key = type+"-"+issue+"-"+code;
                if(null!=cache.getIfPresent(key)){
                    continue;
                }

                int count = baseMapper.existByParams(anEntityList.getType(),
                                                     anEntityList.getIssue(),
                                                     anEntityList.getSite().getCode());
                if(0==count){
                    int insert = batchSqlSession.insert(sqlStatement, anEntityList);
                    if (i >= 1 && i % batchSize == 0) {
                        batchSqlSession.flushStatements();
                    }
                    if(insert > 0){
                        cache.put(key, obj);
                    }
                }

                /*if(0==baseMapper.existByParams(anEntityList.getType(),
                        anEntityList.getIssue(), anEntityList.getSite().getCode())){
                    batchSqlSession.insert(sqlStatement, anEntityList);
                    if (i >= 1 && i % batchSize == 0) {
                        batchSqlSession.flushStatements();
                    }
                }*/

                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }



}
