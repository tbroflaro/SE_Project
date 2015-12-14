import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

/**
 * Created by Draven on 12/11/2015.
 */
public class InventoryDAO {

    private ArrayList id = new ArrayList();

    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";

    public InventoryDAO() {
    }

    public void setInventory(String className)
    {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs;

        switch (className)
        {
            case "Barbarian":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID < 6";
                break;
            case "Bard":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 5 AND ID < 13";
                break;
            case "Cleric":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 13 AND ID < 24";
                break;
            case "Druid":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 24 AND ID < 30";
                break;
            case "Fighter":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 29 AND ID < 39";
                break;
            case "Monk":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 38 AND ID < 43";
                break;
            case "Paladin":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 42 AND ID < 52";
                break;
            case "Ranger":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 51 AND ID < 58";
                break;
            case "Rogue":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 57 AND ID < 58";
                break;
            case "Sorcerer":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 67 AND ID < 74";
                break;
            case "Warlock":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 73 AND ID < 84";
                break;
            case "Wizard":
                sql = "SELECT ITEM_ID FROM INVENTORY WHERE ID > 83";
                break;
        }


        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                id.add(rs.getInt("ITEM_ID"));
            }


            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList getID()
    {
        return id;
    }
}
