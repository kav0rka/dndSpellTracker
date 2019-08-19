package kavorka.dndspelltracker;

public class Cleric extends CharacterClass {
    Cleric(Character character) {
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
        }
    }
}
