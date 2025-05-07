import java.util.Vector;

public class ListPlayer {
    public ListPlayer(int max)
    {
        maxPlayer = max;
        list = new Vector<Player>();
    }
    public boolean addPlayer(Player obj)
    {
        if(list.size() >= maxPlayer) return false;
        list.addElement(obj);
        return true;
    }
    public boolean removePlayer(int index)
    {
        if(list.isEmpty() || list.size() <= index) return false;
        list.remove(index);
        return true;
    }
    public boolean setNamePlayer(int index, String fn, String ln)
    {

    }
    public Player getPlayer(int index)
    {
        try{
            list.get(index);
        }
        catch (ArrayIndexOutOfBoundsException)
        {

        }
    }

    private int maxPlayer;
    private Vector<Player> list;
}
