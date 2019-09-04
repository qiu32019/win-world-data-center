package com.ytx.center.server.config.lottery;

import lombok.Data;

@Data
public class Node {
    private String url;
    private String historyUrl;
    private String positions;
    private String issue;
    private String name;
    private String description;
    private String openWinTime;
    private int interval;
    private int min;
    private int max;
    private boolean enable = true;
    //1 當前單個結果， 2 歷史結果
    private int passageway = 2;
    private String positionPolicy;
    private int length;

    public void setName(String name){
        this.name = name.toUpperCase();
    }
}
