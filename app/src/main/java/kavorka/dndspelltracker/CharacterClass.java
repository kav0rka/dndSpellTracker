package kavorka.dndspelltracker;


public class CharacterClass {

    private int mLvl1SpellMax;
    private int mLvl2SpellMax;


    CharacterClass(Character character) {
        setSpells(character);
    }

    public void setSpells(Character character) {
    // Override this is each class
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
}
