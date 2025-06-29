package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatPlayer;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatPlayerMapper {
    List<StatPlayer> selectAll();
    StatPlayer selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    void insert(@Param("statStatement") StatStatement statStatement);
    Integer getLastId();
}
