package ca.usherbrooke.fgen.api.service.objectServices;

import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TemplateService<ListType> {
    public void addItem(ListType item) {
        // Ajouter l'équipe à la base de données via le mapper
        insert(item);
        add(item);
    }

    public ListType unescapeEntities(ListType item) {
        setName(item);
        return item;
    }

    public List<ListType> unescapeEntities(List<ListType> items) {
        return items
                .stream()
                .map(this::unescapeEntities)
                .collect(Collectors.toList());
    }

    // Getter
    public List<ListType> getItems() {
        List<ListType> items = selectAll();
        for (ListType item : items) {
            add(item);
        }
        return unescapeEntities(items);
    }

    public ListType getItem(@PathParam("id") Integer id) {
        ListType item = selectOne(id);
        if(item != null){
            add(item);
            return unescapeEntities(item);
        }
        return null;
    }

    // Methodes abstraites
    protected abstract List<ListType> selectAll();
    protected abstract ListType selectOne(Integer id);

    /**
     * Méthode pour ajouter l'item à la liste générale Java
     * @param item Item à ajouter
     */
    protected abstract void add(ListType item);

    /**
     * Insert l'item dans la base de données
     * @param item Item à ajouter à la base de données
     */
    protected abstract void insert(ListType item);


    protected abstract void setName(ListType item);
}
