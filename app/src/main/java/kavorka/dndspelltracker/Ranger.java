package kavorka.dndspelltracker;

public class Ranger extends CharacterClass {
    Ranger(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsSemi(character);
    }
}
