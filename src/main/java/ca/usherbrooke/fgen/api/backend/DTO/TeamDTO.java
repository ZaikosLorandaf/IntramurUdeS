package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.DTO.stats.StatPlayerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter pour donner juste ce qu'il faut pour le json retour
 */
public class TeamDTO {
    List<PlayerDTO> listPlayerDTO = new ArrayList<>();

}
