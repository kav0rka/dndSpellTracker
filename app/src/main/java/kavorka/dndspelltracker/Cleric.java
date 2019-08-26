package kavorka.dndspelltracker;

public class Cleric extends CharacterClass {
    Cleric(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsFull(character);
    }
}
