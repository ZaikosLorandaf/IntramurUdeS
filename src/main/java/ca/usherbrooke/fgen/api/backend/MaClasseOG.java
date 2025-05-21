package ca.usherbrooke.fgen.api.backend;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaClasseOG {
    public MaClasseOG() {
        mv = new MonVecteur();
        mv.message += "password = " + mv.password;
    }

    public String getMessage() {
        return mv.message;
    }
    private MonVecteur mv;
}
