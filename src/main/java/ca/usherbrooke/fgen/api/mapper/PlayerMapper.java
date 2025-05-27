package ca.usherbrooke.fgen.api.mapper;

import ca.usherbrooke.fgen.api.backend.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> selectPlayers();
    Player selectOnePlayer(@Param("id") int id);
    void deleteOnePlayer(@Param("id") int id);
    void insertPlayer(Player player);
}