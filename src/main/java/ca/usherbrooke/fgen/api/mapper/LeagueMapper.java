package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeagueMapper {
    List<League> selectAll();
    League selectOne(@Param("id") Integer id);
    List<League> selectFromSport(@Param("id_sport") Integer id_sport);
    void deleteOne(@Param("id") Integer id);
    void insert(@Param("league") League league);
    Integer getLastId();
}
