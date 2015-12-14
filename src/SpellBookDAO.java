import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
/**
 * Created by Draven on 12/11/2015.
 */
public class SpellBookDAO {

    private ArrayList id = new ArrayList();

    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";

    public SpellBookDAO() {
    }

    public void setSpellBook(String className)
    {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs;

        switch (className)
        {
            case "Bard":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 12";
                break;
            case "Cleric":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 11 AND ID < 25";
                break;
            case "Druid":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 24 AND ID < 37";
                break;
            case "Paladin":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 36 AND ID < 43";
                break;
            case "Ranger":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 42 AND ID < 47";
                break;
            case "Sorcerer":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 46 AND ID < 58";
                break;
            case "Warlock":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 57 AND ID < 67";
                break;
            case "Wizard":
                sql = "SELECT SPELL_ID FROM SPELL_BOOK WHERE ID > 66";
                break;
            default:
                sql = "SELECT * FROM SPELL_BOOK FETCH FIRST ROW ONLY";
                break;
        }


        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                id.add(rs.getInt("SPELL_ID"));
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
