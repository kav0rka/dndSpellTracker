package kavorka.dndspelltracker;

public class FullCaster extends CharacterClass {
    FullCaster(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        super.setSpells(character);

        int charLvl = character.getLevel();
        if (charLvl == 1) setLvl1SpellMax(2);
        else if (charLvl == 2) setLvl1SpellMax(3);
        else if (charLvl == 3) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(2);
        } else if (charLvl == 4) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
        } else if (charLvl == 5) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(2);
        } else if (charLvl == 6) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
        } else if (charLvl == 7) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(1);
        } else if (charLvl == 8) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(2);
        } else if (charLvl == 9) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(1);
        } else if (charLvl == 10) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
        } else if (charLvl == 11 || charLvl == 12) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
            setLvl6SpellMax(1);
        } else if (charLvl == 13 || charLvl == 14) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
            setLvl6SpellMax(1);
            setLvl7SpellMax(1);
        } else if (charLvl == 15 || charLvl == 16) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
            setLvl6SpellMax(1);
            setLvl7SpellMax(1);
            setLvl8SpellMax(1);
        } else if (charLvl == 17) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(2);
            setLvl6SpellMax(1);
            setLvl7SpellMax(1);
            setLvl8SpellMax(1);
            setLvl9SpellMax(1);
        } else if (charLvl == 18) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(3);
            setLvl6SpellMax(1);
            setLvl7SpellMax(1);
            setLvl8SpellMax(1);
            setLvl9SpellMax(1);
        } else if (charLvl == 19) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(3);
            setLvl6SpellMax(2);
            setLvl7SpellMax(1);
            setLvl8SpellMax(1);
            setLvl9SpellMax(1);
        } else if (charLvl == 20) {
            setLvl1SpellMax(4);
            setLvl2SpellMax(3);
            setLvl3SpellMax(3);
            setLvl4SpellMax(3);
            setLvl5SpellMax(3);
            setLvl6SpellMax(2);
            setLvl7SpellMax(2);
            setLvl8SpellMax(1);
            setLvl9SpellMax(1);
        }
    }
}
