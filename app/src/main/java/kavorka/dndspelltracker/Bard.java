package kavorka.dndspelltracker;

public class Bard extends CharacterClass {
    Bard(Character character) {
        super(character);
    }

    @Override
    public void setSpells(Character character) {
        setSpellsFull(character);
    }
}
