/**
 * Created by Draven on 12/11/2015.
 */
public class Character {
    private String cName;
    private String pName;
    private String race;
    private String cClass;
    private int lvl = 1;
    private Integer str;
    private Integer con;
    private Integer dex;
    private Integer wis;
    private Integer cha;
    private Integer inte;
    private Integer classID;
    private Integer raceID;
    private Integer inventoryID;
    private Integer spellBookID;
    private Integer abilityScoreID;

    public Character() {
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    public void setCon(Integer con) {
        this.con = con;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public void setWis(Integer wis) {
        this.wis = wis;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public void setInte(Integer inte) {
        this.inte = inte;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public void setRaceID(Integer raceID) {
        this.raceID = raceID;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public void setInventoryID(Integer inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setSpellBookID(Integer spellBookID) {
        this.spellBookID = spellBookID;
    }

    public void setAbilityScoreID(Integer abilityScoreID) {
        this.abilityScoreID = abilityScoreID;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setcClass(String cClass) {
        this.cClass = cClass;
    }

    public String getcClass() {
        return cClass;
    }

    public String getRace() {
        return race;
    }

    public String getCName() {
        return cName;
    }

    public String getPName()
    {
        return pName;
    }

    public int getStr()
    {
        return str;
    }

    public int getCon()
    {
        return con;
    }

    public int getDex()
    {
        return dex;
    }

    public int getWis()
    {
        return wis;
    }

    public int getInte()
    {
        return inte;
    }

    public int getCha() {
        return cha;
    }

    public int getClassID()
    {
        return classID;
    }

    public int getRaceID()
    {
        return raceID;
    }

    public int getInvetoryID()
    {
        return inventoryID;
    }

    public int getSpellBookID()
    {
        return spellBookID;
    }

    public int getAbilityScoreID()
    {
        return abilityScoreID;
    }

    public int getLvl() {
        return lvl;
    }
}
