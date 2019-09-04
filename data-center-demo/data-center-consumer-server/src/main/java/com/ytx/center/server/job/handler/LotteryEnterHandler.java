package com.ytx.center.server.job.handler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.ytx.center.server.config.lottery.LotteryInfoConfig;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import com.ytx.center.server.service.AsyncService;
import com.ytx.center.server.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

@JobHandler(value="lotteryEnterHandler")
@Component
@Slf4j
public class LotteryEnterHandler extends IJobHandler {

    @Autowired
    private AsyncService asyncService;

    private Table<SourceSiteEnum, LotteryTypeEnum, Node> table = HashBasedTable.create();

    //单位:秒
    private static int INTERVAL = 5;

    public LotteryEnterHandler(LotteryInfoConfig lotteryInfoConfig){

        for(Map.Entry<String, List<Node>> entry : lotteryInfoConfig.getLotterySource().entrySet()){
            SourceSiteEnum sourceSiteEnum = SourceSiteEnum.valueOf(entry.getKey());
            if(null==sourceSiteEnum) throw new RuntimeException("没有匹配的站点");
            entry.getValue().stream().forEach(p -> {
                LotteryTypeEnum lotteryTypeEnum = LotteryTypeEnum.valueOf(p.getName());
                if(null==lotteryTypeEnum) throw new RuntimeException("没有匹配的彩种");
                //table.put(sourceSiteEnum, lotteryTypeEnum, p);
            });
        }


        //Table<SourceSiteEnum, LotteryTypeEnum, Node> table1 = HashBasedTable.create();
        lotteryInfoConfig.getLotterySource().entrySet().stream().collect(Collector.of(
                ()-> HashBasedTable.create(),(mutableMap,entryItem)-> {
                        entryItem.getValue().stream().forEach(
                                node ->table.put(SourceSiteEnum.valueOf(entryItem.getKey()),
                                                 LotteryTypeEnum.valueOf(node.getName()),
                                                 node)
                        );
                     },
                    (map1,map2)->{ map1.putAll(map2); return map1;}
        ));
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("[LotteryEnterHandler.execute] start ... param::::"+param);
        XxlJobLogger.log("XXL-JOB, Hello World.");
        try{
            if(!StringUtils.isEmpty(param)){
                DateUtil.parse(param);
            }
        }catch(Exception e){
            log.error("[LotteryEnterHandler.execute]param 不是一个日期格式");
            return FAIL;
        }
        /*SourceSiteEnum sourceSiteEnum = SourceSiteEnum.valueOf(param);
        if(null==sourceSiteEnum) {
            log.error("[LotteryEnterHandler.execute] param({0}) not have relative sourceSiteEnum", param);
            return FAIL;
        }*/

       /* lotteryInfoConfig.getLotterySource().entrySet().stream().forEach(
                        entry->entry.getValue().stream().forEach(
                        node->{
                            LotteryTypeEnum lotteryTypeEnum = LotteryTypeEnum.valueOf(node.getName());
                            if(null!=lotteryTypeEnum && INTERVAL==node.getInterval())
                                asyncService.executeAsync(SourceSiteEnum.valueOf(entry.getKey()),lotteryTypeEnum, node);
                        }));*/

        for (Table.Cell<SourceSiteEnum, LotteryTypeEnum, Node> cell : table.cellSet()) {
            if(cell.getValue().isEnable() && INTERVAL==cell.getValue().getInterval()){
                log.debug("ready go ==>(" + cell.getRowKey() + "," + cell.getColumnKey() + ")=" + cell.getValue());
                asyncService.executeAsync(cell.getRowKey(), cell.getColumnKey(), cell.getValue(), param);
            }
        }

        return SUCCESS;
    }

}
