package com.ytx.center.server.utils;

import com.google.common.base.Optional;
import com.ytx.center.server.entity.Position;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DefaultPositionPolicy implements  IPositionPolicy{
    public Optional<Position> calculate(String[] winNumbers){
        Position p = new Position();
        int[] idx = { 1 };
        Arrays.stream(winNumbers).forEach(number -> {
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
}
