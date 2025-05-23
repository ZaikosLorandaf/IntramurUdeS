package ca.usherbrooke.fgen.api.backend;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OGClass {
    ListLeague listLeague;

    public OGClass() {
        mv = new MonVecteur();
        mv.message += "password = " + mv.password;
        listLeague = new ListLeague();
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
        boolean resultAdd = listLeague.getLeague(0);
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
