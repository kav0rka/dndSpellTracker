package kavorka.dndspelltracker;

public class Sorcerer extends CharacterClass {
    Sorcerer(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsFull(character);
    }
}
