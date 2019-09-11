package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability

class Wizard internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
        val portent = Ability("", "Portent", 2, 0)
        abilities.add(portent)
    }

}
