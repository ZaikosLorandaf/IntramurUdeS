package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.League;
import ca.usherbrooke.fgen.api.backend.Match;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MatchMapper {
    List<Match> selectAll();
    Match selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    void insert(@Param("match") Match match);
    Integer getLastId();
}