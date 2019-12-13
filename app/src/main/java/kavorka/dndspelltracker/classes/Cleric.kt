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

class Cleric(playerCharacter: PlayerCharacter, subClass: String="", level: Int=1) : ClassMain(playerCharacter, subClass, level) {

    init {
        hitDie = 8
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
            abilities.add(Ability("Channel Divinity", max=channelMax, resetOnShort = true))
        }

        // Divine Intervention
        if (level >= 10) {
            abilities.add(Ability("Divine Intervention"))
        }

        // Sub class abilities
        val wisdomMod = getAbilityMod(playerCharacter.wisdom)

        if (subClass == knowledge) {
            if (level >= 17) {
                abilities.add(Ability("Visions of the Past", resetOnShort = true))
            }
        } else if (subClass == light) {
            abilities.add(Ability("Warding Flame", max=wisdomMod))
        } else if (subClass == tempest) {
            abilities.add(Ability("Wrath of the Storm", max=wisdomMod))
        } else if (subClass == war) {
            abilities.add(Ability("War Priest", max=wisdomMod))
        } else if (subClass == forge) {
            abilities.add(Ability("Blessing of the Forge"))
        } else if (subClass == grave) {
            abilities.add(Ability("Eyes of the Grave", max=wisdomMod))
            if (level >= 6) abilities.add(Ability("Sentinel at Death's Door", max=wisdomMod))
        }
    }
}
