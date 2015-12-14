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

    public void writeAbilityScores(int str, int dex, int con, int wis, int inte, int cha, String raceID)
    {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            sql = "SELECT ABILITY_SCORE_1, ABILITY_SCORE_2, ABILITY_SCORE_INCREASE_1, ABILITY_SCORE_INCREASE_2 FROM RACE WHERE ID = " + raceID;

            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                switch(rs.getString("ABILITY_SCORE_1"))
                {
                    case "CON":
                        con += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "STR":
                        str += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "DEX":
                        dex += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "INT":
                        inte += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "WIS":
                        wis += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "CHA":
                        cha += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    case "ALL":
                        con += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        str += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        dex += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        inte += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        wis += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        cha += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_1"));
                        break;
                    default:
                        break;
                }

                switch(rs.getString("ABILITY_SCORE_2"))
                {
                    case "CON":
                        con += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "STR":
                        str += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "DEX":
                        dex += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "INT":
                        inte += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "WIS":
                        wis += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "CHA":
                        cha += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    case "ALL":
                        con += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        str += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        dex += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        inte += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        wis += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        cha += Integer.parseInt(rs.getString("ABLITY_=SCORE_INCREASE_2"));
                        break;
                    default:
                        break;
                }
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
