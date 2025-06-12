package ca.usherbrooke.fgen.api.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class IntegerArrayToListTypeHandler implements TypeHandler<List<Integer>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        Connection conn = ps.getConnection();
        Integer[] integers = parameter.toArray(new Integer[0]);
        Array array = conn.createArrayOf("INTEGER", integers);
        ps.setArray(i, array);
    }

    @Override
    public List<Integer> getResult(ResultSet rs, String columnName) throws SQLException {
        return convertToList(rs.getArray(columnName));
    }

    @Override
    public List<Integer> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertToList(rs.getArray(columnIndex));
    }

    @Override
    public List<Integer> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertToList(cs.getArray(columnIndex));
    }

    private List<Integer> convertToList(Array array) throws SQLException {
        if (array == null) return null;
        Object[] values = (Object[]) array.getArray();
        List<Integer> result = new ArrayList<>();
        for (Object value : values) {
            result.add((Integer) value);
        }
        return result;
    }
}