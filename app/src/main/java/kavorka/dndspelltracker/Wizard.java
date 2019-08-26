package kavorka.dndspelltracker;

public class Wizard extends CharacterClass {
    Wizard(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsFull(character);
    }

}
