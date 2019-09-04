package com.ytx.center.server.extension.handler;

import com.alibaba.fastjson.JSON;
import com.ytx.center.server.entity.Position;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Position.class)
@MappedJdbcTypes(value={JdbcType.VARCHAR})
public class PositionTypeHandler extends BaseTypeHandler<Position> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Position parameter,
                                    JdbcType jdbcType) throws SQLException {

        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Position getNullableResult(ResultSet rs, String columnName)
            throws SQLException {

        return JSON.parseObject(rs.getString(columnName), Position.class);
    }

    @Override
    public Position getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return JSON.parseObject(rs.getString(columnIndex), Position.class);
    }

    @Override
    public Position getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {

        return JSON.parseObject(cs.getString(columnIndex), Position.class);
    }

}
