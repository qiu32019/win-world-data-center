package com.ytx.center.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytx.center.server.entity.Lottery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LotteryDao extends BaseMapper<Lottery> {
    static final String CACHE_NAME = "lotteryCache";
    /**
     * 根据key，更新value
     */
    int updateIssueById(@Param("id") int id, @Param("newIssue") String newIssue);

    @Select("select * from lottery where type=#{lotteryType} and issue=#{issue}")
    public List<Lottery> queryByParams(String lotteryType, String issue);

    //@Cacheable(value = CACHE_NAME)
    @Select("select count(1) from lottery where type=#{lotteryType} and issue=#{issue} and site=#{site}")
    public int existByParams(String lotteryType, String issue, int site);
}
