package com.ytx.center.server.utils;

import com.google.common.base.Optional;
import com.ytx.center.server.entity.Position;

public interface IPositionPolicy {
    public Optional<Position> calculate(String[] winNumbers);
}
