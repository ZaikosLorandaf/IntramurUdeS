package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> selectTeams();
    Team selectOneTeam(@Param("id") int id);
    void deleteOneTeam(@Param("id") int id);
    void insertTeam(Team team);
    int getNewId();
}