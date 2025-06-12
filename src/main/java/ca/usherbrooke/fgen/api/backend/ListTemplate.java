package ca.usherbrooke.fgen.api.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ListTemplate<ObjectType, NameType> {
    protected Map<Integer, ObjectType> mapId;    // Converssion d'un id vers l'objet
    protected Map<NameType, Integer> mapNameId;  // Converssion nom objet vers id

    // Constructeur
    /**
     * Constructeur par defaut
     */
    public ListTemplate(){
        this.mapId = new HashMap<>();
        this.mapNameId = new HashMap<>();
    }

    // Methodes
    /**
     * Ajouter un element dans le map qui converti l'id en objet
     * et le map qui converti l'objet en id
     *
     * @param item element a ajouter aux maps
     *
     * @return 1 reussi, 0 echec
     */
    protected int addItem(ObjectType item){
        if (!this.mapId.containsKey(getId(item)) && !this.mapNameId.containsKey(getName(item))) {
            this.mapId.put(getId(item), item);
            this.mapNameId.put(getName(item), getId(item));
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * ajouter plusieurs element dans les maps a partir d'une liste
     *
     * @param items liste d'objet a ajouter aux maps
     *
     * @return nombre d'elements ajoutes
     */
    protected int addItems(List<ObjectType> items){
        int counter = 0;
        for (ObjectType item : items)
            counter += this.addItem(item);

        return counter;
    }

    /**
     * retirer un element des maps
     *
     * @param id id de l'objet a retirer
     *
     * @return retrait reussi?
     */
    protected boolean removeItem(int id){
        if(this.mapId.containsKey(id) && this.mapNameId.containsKey(getName(this.mapId.get(id)))) {
            this.mapNameId.remove(getName(this.mapId.get(id)));
            this.mapId.remove(id);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Efface le contenu des maps
     */
    protected void clearMap(){
        this.mapId.clear();
        this.mapNameId.clear();
    }

    // Getter
    public ObjectType getItem(int id) { return this.mapId.getOrDefault(id, null); }
    public ObjectType getItem(NameType nom) { return this.mapId.getOrDefault(this.mapNameId.get(nom), null); }
    public List<ObjectType> getAllItems() { return new ArrayList<>(this.mapId.values()); }
    public int getMapSize() { return this.mapId.size(); }
    public Map<Integer, ObjectType> getMapItems() { return this.mapId; }
    public List<Integer> getMapIds() { return new ArrayList<>(this.mapId.keySet()); }
    public boolean checkItemExist(ObjectType item) { return this.mapId.containsKey(getId(item)); }
    public Map<NameType, Integer> getMapNameId() { return this.mapNameId; }

    // Methodes abtraites
    abstract int getId(ObjectType item);
    abstract NameType getName(ObjectType item);
}
