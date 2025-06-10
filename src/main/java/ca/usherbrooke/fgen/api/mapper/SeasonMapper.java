package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.Season;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeasonMapper {
    List<Season> selectAll();
    Season selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    void insert(@Param("season") Season Season);
    Integer getLastId();
}
