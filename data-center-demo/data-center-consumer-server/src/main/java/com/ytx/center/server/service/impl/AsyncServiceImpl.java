package com.ytx.center.server.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ytx.center.server.config.lottery.LotteryInfoConfig;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.entity.Lottery;
import com.ytx.center.server.entity.Position;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import com.ytx.center.server.service.AsyncService;
import com.ytx.center.server.service.LotteryService;
import com.ytx.center.server.utils.DateUtil;
import com.ytx.center.server.utils.PositionBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PositionBuilder positionBuilder;

    @Autowired
    private LotteryInfoConfig lotteryInfoConfig;

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(SourceSiteEnum siteEnum, LotteryTypeEnum lotteryTypeEnum, Node node, String date) {
        log.info("start executeAsync");
        /*try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }*/

        doExecute(siteEnum, lotteryTypeEnum, node, date);

        log.info("end executeAsync");
    }

    private void doExecute(SourceSiteEnum siteEnum, LotteryTypeEnum lotteryTypeEnum, Node node, String date){
        if (StringUtils.isEmpty(date)) {
            date = DateUtil.format(Calendar.getInstance().getTime());
        }
        JsonObject json = fromRemote(node.getUrl()+date);

        if("0".equals(json.get(lotteryInfoConfig.getErrorProp()).getAsString())){
            List<Lottery> lotteryList = null;

            if(1==node.getPassageway()){
                lotteryList = single(json, siteEnum, lotteryTypeEnum, node);
            }else{
                lotteryList = multiple(json, siteEnum, lotteryTypeEnum, node);
            }

            if(null!=lotteryList && 0<lotteryList.size()){
                lotteryService.saveBatchNotRepeat(lotteryList, 10);
            }

        }
    }

    private List<Lottery> multiple(JsonObject json, SourceSiteEnum siteEnum, LotteryTypeEnum lotteryTypeEnum, Node node){
        JsonArray asJsonArray = json.get(lotteryInfoConfig.getResultProp()).getAsJsonObject()
                .get(lotteryInfoConfig.getDataProp()).getAsJsonArray();
        List<Lottery> lotteryList = Lists.newArrayListWithCapacity(asJsonArray.size());
        Iterator<JsonElement> iterator = asJsonArray.iterator();
        while(iterator.hasNext()){
            JsonObject next = iterator.next().getAsJsonObject();
            Date winTime = null;
            Optional<Position> posi = positionBuilder.build(next, node, siteEnum, lotteryTypeEnum);
            if(posi.isPresent()){
                Lottery<Position> lottery = new Lottery<>();
                lottery.setName(lotteryTypeEnum.getDescp());
                lottery.setVersion(1);
                lottery.setType(lotteryTypeEnum.name());
                lottery.setIssue(next.get(node.getIssue()).getAsString());
                lottery.setSite(siteEnum);
                lottery.setData(posi.get());
                if(null!=next.get(node.getOpenWinTime())){
                    lottery.setOpenWinTimeStr( next.get(node.getOpenWinTime()).getAsString());
                }
                lotteryList.add(lottery);
            }
        }
        return lotteryList;
    }

    private List<Lottery> single(JsonObject json, SourceSiteEnum siteEnum, LotteryTypeEnum lotteryTypeEnum, Node node){
        JsonObject jsonObject = json.get(lotteryInfoConfig.getResultProp()).getAsJsonObject()
                .get(lotteryInfoConfig.getDataProp()).getAsJsonObject();
        List<Lottery> lotteryList = Lists.newArrayListWithCapacity(1);

        Optional<Position> posi = positionBuilder.build(jsonObject, node, siteEnum, lotteryTypeEnum);
        if(posi.isPresent()){
            Lottery<Position> lottery = new Lottery<>();
            lottery.setName(lotteryTypeEnum.getDescp());
            lottery.setVersion(1);
            lottery.setType(lotteryTypeEnum.name());
            lottery.setIssue(jsonObject.get(node.getIssue()).getAsString());
            lottery.setSite(siteEnum);
            lottery.setData(posi.get());
            if(null!=jsonObject.get(node.getOpenWinTime())){
                lottery.setOpenWinTimeStr( jsonObject.get(node.getOpenWinTime()).getAsString());
            }
            lotteryList.add(lottery);
        }
        return lotteryList;
    }

    private JsonObject fromRemote(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String responseStr = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(responseStr, JsonObject.class);

        return json;
    }
}
