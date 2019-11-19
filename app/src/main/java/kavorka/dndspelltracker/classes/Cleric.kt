package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val knowledge = "Knowledge"
const val life = "Life"
const val light = "Light"
const val nature = "Nature"
const val tempest = "Tempest"
const val trickery = "Trickery"
const val war = "War"

class Cleric(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsFull(level)

        subClasses.add(knowledge)
        subClasses.add(life)
        subClasses.add(light)
        subClasses.add(nature)
        subClasses.add(tempest)
        subClasses.add(trickery)
        subClasses.add(war)

        // Channel Divinity
        if (level >= 2) {
            var channelMax = 1
            if (level >= 6) channelMax++
            if (level >= 18) channelMax++
            abilities.add(Ability("", "Channel Divinity", channelMax, resetOnShort = true))
        }

        // Divine Intervention
        if (level >= 10) {
            abilities.add(Ability("", "Divine Intervention", 1))
        }

        // Sub class abilities
        val sub = playerCharacter.characterSubClass

        if (sub == knowledge) {
            if (level >= 17) {
                abilities.add(Ability("", "Visions of the Past", 1, resetOnShort = true))
            }
        } else if (sub == light) {
            val wardingMax = getAbilityMod(playerCharacter.wisdom)
            abilities.add(Ability("", "Warding Flame", wardingMax))
        } else if (sub == tempest) {
            val wrathMax = getAbilityMod(playerCharacter.wisdom)
            abilities.add(Ability("", "Wrath of the Storm", wrathMax))
        } else if (sub == war) {
            val priestMax = getAbilityMod(playerCharacter.wisdom)
            abilities.add(Ability("", "War Priest", priestMax))
        }

    }
}
