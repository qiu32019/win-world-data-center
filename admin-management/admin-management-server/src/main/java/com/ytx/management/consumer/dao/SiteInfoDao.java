package com.ytx.management.consumer.dao;

import com.ytx.management.consumer.entity.SiteInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SiteInfoDao {

    public List<SiteInfo> findAll();

//    public List<SiteInfo> findByAddressType(@Param("addressType") int addressType);

    @Insert("insert into site_info(siteNo, sportSiteNo, uppername, deskey, allowedtype, perbatch, prefix, disabled, ptscore)\n" +
            "values(#{siteNo}, #{sportSiteNo}, #{uppername}, #{deskey}, #{allowedtype}, #{perbatch}, #{prefix},#{enable}, #{ptscore})")
    public int save(SiteInfo siteInfo);

    public int update(SiteInfo siteInfo);

    public int remove(@Param("id") int id);

    public SiteInfo load(@Param("id") int id);
}
