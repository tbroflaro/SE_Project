import java.sql.*;

/**
 * Created by Draven on 12/11/2015.
 */
public class AbilityScoreDAO {

    private int id;
    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";

    public AbilityScoreDAO() {
    }

    public void writeAbilityScores(int str, int dex, int con, int wis, int inte, int cha)
    {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            sql = "INSERT INTO ABILITY_SCORE (STRENGTH, DEXTERITY, CONSITUTION, INTELLIGENCE, WISDOM, CHARISMA) OUTPUT inserted.ID VALUES (" + str + ", " + dex + ", " + con + ", " + inte + ", " + wis + ", " + cha + ")";

            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                id = rs.getInt("ID");
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

    public int getID()
    {
        return id;
    }
}
