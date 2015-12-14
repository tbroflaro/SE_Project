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
        writer.println("Attributes: " + character.getStr());
        writer.println("\tDexterity: " + character.getDex());
        writer.println("\tConsitution: " + character.getCon());
        writer.println("\tIntelligence: " + character.getInte());
        writer.println("\tWisdom: " + character.getWis());
        writer.println("\tCharisma: " + character.getCha());

        writer.println("Proficiencies: " + cDAO.getProf(character.getClassID()));
        switch(character.getcClass().toLowerCase())
        {
            case "barbarian":
                    writer.println("\t\tPath of the Berserker: ");
                    writer.println("\t\t\t3: Frenxy");
                break;
            case "bard":
                writer.println("\t\tCollege of Valor:");
                writer.println("\t\t\t3: New Proficiencies:");
                writer.println("\t\t\t\tARMOR: MEDIUM, SHIELDS/WEAPONS: MARTIAL");
                writer.println("\t\t3: Combat Inspiration");
                break;
            case "cleric":
                writer.println("\t\tLife Domain:");
                writer.println("\t\t\tNew Spells:");
                writer.println("\t\t\t\t3: LESSER RESTORATION, SPIRITUAL WEAPON");
                if (character.getLvl().equals("5"))
                    writer.println("\t\t\t\t5: BEACON OF HOPE, REVIVFY");
                writer.println("\t\t\tNew Proficiencies:");
                writer.println("\t\t\t\tARMOR: HEAVY");
                writer.println("\t\t\tDisciple of Life");
                writer.println("\t\t\tChannel Divinity: Preserve LIfe");
                break;
            case "druid":
                writer.println("\t\tCircle of the Land:");
                writer.println("\t\t\tNew Spells:");
                writer.println("\t\t\t\tBonus Cantrip");
                writer.println("\t\t\t2: Natural Recovery");
                writer.println("\t\t\t: Circle Spells: Mountain");
                writer.println("\t\t\t\t3: SPIDER CLIMB, SPIKE GROWTH");
                if (character.getLvl().equals("5"))
                    writer.println("\t\t\t\t5: LIGHTNING BOLT, MELD INTO STONE");
                break;
            case "fighter":
                writer.println("\t\tChampion:");
                writer.println("\t\t\tImproved Critical");
                break;
            case "monk":
                writer.println("\t\tWay of the Open Hand:");
                writer.println("\t\t\tOpen Hand Techniques");
                break;
            case "paladin":
                writer.println("\t\tOath of the Ancients:");
                writer.println("\t\t\tNew Spells:");
                writer.println("\t\t\t\t3: ENSNARING STRIKE, SPEAK WITH ANIMALS");
                if (character.getLvl().equals("5"))
                    writer.println("\t\t\t\t5: MOONBEAM, MISTY STEP");
                writer.println("\t\t\tChannel Divinity: Nature's Wrath");
                break;
            case "ranger":
                writer.println("\t\tHunter:");
                writer.println("\t\t\tHunter's Prey:");
                writer.println("\t\t\t\tHorde Breaker");
                break;
            case "rogue":
                writer.println("\t\tAssassin:");
                writer.println("\t\t\t New Proficiencies:");
                writer.println("\t\t\t\tTOOLS: DISGUISE KIT, POISONER'S KIT");
                writer.println("\t\t\t3: Assassinate");
                break;
            case "sorcerer":
                writer.println("\t\tWild Magic:");
                writer.println("\t\t\tWild Magic Surge");
                writer.println("\t\t\tTides of Chaos");
                break;
            case "warlock":
                writer.println("\t\tFiend with Pact of the Tomb:");
                writer.println("\t\t\tNew Spells:");
                writer.println("\t\t\t\t1: BURNING HANDS, COMMAND");
                writer.println("\t\t\t\t2: BLINDNESS/DEAFNESS, SCORCHING RAY");
                writer.println("\t\t\t\t3: FIREBALL, STINKING CLOUD");
                writer.println("\t\t\t\t4: FIRE SHIELD, WALL OF FIRE");
                writer.println("\t\t\t\t5: FLAME STRIKE, HALLOW");
                writer.println("\t\t\tDark One's Blessing");
                break;
            case "Wizard":
                writer.println("\t\tSchool of Evocation:");
                writer.println("\t\t\t2: Evocation Savant");
                writer.println("\t\t\t2: Sculpt Spells");
                break;
        }
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
                    writer.println("\t" + spellDAO.getSpellName("6"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("1"));
                    writer.println("\t" + spellDAO.getSpellName("2"));
                    writer.println("\t" + spellDAO.getSpellName("4"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                    writer.println("\t" + spellDAO.getSpellName("6"));
                    writer.println("\t" + spellDAO.getSpellName("9"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("1"));
                    writer.println("\t" + spellDAO.getSpellName("2"));
                    writer.println("\t" + spellDAO.getSpellName("3"));
                    writer.println("\t" + spellDAO.getSpellName("4"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                    writer.println("\t" + spellDAO.getSpellName("6"));
                    writer.println("\t" + spellDAO.getSpellName("9"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("1"));
                    writer.println("\t" + spellDAO.getSpellName("2"));
                    writer.println("\t" + spellDAO.getSpellName("3"));
                    writer.println("\t" + spellDAO.getSpellName("4"));
                    writer.println("\t" + spellDAO.getSpellName("5"));
                    writer.println("\t" + spellDAO.getSpellName("6"));
                    writer.println("\t" + spellDAO.getSpellName("9"));
                    writer.println("\t" + spellDAO.getSpellName("10"));
                }
                break;
            case "cleric":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("12"));
                    writer.println("\t" + spellDAO.getSpellName("13"));
                    writer.println("\t" + spellDAO.getSpellName("14"));
                    writer.println("\t" + spellDAO.getSpellName("16"));
                    writer.println("\t" + spellDAO.getSpellName("17"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("12"));
                    writer.println("\t" + spellDAO.getSpellName("13"));
                    writer.println("\t" + spellDAO.getSpellName("14"));
                    writer.println("\t" + spellDAO.getSpellName("16"));
                    writer.println("\t" + spellDAO.getSpellName("17"));
                    writer.println("\t" + spellDAO.getSpellName("18"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("12"));
                    writer.println("\t" + spellDAO.getSpellName("13"));
                    writer.println("\t" + spellDAO.getSpellName("14"));
                    writer.println("\t" + spellDAO.getSpellName("16"));
                    writer.println("\t" + spellDAO.getSpellName("17"));
                    writer.println("\t" + spellDAO.getSpellName("18"));
                    writer.println("\t" + spellDAO.getSpellName("19"));
                    writer.println("\t" + spellDAO.getSpellName("20"));
                    writer.println("\t" + spellDAO.getSpellName("21"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("12"));
                    writer.println("\t" + spellDAO.getSpellName("13"));
                    writer.println("\t" + spellDAO.getSpellName("14"));
                    writer.println("\t" + spellDAO.getSpellName("15"));
                    writer.println("\t" + spellDAO.getSpellName("16"));
                    writer.println("\t" + spellDAO.getSpellName("17"));
                    writer.println("\t" + spellDAO.getSpellName("18"));
                    writer.println("\t" + spellDAO.getSpellName("19"));
                    writer.println("\t" + spellDAO.getSpellName("20"));
                    writer.println("\t" + spellDAO.getSpellName("21"));
                    writer.println("\t" + spellDAO.getSpellName("22"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("12"));
                    writer.println("\t" + spellDAO.getSpellName("13"));
                    writer.println("\t" + spellDAO.getSpellName("14"));
                    writer.println("\t" + spellDAO.getSpellName("15"));
                    writer.println("\t" + spellDAO.getSpellName("16"));
                    writer.println("\t" + spellDAO.getSpellName("17"));
                    writer.println("\t" + spellDAO.getSpellName("18"));
                    writer.println("\t" + spellDAO.getSpellName("19"));
                    writer.println("\t" + spellDAO.getSpellName("20"));
                    writer.println("\t" + spellDAO.getSpellName("21"));
                    writer.println("\t" + spellDAO.getSpellName("22"));
                    writer.println("\t" + spellDAO.getSpellName("23"));
                    writer.println("\t" + spellDAO.getSpellName("24"));
                }
                break;
            case "druid":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("25"));
                    writer.println("\t" + spellDAO.getSpellName("26"));
                    writer.println("\t" + spellDAO.getSpellName("28"));
                    writer.println("\t" + spellDAO.getSpellName("29"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("25"));
                    writer.println("\t" + spellDAO.getSpellName("26"));
                    writer.println("\t" + spellDAO.getSpellName("28"));
                    writer.println("\t" + spellDAO.getSpellName("29"));
                    writer.println("\t" + spellDAO.getSpellName("30"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("25"));
                    writer.println("\t" + spellDAO.getSpellName("26"));
                    writer.println("\t" + spellDAO.getSpellName("28"));
                    writer.println("\t" + spellDAO.getSpellName("29"));
                    writer.println("\t" + spellDAO.getSpellName("30"));
                    writer.println("\t" + spellDAO.getSpellName("31"));
                    writer.println("\t" + spellDAO.getSpellName("32"));
                    writer.println("\t" + spellDAO.getSpellName("33"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("25"));
                    writer.println("\t" + spellDAO.getSpellName("26"));
                    writer.println("\t" + spellDAO.getSpellName("27"));
                    writer.println("\t" + spellDAO.getSpellName("28"));
                    writer.println("\t" + spellDAO.getSpellName("29"));
                    writer.println("\t" + spellDAO.getSpellName("30"));
                    writer.println("\t" + spellDAO.getSpellName("31"));
                    writer.println("\t" + spellDAO.getSpellName("32"));
                    writer.println("\t" + spellDAO.getSpellName("33"));
                    writer.println("\t" + spellDAO.getSpellName("34"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("25"));
                    writer.println("\t" + spellDAO.getSpellName("26"));
                    writer.println("\t" + spellDAO.getSpellName("27"));
                    writer.println("\t" + spellDAO.getSpellName("28"));
                    writer.println("\t" + spellDAO.getSpellName("29"));
                    writer.println("\t" + spellDAO.getSpellName("30"));
                    writer.println("\t" + spellDAO.getSpellName("31"));
                    writer.println("\t" + spellDAO.getSpellName("32"));
                    writer.println("\t" + spellDAO.getSpellName("33"));
                    writer.println("\t" + spellDAO.getSpellName("34"));
                    writer.println("\t" + spellDAO.getSpellName("35"));
                    writer.println("\t" + spellDAO.getSpellName("36"));
                }
                break;
            case "paladin":
                if (character.getLvl().equals(1)) {
                    writer.println("\t N/A");
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("37"));
                    writer.println("\t" + spellDAO.getSpellName("38"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("37"));
                    writer.println("\t" + spellDAO.getSpellName("38"));
                    writer.println("\t" + spellDAO.getSpellName("39"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("37"));
                    writer.println("\t" + spellDAO.getSpellName("38"));
                    writer.println("\t" + spellDAO.getSpellName("39"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("37"));
                    writer.println("\t" + spellDAO.getSpellName("38"));
                    writer.println("\t" + spellDAO.getSpellName("39"));
                    writer.println("\t" + spellDAO.getSpellName("40"));
                    writer.println("\t" + spellDAO.getSpellName("41"));
                    writer.println("\t" + spellDAO.getSpellName("42"));
                }
                break;
            case "ranger":
                if (character.getLvl().equals(1)) {
                    writer.println("\t N/A");
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("43"));
                    writer.println("\t" + spellDAO.getSpellName("44"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("43"));
                    writer.println("\t" + spellDAO.getSpellName("44"));
                    writer.println("\t" + spellDAO.getSpellName("45"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("43"));
                    writer.println("\t" + spellDAO.getSpellName("44"));
                    writer.println("\t" + spellDAO.getSpellName("45"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("43"));
                    writer.println("\t" + spellDAO.getSpellName("44"));
                    writer.println("\t" + spellDAO.getSpellName("45"));
                    writer.println("\t" + spellDAO.getSpellName("46"));
                }
                break;
            case "sorcerer":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("47"));
                    writer.println("\t" + spellDAO.getSpellName("48"));
                    writer.println("\t" + spellDAO.getSpellName("49"));
                    writer.println("\t" + spellDAO.getSpellName("50"));
                    writer.println("\t" + spellDAO.getSpellName("52"));
                    writer.println("\t" + spellDAO.getSpellName("53"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("47"));
                    writer.println("\t" + spellDAO.getSpellName("48"));
                    writer.println("\t" + spellDAO.getSpellName("49"));
                    writer.println("\t" + spellDAO.getSpellName("50"));
                    writer.println("\t" + spellDAO.getSpellName("52"));
                    writer.println("\t" + spellDAO.getSpellName("53"));
                    writer.println("\t" + spellDAO.getSpellName("54"));

                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("47"));
                    writer.println("\t" + spellDAO.getSpellName("48"));
                    writer.println("\t" + spellDAO.getSpellName("49"));
                    writer.println("\t" + spellDAO.getSpellName("50"));
                    writer.println("\t" + spellDAO.getSpellName("52"));
                    writer.println("\t" + spellDAO.getSpellName("53"));
                    writer.println("\t" + spellDAO.getSpellName("54"));
                    writer.println("\t" + spellDAO.getSpellName("55"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("47"));
                    writer.println("\t" + spellDAO.getSpellName("48"));
                    writer.println("\t" + spellDAO.getSpellName("49"));
                    writer.println("\t" + spellDAO.getSpellName("50"));
                    writer.println("\t" + spellDAO.getSpellName("52"));
                    writer.println("\t" + spellDAO.getSpellName("53"));
                    writer.println("\t" + spellDAO.getSpellName("54"));
                    writer.println("\t" + spellDAO.getSpellName("55"));
                    writer.println("\t" + spellDAO.getSpellName("56"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("47"));
                    writer.println("\t" + spellDAO.getSpellName("48"));
                    writer.println("\t" + spellDAO.getSpellName("49"));
                    writer.println("\t" + spellDAO.getSpellName("50"));
                    writer.println("\t" + spellDAO.getSpellName("52"));
                    writer.println("\t" + spellDAO.getSpellName("53"));
                    writer.println("\t" + spellDAO.getSpellName("54"));
                    writer.println("\t" + spellDAO.getSpellName("55"));
                    writer.println("\t" + spellDAO.getSpellName("56"));
                    writer.println("\t" + spellDAO.getSpellName("58"));
                }
                break;
            case "warlock":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("59"));
                    writer.println("\t" + spellDAO.getSpellName("60"));
                    writer.println("\t" + spellDAO.getSpellName("62"));
                    writer.println("\t" + spellDAO.getSpellName("63"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("59"));
                    writer.println("\t" + spellDAO.getSpellName("60"));
                    writer.println("\t" + spellDAO.getSpellName("62"));
                    writer.println("\t" + spellDAO.getSpellName("63"));
                    writer.println("\t" + spellDAO.getSpellName("64"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("59"));
                    writer.println("\t" + spellDAO.getSpellName("60"));
                    writer.println("\t" + spellDAO.getSpellName("62"));
                    writer.println("\t" + spellDAO.getSpellName("63"));
                    writer.println("\t" + spellDAO.getSpellName("64"));
                    writer.println("\t" + spellDAO.getSpellName("65"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("59"));
                    writer.println("\t" + spellDAO.getSpellName("60"));
                    writer.println("\t" + spellDAO.getSpellName("61"));
                    writer.println("\t" + spellDAO.getSpellName("62"));
                    writer.println("\t" + spellDAO.getSpellName("63"));
                    writer.println("\t" + spellDAO.getSpellName("64"));
                    writer.println("\t" + spellDAO.getSpellName("65"));
                    writer.println("\t" + spellDAO.getSpellName("66"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("59"));
                    writer.println("\t" + spellDAO.getSpellName("60"));
                    writer.println("\t" + spellDAO.getSpellName("61"));
                    writer.println("\t" + spellDAO.getSpellName("62"));
                    writer.println("\t" + spellDAO.getSpellName("63"));
                    writer.println("\t" + spellDAO.getSpellName("64"));
                    writer.println("\t" + spellDAO.getSpellName("65"));
                    writer.println("\t" + spellDAO.getSpellName("66"));
                    writer.println("\t" + spellDAO.getSpellName("67"));
                }
                break;
            case "wizard":
                if (character.getLvl().equals(1)) {
                    writer.println("\t" + spellDAO.getSpellName("69"));
                    writer.println("\t" + spellDAO.getSpellName("70"));
                    writer.println("\t" + spellDAO.getSpellName("71"));
                    writer.println("\t" + spellDAO.getSpellName("73"));
                    writer.println("\t" + spellDAO.getSpellName("74"));
                }
                else if(character.getLvl().equals(2))
                {
                    writer.println("\t" + spellDAO.getSpellName("69"));
                    writer.println("\t" + spellDAO.getSpellName("70"));
                    writer.println("\t" + spellDAO.getSpellName("71"));
                    writer.println("\t" + spellDAO.getSpellName("73"));
                    writer.println("\t" + spellDAO.getSpellName("74"));
                    writer.println("\t" + spellDAO.getSpellName("75"));
                }
                else if(character.getLvl().equals(3))
                {
                    writer.println("\t" + spellDAO.getSpellName("69"));
                    writer.println("\t" + spellDAO.getSpellName("70"));
                    writer.println("\t" + spellDAO.getSpellName("71"));
                    writer.println("\t" + spellDAO.getSpellName("73"));
                    writer.println("\t" + spellDAO.getSpellName("74"));
                    writer.println("\t" + spellDAO.getSpellName("75"));
                    writer.println("\t" + spellDAO.getSpellName("76"));
                    writer.println("\t" + spellDAO.getSpellName("77"));
                    writer.println("\t" + spellDAO.getSpellName("78"));
                }
                else if(character.getLvl().equals(4))
                {
                    writer.println("\t" + spellDAO.getSpellName("69"));
                    writer.println("\t" + spellDAO.getSpellName("70"));
                    writer.println("\t" + spellDAO.getSpellName("71"));
                    writer.println("\t" + spellDAO.getSpellName("72"));
                    writer.println("\t" + spellDAO.getSpellName("73"));
                    writer.println("\t" + spellDAO.getSpellName("74"));
                    writer.println("\t" + spellDAO.getSpellName("75"));
                    writer.println("\t" + spellDAO.getSpellName("76"));
                    writer.println("\t" + spellDAO.getSpellName("77"));
                    writer.println("\t" + spellDAO.getSpellName("78"));
                    writer.println("\t" + spellDAO.getSpellName("79"));
                }
                else
                {
                    writer.println("\t" + spellDAO.getSpellName("69"));
                    writer.println("\t" + spellDAO.getSpellName("70"));
                    writer.println("\t" + spellDAO.getSpellName("71"));
                    writer.println("\t" + spellDAO.getSpellName("72"));
                    writer.println("\t" + spellDAO.getSpellName("73"));
                    writer.println("\t" + spellDAO.getSpellName("74"));
                    writer.println("\t" + spellDAO.getSpellName("75"));
                    writer.println("\t" + spellDAO.getSpellName("76"));
                    writer.println("\t" + spellDAO.getSpellName("77"));
                    writer.println("\t" + spellDAO.getSpellName("78"));
                    writer.println("\t" + spellDAO.getSpellName("79"));
                    writer.println("\t" + spellDAO.getSpellName("80"));
                    writer.println("\t" + spellDAO.getSpellName("81"));
                }
                break;
            default:
                writer.println("N/A");
                break;
        }

        writer.close();
    }

    public void exportToFile(String id){ };
    public int getID()
    {
        return id;
    }


}
