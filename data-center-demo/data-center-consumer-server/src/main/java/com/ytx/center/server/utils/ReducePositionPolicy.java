package com.ytx.center.server.utils;

import com.google.common.base.Optional;
import com.ytx.center.server.config.lottery.Node;
import com.ytx.center.server.entity.Position;
import lombok.Data;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * openResult length 10, but we only use 5 or other
 * $ball_1 = intval($List['openResult'][0]);
 * $ball_2 = intval($List['openResult'][1]);
 * $ball_3 = intval($List['openResult'][2]);
 * $ball_4 = intval($List['openResult'][3]);
 * $ball_5 = intval($List['openResult'][4]);
 */
@Data
public class ReducePositionPolicy implements IPositionPolicy {
    private Node node;

    public ReducePositionPolicy(Node node){
        this.node = node;
    }

    @Override
    public Optional<Position> calculate(String[] winNumbers) {
        Position p = new Position();
        int[] idx = { 1 };
        Arrays.stream(winNumbers).limit(getCount()).forEach(number -> {
            Method method = ReflectionUtils.findMethod(Position.class, "setP"+idx[0]++, new Class<?>[]{String.class});
            try {
                method.invoke(p, number);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return Optional.of(p);
    }

    public int getCount(){
        return node.getLength();
    }
}
