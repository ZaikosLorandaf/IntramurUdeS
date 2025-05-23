package ca.usherbrooke.fgen.api.backend;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class OGClass {
    ListLeague listLeague;
    ListSport listeSport;

    public OGClass() {
        mv = new MonVecteur();
        mv.message += "password = " + mv.password;
        listLeague = new ListLeague();
        listeSport = new ListSport();
        trashData();
    }

    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        for (int i = 0; i < listeSport.getSize(); i++) {
            Sport sport = listeSport.getSport(i);

            // Récupère les noms des ligues de ce sport
            ListLeague leagues = sport.getListLeague();
            List<String> leagueNames = new ArrayList<>();
            for (int j = 0; j < leagues.getSize(); j++) {
                leagueNames.add(leagues.getLeague(j).getName());
            }

            // Ajoute le sport et ses ligues dans le JSON
            JSONObject sportJson = new JSONObject()
                    .put("name", sport.getName())
                    .put("id", sport.getId())
                    .put("seasons", new JSONArray(leagueNames));

            sports.put(sportJson);
        }

        return sports.toString();
    }


    public void trashData(){
        Sport bb = new Sport("Basket Ball", -1);
        bb.addLeague(new League("Tintin au congo"));
        bb.addLeague(new League("Les pythoniste"));
        listeSport.addSport(bb);

        Sport vb = new Sport("Volley Ball", -2);
        vb.addLeague(new League("Les coucouille de Gerard"));
        vb.addLeague(new League("Les c++ teams"));
        listeSport.addSport(vb);

    }

    public String newLeague(String nom)
    {

        League newLeague = new League(nom);
        boolean resultAdd = listLeague.addLeague(newLeague);
        String result;
        if (!resultAdd) {
            result = "<div>erreur</div>";
        }
        else {
            result = "<div>";
            result += listLeague.getLeague(listLeague.getSize()-1).getName();

        }
        return result;
//        return "<div> coco</div>";
    }

    public String getLeague() {
        String result = Integer.toString(listLeague.getSize());
        System.out.println("League: " + result);
        //result = "<div>" + result +  "</div>";
        //String result = "<div> aaaaa </div>";
        return result;
    }

    public String getMessage() {
        return mv.message;
    }

    public String addTeam(String nomLigue)
    {
        boolean resultAdd = true;//listLeague.getLeague(0);
        String result;
        if (!resultAdd) {
            result = "<div>erreur</div>";
        }
        else {
            result = "<div>";
            result += listLeague.getLeague(listLeague.getSize()-1).getName();

        }
        return result;
    }

    private MonVecteur mv;
}
