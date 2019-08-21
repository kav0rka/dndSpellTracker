package kavorka.dndspelltracker;

public class Warlock extends CharacterClass {
    Warlock(Character character) {
        super(character);
    }


    @Override
    public void setSpells(Character character) {
        super.setSpells(character);

        int charLvl = character.getLevel();

        if (charLvl == 1) setLvl1SpellMax(1);
        else if (charLvl == 2) setLvl1SpellMax(2);
        else if (charLvl == 3 || charLvl == 4) {
            setLvl2SpellMax(2);
        } else if (charLvl == 5 || charLvl == 6) {
            setLvl3SpellMax(2);
        } else if (charLvl == 7 || charLvl == 8) {
            setLvl4SpellMax(2);
        } else if (charLvl == 9 || charLvl == 10) {
            setLvl5SpellMax(2);
        } else if (charLvl >= 11 && charLvl <= 16) {
            setLvl5SpellMax(3);
        } else if (charLvl >= 17) {
            setLvl5SpellMax(4);
        }
    }
}
