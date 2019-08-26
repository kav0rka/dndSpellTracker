package kavorka.dndspelltracker;

public class Druid extends CharacterClass {
    Druid(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsFull(character);
    }
}
