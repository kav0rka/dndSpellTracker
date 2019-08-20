package kavorka.dndspelltracker;

public class Character {

    private int mHitPoints;
    private int mLevel;
    private String mName;
    private CharacterClass mClass;

    // Stats
    private int mStr;
    private int mDex;
    private int mCon;
    private int mInt;
    private int mWis;
    private int mCha;

    Character() {
        // TEMP FOR NOW
        mName = "Delg";
        mHitPoints = 27;
        mLevel = 3;
        mStr = 14;
        mDex = 8;
        mCon = 14;
        mInt = 10;
        mWis = 16;
        mCha = 12;

        mClass = new Cleric(this);
    }

    // Getters and setters
    public int getHitPoints() {
        return mHitPoints;
    }

    public void setHitPoints(int hitPoints) {
        mHitPoints = hitPoints;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getStr() {
        return mStr;
    }

    public void setStr(int str) {
        mStr = str;
    }

    public int getDex() {
        return mDex;
    }

    public void setDex(int dex) {
        mDex = dex;
    }

    public int getCon() {
        return mCon;
    }

    public void setCon(int con) {
        mCon = con;
    }

    public int getInt() {
        return mInt;
    }

    public void setInt(int anInt) {
        mInt = anInt;
    }

    public int getWis() {
        return mWis;
    }

    public void setWis(int wis) {
        mWis = wis;
    }

    public int getCha() {
        return mCha;
    }

    public void setCha(int cha) {
        mCha = cha;
    }
}