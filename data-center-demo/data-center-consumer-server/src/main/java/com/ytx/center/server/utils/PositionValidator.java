package com.ytx.center.server.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.ytx.center.server.config.lottery.LotteryInfoConfig;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.extension.enums.LotteryTypeEnum;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collector;

@Component
public class PositionValidator {

    //private Map<LotteryTypeEnum, Range> ranger;

    private Table<SourceSiteEnum, LotteryTypeEnum, Range> table = HashBasedTable.create();

//    @Autowired
//    private LotteryInfoConfig lotteryInfoConfig;

    public PositionValidator(LotteryInfoConfig lotteryInfoConfig){
        /*ranger = Maps.newHashMapWithExpectedSize(16);
        ranger.put(LotteryTypeEnum.BJPK10, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSSC, new Range(0,80));
        ranger.put(LotteryTypeEnum.CQHLSX, new Range(0,80));
        ranger.put(LotteryTypeEnum.XYSSC, new Range(0,80));
        ranger.put(LotteryTypeEnum.XYFT, new Range(0,80));
        ranger.put(LotteryTypeEnum.SGFT, new Range(0,80));
        ranger.put(LotteryTypeEnum.XGC, new Range(0,80));
        ranger.put(LotteryTypeEnum.XYFFC, new Range(0,80));
        ranger.put(LotteryTypeEnum.HL5FC, new Range(0,80));
        ranger.put(LotteryTypeEnum.TW5FC, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSSSC, new Range(0,80));
        ranger.put(LotteryTypeEnum.TJSSC, new Range(0,80));
        ranger.put(LotteryTypeEnum.XJSSC, new Range(0,80));
        ranger.put(LotteryTypeEnum.GDKL10F, new Range(0,80));
        ranger.put(LotteryTypeEnum.GD11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.SYYDJ, new Range(0,80));
        ranger.put(LotteryTypeEnum.CQXYLC, new Range(0,80));
        ranger.put(LotteryTypeEnum.AZXY5, new Range(0,80));
        ranger.put(LotteryTypeEnum.AZXY8, new Range(0,80));
        ranger.put(LotteryTypeEnum.AZXY10, new Range(0,80));
        ranger.put(LotteryTypeEnum.AZXY20, new Range(0,80));
        ranger.put(LotteryTypeEnum.BJKL8, new Range(0,80));
        ranger.put(LotteryTypeEnum.JX11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.JS11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.AH11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.SH11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.LL11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.HB11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.GX11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.JL11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.NMG11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.ZJ11X5, new Range(0,80));
        ranger.put(LotteryTypeEnum.GXK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.JLK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.HBK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.NMGK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.AHK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.FJK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.HBK3_01, new Range(0,80));
        ranger.put(LotteryTypeEnum.BJK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.TJKL10F, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSFT, new Range(0,80));
        ranger.put(LotteryTypeEnum.GXKL10F, new Range(0,80));
        ranger.put(LotteryTypeEnum.FCSSQ, new Range(0,80));
        ranger.put(LotteryTypeEnum.CJDLT, new Range(0,80));
        ranger.put(LotteryTypeEnum.FC3D, new Range(0,80));
        ranger.put(LotteryTypeEnum.FC7LC, new Range(0,80));
        ranger.put(LotteryTypeEnum.TCPL3, new Range(0,80));
        ranger.put(LotteryTypeEnum.TCPL5, new Range(0,80));
        ranger.put(LotteryTypeEnum.TC7XC, new Range(0,80));
        ranger.put(LotteryTypeEnum.JS6HC, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSK3_01, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSKL10F, new Range(0,80));
        ranger.put(LotteryTypeEnum.JSKL8, new Range(0,80));
        ranger.put(LotteryTypeEnum.JS11X5_01, new Range(0,80));
        ranger.put(LotteryTypeEnum.PCDDXY28, new Range(0,80));
        ranger.put(LotteryTypeEnum.TWBG, new Range(0,80));
        ranger.put(LotteryTypeEnum.SHK3, new Range(0,80));
        ranger.put(LotteryTypeEnum.GZK3, new Range(0,80));*/

        /*Map<String, List<Node>> lotterySource = lotteryInfoConfig.getLotterySource();
        List<LotteryTypeEnum> lotteryTypeEnumList = lotterySource.entrySet().stream()
                .map(p -> LotteryTypeEnum.valueOf(p.getKey()))
                .collect(Collectors.toList());*/

        /*Map<LotteryTypeEnum,Range> newMap = lotterySource.entrySet().stream().collect(Collector.of(
                ()-> new HashMap<LotteryTypeEnum,Range>(),
                        (mutableMap,entryItem)-> mutableMap.put(
                                LotteryTypeEnum.valueOf(entryItem.getKey()),
                                new Range(80,20)),
                        (map1,map2)->{ map1.putAll(map2); return map1;}
                ));*/
        //Table<SourceSiteEnum, LotteryTypeEnum, Range> table = HashBasedTable.create();
        /*lotteryInfoConfig.getLotterySource().entrySet().stream().collect(Collector.of(
                ()-> HashBasedTable.create(),
                (mutableMap,entryItem)-> {
                    int[] index ={0};
                    table.put(SourceSiteEnum.valueOf(entryItem.getKey()),
                              LotteryTypeEnum.valueOf(entryItem.getValue().get(index[0]).getName()),
                              new Range(entryItem.getValue().get(index[0]++)));
                    },
                (map1,map2)->{ map1.putAll(map2); return map1;}
        ));*/

        lotteryInfoConfig.getLotterySource().entrySet().stream().collect(Collector.of(
                ()-> HashBasedTable.create(),(mutableMap,entryItem)-> {
                    entryItem.getValue().stream().forEach(
                            node ->table.put(SourceSiteEnum.valueOf(entryItem.getKey()),
                                    LotteryTypeEnum.valueOf(node.getName()),
                                    new Range(node))
                    );
                },
                (map1,map2)->{ map1.putAll(map2); return map1;}
        ));

    }

    public  boolean validate(String[] numbers, SourceSiteEnum sourceSiteEnum, LotteryTypeEnum lotteryTypeEnum){
        Range range = table.get(sourceSiteEnum, lotteryTypeEnum);
        return validate(numbers, range.getMin(), range.getMax());
    }

    private  boolean validate(String[] numbers, int min, int max){
        boolean result = Arrays.stream(numbers).allMatch(p->
                {
                    int x = 0;
                    return StringUtils.isNumeric(p) && min <= (x=Integer.parseInt(p)) && max > x;
                }
        );
        return result;
    }

    class Range {
        private Node node;
//        private int min = node.getMin();
//        private int max = node.getMax();
        Range(Node node){
            this.node = node;
        }
        int getMin(){
            return node.getMin();
        }

        int getMax(){
            return node.getMax();
        }
    }

}
