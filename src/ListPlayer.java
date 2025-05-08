import java.util.Vector;

public class ListPlayer {
    private int maxPlayer;
    private Vector<Player> list;


    public ListPlayer(int max) {
        maxPlayer = max;
        list = new Vector<Player>();
    }

    public ListPlayer() {
        maxPlayer = 100;
        list = new Vector<Player>();
    }

    public boolean addPlayer(Player obj) //return false if list over max size;
    {
        if (list.size() >= maxPlayer) return false;
        list.addElement(obj);
        return true;
    }

    public boolean removePlayer(int index) //return false if index out of bounds
    {
        if (list.isEmpty() || list.size() <= index) return false;
        list.remove(index);
        return true;
    }

    public boolean removePlayer(Player player) //return false if list is empty or player not found
    {
        if (list.isEmpty()) return false;
        return list.remove(player);
    }

    public Player getPlayer(int index) //return null if index not in bound
    {
        Player player;
        try
        {
            player = list.get(index);
        }
        catch (Exception e)
        {
            player = null;
        }
        return player;
    }

    public int getIndex(Player player) //return -1 if it's not find
    {
        return list.indexOf(player);
    }

    public void printList() {
        if (list.size() <= 0) System.out.println("Liste vide");
        else {
            System.out.println("------LISTE------");
            System.out.printf("Size = %d\nMax = %d\n",getSize(),getMaxPlayer());
            for (int i = 0; i < list.size(); i++) {
                getPlayer(i).printPlayer();
            }
            System.out.println("------FIN------");
        }
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean setMaxPlayer(int max) {
        if(max >= getSize())
        {
            maxPlayer = max;
            return true;
        }
        return false;
    }

    public int getSize()
    {
        return list.size();
    }
}



