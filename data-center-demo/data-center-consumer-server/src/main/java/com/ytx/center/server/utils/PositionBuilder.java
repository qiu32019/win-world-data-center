package com.ytx.center.server.utils;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.gson.JsonObject;
import com.ytx.center.server.config.lottery.LotteryInfoConfig;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.entity.Position;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;

@Component
@Data
public class PositionBuilder {
    private final static Map<String, LotteryTypeEnum> kvs = Maps.newHashMapWithExpectedSize(LotteryTypeEnum.values().length);

    private Table<SourceSiteEnum, LotteryTypeEnum, IPositionPolicy> table = HashBasedTable.create();
    @Autowired
    private PositionValidator validator;

    public PositionBuilder(LotteryInfoConfig lotteryInfoConfig){
        Arrays.stream(LotteryTypeEnum.values())
                .forEach(element -> kvs.put(element.getDescp(), element));

        lotteryInfoConfig.getLotterySource().entrySet().stream().collect(Collector.of(
                ()-> HashBasedTable.create(),(mutableMap, entryItem)-> {
                    entryItem.getValue().stream().forEach(
                            node ->table.put(SourceSiteEnum.valueOf(entryItem.getKey()),
                                    LotteryTypeEnum.valueOf(node.getName()),
                                    build(node))
                    );
                },
                (map1,map2)->{ map1.putAll(map2); return map1;}
        ));
    }

    public Optional<String> convertToLotteryType(JsonObject json, SourceSiteEnum siteEnum){
        String type = null;
        if(SourceSiteEnum.KJW168==siteEnum){
            type = json.get("name").getAsString();
        }else if(SourceSiteEnum.CPK==siteEnum){
            type = json.get("name").getAsString();
        }else{
            return Optional.absent();
        }

        if(!kvs.containsKey(type)){
            return Optional.absent();
        }

        LotteryTypeEnum lotteryTypeEnum = kvs.get(type);
        return Optional.of(lotteryTypeEnum.name());
    }

    public  Optional<Position> build(JsonObject json, Node node, SourceSiteEnum sourceSiteEnum, LotteryTypeEnum lotteryTypeEnum){
        String winNumber = json.get(node.getPositions()).getAsString();
//        if(!kvs.containsKey(type)){
//            return Optional.absent();
//        }
        String[] winNumbers;
        if(StringUtils.isEmpty(winNumber) ||
                !validator.validate(winNumbers = winNumber.split(","),sourceSiteEnum, lotteryTypeEnum)){
            return Optional.absent();
        }

        Optional<Position> result = buildPosition(winNumbers,sourceSiteEnum,lotteryTypeEnum);

        return result;
    }

    private Optional<Position> buildPosition(String[] winNumbers,SourceSiteEnum sourceSiteEnum, LotteryTypeEnum lotteryTypeEnum){
        IPositionPolicy positionPolicy = table.get(sourceSiteEnum, lotteryTypeEnum);
        Optional<Position> result = positionPolicy.calculate(winNumbers);

        return result;
    }

   private IPositionPolicy build(Node node){
       String positionPolicy = node.getPositionPolicy();
       if(StringUtils.isEmpty(positionPolicy)){
           return new DefaultPositionPolicy();
       }

       try {
           IPositionPolicy policy = (IPositionPolicy)Class.forName(positionPolicy)
                   .getConstructor(Node.class)
                   .newInstance(node);
           return policy;
       } catch (IllegalAccessException e) {
           throw new RuntimeException("[PositionBuilder.build] 参数错误:"+positionPolicy,e);
       } catch (InstantiationException e) {
           throw new RuntimeException("[PositionBuilder.build] 初始化错误:"+positionPolicy,e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException("[PositionBuilder.build] 找不到指定的类:"+positionPolicy,e);
       } catch (NoSuchMethodException e) {
           throw new RuntimeException("[PositionBuilder.build] 没有这个函数类型",e);
       } catch (InvocationTargetException e) {
          throw new RuntimeException("[PositionBuilder.build] 实例化构造函数报错",e);
       }

   }
}
