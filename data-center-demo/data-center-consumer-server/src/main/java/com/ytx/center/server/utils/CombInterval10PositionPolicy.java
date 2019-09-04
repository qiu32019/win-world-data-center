package com.ytx.center.server.utils;

import com.google.common.base.Optional;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.entity.Position;

/**
 * $ball_1_tp = ($ball_1 + $ball_11) % 10;
 * $ball_2_tp = ($ball_2 + $ball_12) % 10;
 * $ball_3_tp = ($ball_3 + $ball_13) % 10;
 * $ball_4_tp = ($ball_4 + $ball_14) % 10;
 * $ball_5_tp = ($ball_5 + $ball_15) % 10;
 */
public class CombInterval10PositionPolicy implements IPositionPolicy {
    private Node node;

    public CombInterval10PositionPolicy(Node node){
        this.node = node;
    }

    @Override
    public Optional<Position> calculate(String[] winNumbers) {
        Position p = new Position();
        p.setP1(winNumbers[0]+winNumbers[10]);
        p.setP2(winNumbers[1]+winNumbers[11]);
        p.setP3(winNumbers[2]+winNumbers[12]);
        p.setP4(winNumbers[3]+winNumbers[13]);
        p.setP5(winNumbers[4]+winNumbers[14]);
        return Optional.of(p);
    }
}
