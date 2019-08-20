package kavorka.dndspelltracker;


public class CharacterClass {

    private int mLvl1SpellMax;
    private int mLvl2SpellMax;
    private int mLvl3SpellMax;
    private int mLvl4SpellMax;
    private int mLvl5SpellMax;
    private int mLvl6SpellMax;
    private int mLvl7SpellMax;
    private int mLvl8SpellMax;
    private int mLvl9SpellMax;


    CharacterClass(Character character) {
        setSpells(character);
    }

    public void setSpells(Character character) {
    // Override this is each class
    }

    public int[] getSpellSlots() {
        int[] slots = new int[9];
        slots[0] = mLvl1SpellMax;
        slots[1] = mLvl2SpellMax;
        slots[2] = mLvl3SpellMax;
        slots[3] = mLvl4SpellMax;
        slots[4] = mLvl5SpellMax;
        slots[5] = mLvl6SpellMax;
        slots[6] = mLvl7SpellMax;
        slots[7] = mLvl8SpellMax;
        slots[8] = mLvl9SpellMax;
        return slots;
    }

    public int getLvl1SpellMax() {
        return mLvl1SpellMax;
    }

    public void setLvl1SpellMax(int mLvl1SpellMax) {
        this.mLvl1SpellMax = mLvl1SpellMax;
    }

    public int getLvl2SpellMax() {
        return mLvl2SpellMax;
    }

    public void setLvl2SpellMax(int mLvl2SpellMax) {
        this.mLvl2SpellMax = mLvl2SpellMax;
    }

    public int getLvl3SpellMax() {
        return mLvl3SpellMax;
    }

    public void setLvl3SpellMax(int lvl3SpellMax) {
        mLvl3SpellMax = lvl3SpellMax;
    }

    public int getLvl4SpellMax() {
        return mLvl4SpellMax;
    }

    public void setLvl4SpellMax(int lvl4SpellMax) {
        mLvl4SpellMax = lvl4SpellMax;
    }

    public int getLvl5SpellMax() {
        return mLvl5SpellMax;
    }

    public void setLvl5SpellMax(int lvl5SpellMax) {
        mLvl5SpellMax = lvl5SpellMax;
    }

    public int getLvl6SpellMax() {
        return mLvl6SpellMax;
    }

    public void setLvl6SpellMax(int lvl6SpellMax) {
        mLvl6SpellMax = lvl6SpellMax;
    }

    public int getLvl7SpellMax() {
        return mLvl7SpellMax;
    }

    public void setLvl7SpellMax(int lvl7SpellMax) {
        mLvl7SpellMax = lvl7SpellMax;
    }

    public int getLvl8SpellMax() {
        return mLvl8SpellMax;
    }

    public void setLvl8SpellMax(int lvl8SpellMax) {
        mLvl8SpellMax = lvl8SpellMax;
    }

    public int getLvl9SpellMax() {
        return mLvl9SpellMax;
    }

    public void setLvl9SpellMax(int lvl9SpellMax) {
        mLvl9SpellMax = lvl9SpellMax;
    }
}
