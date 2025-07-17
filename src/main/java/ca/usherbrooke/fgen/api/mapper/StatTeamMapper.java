package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatTeam;
import ca.usherbrooke.fgen.api.service.objectServices.stats.StatTeamService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatTeamMapper {
    List<StatTeam> selectAll();
    StatTeam selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    void insert(@Param("statTeam") StatTeam statTeam);
    Integer getLastId();
}
