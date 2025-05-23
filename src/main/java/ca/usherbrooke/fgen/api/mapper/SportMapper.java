package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SportMapper {
    List<Sport> selectAll();
    Sport selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    void insertSport(@Param("sport") Sport sport);
    Integer getNewId();
}
