import java.sql.*;

/**
 * Created by Draven on 12/11/2015.
 */
public class AbilityScoreDAO {

    private int id;

    private final String JDBC_DRIVE = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=DUNGEONS_AND_DRAGONS;";

    public AbilityScoreDAO() {
    }

    public void writeAbilityScores(int str, int dex, int con, int wis, int inte, int cha)
    {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;

        try{
            Class.forName(JDBC_DRIVE);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();
            sql = "select * from Race";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID");

                System.out.println(id + " this is a test");
                System.out.println("It went through");
            }
            rs.close();
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
