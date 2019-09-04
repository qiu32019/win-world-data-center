package com.ytx.center.server.config.lottery;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "lottery")
@Component
@Data
public class LotteryInfoConfig {
    private Map<String, List<Node>> lotterySource =  Maps.newLinkedHashMap();

    private String errorProp;

    private String resultProp;

    private String dataProp;
}
