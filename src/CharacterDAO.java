import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Draven on 12/11/2015.
 */
public class CharacterDAO {

    private AbilityScoreDAO acDAO = new AbilityScoreDAO();
    private ClassDAO cDAO = new ClassDAO();
    private RaceDAO rDAO = new RaceDAO();
    private InventoryDAO iDAO = new InventoryDAO();
    private SpellBookDAO sbDAO = new SpellBookDAO();
    private ItemDAO itemDAO = new ItemDAO();
    private SpellDAO spellDAO = new SpellDAO();

    private PrintWriter writer = null;
    private Character character = null;

    private int id;
    private String prof = "";

    private final String DB_URL = "jdbc:sqlserver://localhost:50396;" + "databaseName=DUNGEON_AND_DRAGONS;";
    private final String USER = "sa";
    private final String PASSWORD = "Yhlnjlk1";

    private Connection conn = null;
    private Statement stmt = null;
    private String sql = null;
    private ResultSet rs;

    public CharacterDAO(Character character)
    {
        this.character = character;
    }

    public void writeCharacterToDatabase()
    {
        acDAO.writeAbilityScores(character.getStr(), character.getDex(), character.getCon(), character.getInte(), character.getWis(), character.getCha());
        character.setAbilityScoreID(acDAO.getID());

        sbDAO.setSpellBook(character.getcClass());
        if (sbDAO.getID().size() < 3)
            character.setSpellBookID(null);
        else
            character.setSpellBookID(sbDAO.getID());

        iDAO.setInventory(character.getcClass());
        character.setInventoryID(iDAO.getID());

        cDAO.setID(character.getcClass());
        character.setClassID(cDAO.getID());

        rDAO.setID(character.getRace());
        character.setRaceID(rDAO.getID());

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            sql = "INSERT INTO CHARACTER (RACE_ID, CLASS_ID, INVENTORY_ID, SPELL_BOOK_ID, ABILITY_SCORE_ID, CHAR_NAME, PLAYER_NAME, CHAR_LVL) OUTPUT inserted.ID  VALUES (" + character.getRaceID() + ", " + character.getClassID() + ", " + character.getInvetoryID() + ", " + character.getSpellBookID() + ", " + character.getAbilityScoreID() + ", " + character.getCName() + ", " + character.getPName() + ", " + character.getLvl() + ")";

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
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportToFile() throws IOException {


        writer = new PrintWriter("character.txt");
        writer.print("Character name: " + character.getCName());
        writer.println("Player name: " + character.getPName());
        writer.println("Class: " + character.getClass());
        writer.println("Race: " + character.getRace());
        writer.println("Attributes: \n\tStrength: " + character.getStr());
        writer.println("\tDexterity: " + character.getDex());
        writer.println("\tConsitution: " + character.getCon());
        writer.println("\tIntelligence: " + character.getInte());
        writer.println("\tWisdom: " + character.getWis());
        writer.println("\tCharisma: " + character.getCha());

        writer.println("Proficiencies: " + cDAO.getProf(character.getClassID()));
        writer.println("Feats: " + rDAO.getFeats(character.getRaceID()));
        writer.println("Inventory: ");
        ArrayList items = character.getInvetoryID();

        for (int x = 0; x < items.size(); x ++)
        {
            writer.println("\t "+ itemDAO.getItemName((int)items.get(x)));
        }

        writer.println("Spell List: ");
        switch (character.getcClass().toLowerCase())
        {
            case "bard":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("1"));
                    writer.println("\t" + spellDAO.getSpellName("2"));
                    writer.println("\t" + spellDAO.getSpellName("4"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("1"));
                    writer.println("\t" + spellDAO.getSpellName("2"));
                    writer.println("\t" + spellDAO.getSpellName("4"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                }
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "cleric":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "druid":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "paladin":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "ranger":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "sorcerer":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "warlock":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            case "wizard":
                if (character.getLvl().equals(1))
                    break;
                else if(character.getLvl().equals(2))
                    break;
                else if(character.getLvl().equals(3))
                    break;
                else if(character.getLvl().equals(4))
                    break;
                else
                    break;
            default:
                writer.println("N/A");
                break;
        }

        writer.close();
    }

    public int getID()
    {
        return id;
    }


}
