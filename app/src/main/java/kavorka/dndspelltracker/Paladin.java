package kavorka.dndspelltracker;

public class Paladin extends CharacterClass {
    Paladin(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsSemi(character);
    }
}
