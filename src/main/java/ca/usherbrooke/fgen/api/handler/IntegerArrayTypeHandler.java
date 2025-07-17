package ca.usherbrooke.fgen.api.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class IntegerArrayTypeHandler implements TypeHandler<List<Integer>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType)
            throws SQLException {
        Connection connection = ps.getConnection();
        Integer[] array = parameter.toArray(new Integer[0]);
        Array sqlArray = connection.createArrayOf("integer", array);
        ps.setArray(i, sqlArray);
    }

    @Override
    public List<Integer> getResult(ResultSet rs, String columnName) throws SQLException {
        Array array = rs.getArray(columnName);
        return Arrays.asList((Integer[]) array.getArray());
    }

    @Override
    public List<Integer> getResult(ResultSet rs, int columnIndex) throws SQLException {
        Array array = rs.getArray(columnIndex);
        return Arrays.asList((Integer[]) array.getArray());
    }

    @Override
    public List<Integer> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Array array = cs.getArray(columnIndex);
        return Arrays.asList((Integer[]) array.getArray());
    }
}
