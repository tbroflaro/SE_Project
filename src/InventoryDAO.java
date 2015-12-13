import java.util.ArrayList;

/**
 * Created by Draven on 12/11/2015.
 */
public class InventoryDAO {

    private ArrayList id;

    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";

    public InventoryDAO() {
    }

    private void setInventory()
    {

    }

    public ArrayList getID()
    {
        return id;
    }
}
