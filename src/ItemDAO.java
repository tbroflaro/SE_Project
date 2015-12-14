import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 * Created by Draven on 12/11/2015.
 */
public class ItemDAO {

    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";


    public ItemDAO() {
    }

    public String getItemName(String id)
    {
        String name = null;

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            sql = "SELECT NAME FROM ITEM WHERE ID = "+id;
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                name = rs.getString("NAME");
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

        return name;
    }
}
