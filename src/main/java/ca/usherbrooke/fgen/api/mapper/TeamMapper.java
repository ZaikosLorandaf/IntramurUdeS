package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> selectTeams();
    Team selectOneTeam(@Param("id") int id);
    void deleteOneTeam(@Param("id") int id);
    void insert(Team team);
    int getLastId();
}