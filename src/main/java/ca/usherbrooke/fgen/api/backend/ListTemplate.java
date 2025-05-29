package ca.usherbrooke.fgen.api.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ListTemplate<ObjectType, NameType> {
    private Map<Integer, ObjectType> mapId;    // Converssion d'un id vers l'objet
    private Map<NameType, Integer> mapNameId;  // Converssion nom objet vers id

    // Constructeur
    public ListTemplate(){
        this.mapId = new HashMap<>();
        this.mapNameId = new HashMap<>();
    }

    // Methodes
    protected int addItem(ObjectType item){
        if (!this.mapId.containsKey(getId(item)) && !this.mapNameId.containsKey(getName(item))) {
            this.mapId.put(getId(item), item);
            this.mapNameId.put(getName(item), getId(item));
            return 1;
        } else {
            return 0;
        }
    }

    protected int addItems(List<ObjectType> items){
        int counter = 0;
        for (ObjectType item : items)
            counter += this.addItem(item);

        return counter;
    }

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

    protected void clearMap(){
        this.mapId.clear();
    }

    // Getter
    public ObjectType getItem(int id) { return this.mapId.getOrDefault(id, null); }
    public ObjectType getItem(NameType nom) { return this.mapId.getOrDefault(this.mapNameId.get(nom), null); }
    public List<ObjectType> getAllItems() { return new ArrayList<>(this.mapId.values()); }
    public int getMapSize() { return this.mapId.size(); }
    public Map<Integer, ObjectType> getMapItems() { return this.mapId; }
    public List<Integer> getMapIds() { return new ArrayList<>(this.mapId.keySet()); }
    public boolean checkItemExist(ObjectType item) { return this.mapId.containsKey(getId(item)); }

    // Methodes abtraites
    abstract int getId(ObjectType item);
    abstract NameType getName(ObjectType item);
}
