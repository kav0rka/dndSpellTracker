package kavorka.dndspelltracker;

public class SemiCaster extends CharacterClass {
    SemiCaster(Character character) {
        super(character);
    }


    @Override
    public void setSpells(Character character) {
        super.setSpells(character);

        int charLvl = character.getLevel();
        if (charLvl == 2) setLvl1SpellMax(2);
        else if (charLvl == 3 || charLvl == 4) {
            setLvl1SpellMax(3);
        } else if (charLvl == 5 || charLvl == 6) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(2);
        } else if (charLvl == 7 || charLvl == 8) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
        } else if (charLvl == 9 || charLvl == 10) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(2);
        } else if (charLvl == 11 || charLvl == 12) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
        } else if (charLvl == 13 || charLvl == 14) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(1);
        } else if (charLvl == 15 || charLvl == 16) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(2);
        } else if (charLvl == 17 || charLvl == 18) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
        } else if (charLvl == 19 || charLvl == 20) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
        }
    }
}
