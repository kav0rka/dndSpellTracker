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
const val forge = "Forge"
const val grave = "Grave"

class Cleric(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level = playerCharacter.level
        setSpellsFull(level)

        subClasses.add(knowledge)
        subClasses.add(life)
        subClasses.add(light)
        subClasses.add(nature)
        subClasses.add(tempest)
        subClasses.add(trickery)
        subClasses.add(war)
        subClasses.add(forge)
        subClasses.add(grave)

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
        val wisdomMod = getAbilityMod(playerCharacter.wisdom)

        if (sub == knowledge) {
            if (level >= 17) {
                abilities.add(Ability("", "Visions of the Past", 1, resetOnShort = true))
            }
        } else if (sub == light) {
            abilities.add(Ability("", "Warding Flame", wisdomMod))
        } else if (sub == tempest) {
            abilities.add(Ability("", "Wrath of the Storm", wisdomMod))
        } else if (sub == war) {
            abilities.add(Ability("", "War Priest", wisdomMod))
        } else if (sub == forge) {
            abilities.add(Ability("", "Blessing of the Forge", 1))
        } else if (sub == grave) {
            abilities.add(Ability("", "Eyes of the Grave", wisdomMod))
            if (level >= 6) abilities.add(Ability("", "Sentinel at Death's Door", wisdomMod))
        }
    }
}
