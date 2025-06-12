package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> selectAll();
    Player selectOne(@Param("id") int id);
    void deleteOne(@Param("id") int id);
    void insert(Player player);
    int getLastId();
}