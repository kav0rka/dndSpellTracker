package kavorka.dndspelltracker

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.classes.*
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.data.Spells
import kavorka.dndspelltracker.races.*
import kotlinx.android.synthetic.main.activity_new_character.*
import kotlin.concurrent.thread


class NewCharacterActivity : AppCompatActivity() {
    private var name = ""
    private var oldName = ""
    lateinit var abilityRecyclerView: RecyclerView
    lateinit var abilityAdapter: NewAbilityAdapter
    lateinit var viewModel: NewCharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        name = intent.getStringExtra("Name")
        viewModel = ViewModelProviders.of(this).get(NewCharacterViewModel::class.java)
        autoHPSwitch.isChecked = false

        // Automatically set HP is auto HP is selected
        fun autoUpdateHP(level: Int) {
            if (autoHPSwitch.isChecked) {
                val con = conEditText.text.toString().toInt()
                val playerCharacter = PlayerCharacter(level = level, constitution = con)
                val characterClass = classSpinner.selectedItem.toString()
                maxHPEditText.setText(getClass(characterClass, playerCharacter).getMaxHP().toString())
            }
        }


        // Class spinner
        val classNames = arrayOf(bard, barbarian, cleric, druid, fighter, monk, paladin, ranger, rogue, sorcerer, warlock, wizard)
        val classArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classNames)
        classSpinner.adapter = classArrayAdapter

        // Sub class spinner
        val subClassNames = getSubClasses(classSpinner.selectedItem.toString(), 1)
        val subClassAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subClassNames)
        subClassSpinner.adapter = subClassAdapter

        // Update sub classes when class is selected
        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                subClassAdapter.clear()
                subClassAdapter.addAll(getSubClasses(classSpinner.selectedItem.toString(), levelSpinner.selectedItemPosition + 1))
                subClassAdapter.notifyDataSetChanged()

                viewModel.characterClass = classSpinner.selectedItem.toString()
                abilityAdapter.notifyDataSetChanged()
                autoUpdateHP(levelSpinner.selectedItem.toString().toInt())
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
        }

        // Race spinner
        val races = arrayOf(dwarf, elf, halfling, human, dragonborn, gnome, halfelf, halforc, tiefling)
        val raceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, races)
        raceSpinner.adapter = raceAdapter

        // Sub race spinner
        val subRaceNames = getSubRaces(raceSpinner.selectedItem.toString())
        val subRaceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subRaceNames)
        subRaceSpinner.adapter = subRaceAdapter

        // Update sub races when race is selected
        raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                subRaceAdapter.clear()
                subRaceAdapter.addAll(getSubRaces(raceSpinner.selectedItem.toString()))
                subRaceAdapter.notifyDataSetChanged()

                viewModel.race = raceSpinner.selectedItem.toString()
                abilityAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
        }

        // Level spinner
        val levels = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        val levelArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levels)
        levelSpinner.adapter = levelArrayAdapter

        // Update sub class  and HP when level is selected
        levelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val level = pos + 1
                subClassAdapter.clear()
                subClassAdapter.addAll(getSubClasses(classSpinner.selectedItem.toString(), level = level))
                subClassAdapter.notifyDataSetChanged()
                viewModel.level = level
                abilityAdapter.notifyDataSetChanged()
                autoUpdateHP(level)
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
        }

        // Update HP when "Auto HP" is switched on
        autoHPSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) autoUpdateHP(levelSpinner.selectedItem.toString().toInt())
        }
        
        // Update HP when constitution is changed
        conEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.toString() != "") {
                    autoUpdateHP(levelSpinner.selectedItem.toString().toInt())
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        initRecyclerViews()

        // Set stats if editing a character
        if (name != "") {
            thread {
                val playerCharacter = db.charactersDao().getCharacterByName(name)
                oldName = name

                nameEditText.setText(name)
                // Set level
                levelSpinner.setSelection(playerCharacter.level - 1)
                // Set Class
                val classPos = classArrayAdapter.getPosition(playerCharacter.characterClass)
                classSpinner.setSelection(classPos)
                // Set sub class
                subClassAdapter.clear()
                val allSubClasses = getSubClasses(playerCharacter.characterClass, level = playerCharacter.level)
                subClassAdapter.addAll(allSubClasses)
                subClassAdapter.notifyDataSetChanged()
                val subClassPos = allSubClasses.indexOf(playerCharacter.characterSubClass)
                subClassSpinner.setSelection(subClassPos)
                viewModel.characterClass = playerCharacter.characterClass
                // Set race
                val racePos = raceAdapter.getPosition(playerCharacter.race)
                raceSpinner.setSelection(racePos)
                viewModel.race = playerCharacter.race
                // Set sub race
                subRaceAdapter.clear()
                val allSubRaces = getSubRaces(playerCharacter.race)
                subRaceAdapter.addAll(allSubRaces)
                subRaceAdapter.notifyDataSetChanged()
                val subRacePos = allSubRaces.indexOf(playerCharacter.subRace)
                subRaceSpinner.setSelection(subRacePos)

                // Set Stats
                strEditText.setText(playerCharacter.strength.toString())
                dexEditText.setText(playerCharacter.dexterity.toString())
                conEditText.setText(playerCharacter.constitution.toString())
                intEditText.setText(playerCharacter.intelligence.toString())
                wisEditText.setText(playerCharacter.wisdom.toString())
                chaEditText.setText(playerCharacter.charisma.toString())
                maxHPEditText.setText(playerCharacter.maxHP.toString())

                // Add abilities
                db.abilityDao().getAbilitiesByCharacter(name).forEach {
                    if (it.type == feat) abilityAdapter.abilitiesList.add(it)
                    if (playerCharacter.characterClass == warlock) {
                        if (it.type == invocation) abilityAdapter.abilitiesList.add(it)
                    }
                }
            }
        }


        // Save button
        saveButton.setOnClickListener {
            // Name
            val name = nameEditText.text.toString()

            // Stats
            val strength = strEditText.text.toString().toInt()
            val dexterity = dexEditText.text.toString().toInt()
            val constitution = conEditText.text.toString().toInt()
            val intelligence = intEditText.text.toString().toInt()
            val wisdom = wisEditText.text.toString().toInt()
            val charisma = chaEditText.text.toString().toInt()
            val level = levelSpinner.selectedItem.toString().toInt()
            val maxHP = maxHPEditText.text.toString().toInt()

            // Class
            val playerClass = classSpinner.selectedItem.toString()
            var playerSubClass = ""
            if (subClassSpinner.selectedItem != null) {
                playerSubClass = subClassSpinner.selectedItem.toString()
            }

            // Race
            val race = raceSpinner.selectedItem.toString()
            var subRace = ""
            if (subRaceSpinner.selectedItem != null) {
                subRace = subRaceSpinner.selectedItem.toString()
            }

            // Make the new character
            val newCharacter = PlayerCharacter(name, playerClass, playerSubClass, race, subRace, level, strength, dexterity,
                    constitution, intelligence, wisdom, charisma, maxHP, maxHP)

            // Save
            thread {
                db.charactersDao().insert(newCharacter)

                // Delete old character if name has changed
                if (name != oldName) {
                    db.charactersDao().deleteCharacterByName(oldName)
                    db.abilityDao().deleteAbilitiesByCharacter(oldName)
                    db.spellsDao().deleteSpellsByCharacter(oldName)
                }

                // Insert spells
                val characterClass = getClass(playerClass, newCharacter)
                characterClass.getSpellSlots().forEachIndexed { index, i ->
                    if (i > 0) {
                        val level = index + 1
                        var resetOnShort = false
                        if (playerClass == warlock) {
                            if (level <= 5) resetOnShort = true
                        }
                        val spell = Spells(name, level, i, 0, resetOnShort = resetOnShort)
                        db.spellsDao().insert(spell)
                    } else {
                        db.spellsDao().deleteSpellsByCharacterAndLevel(name, index + 1)
                    }
                }

                // Insert abilities
                db.abilityDao().deleteAbilitiesByCharacter(name)
                characterClass.abilities.forEach {
                    it.character = name
                    db.abilityDao().insert(it)
                }
                abilityAdapter.abilitiesList.forEach {
                    it.character = name
                    db.abilityDao().insert(it)
                }
                getRace(race, newCharacter).raceAbilities.forEach {
                    it.character = name
                    db.abilityDao().insert(it)
                }

                // Go back to main activity
                val myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
        }
    }

    private fun initRecyclerViews() {
        abilityRecyclerView = findViewById(R.id.abilitiesRecyclerView)
        abilityAdapter = NewAbilityAdapter(viewModel)
        abilityRecyclerView.adapter = abilityAdapter
        abilityRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
        abilityRecyclerView.addItemDecoration(itemDecor)
    }


    private fun getClass(characterClass: String, playerCharacter: PlayerCharacter): CharacterClass {
        return when (characterClass) {
            bard -> Bard(playerCharacter)
            barbarian -> Barbarian(playerCharacter)
            cleric -> Cleric(playerCharacter)
            druid -> Druid(playerCharacter)
            fighter -> Fighter(playerCharacter)
            monk -> Monk(playerCharacter)
            paladin -> Paladin(playerCharacter)
            ranger -> Ranger(playerCharacter)
            rogue -> Rogue(playerCharacter)
            sorcerer -> Sorcerer(playerCharacter)
            warlock -> Warlock(playerCharacter)
            else -> Wizard(playerCharacter)
        }
    }

    private fun getSubClasses(characterClass: String, level: Int): List<String> {
        val playerCharacter = PlayerCharacter(level = level)
        return getClass(characterClass, playerCharacter).subClasses
    }

    private fun getRace(raceName: String, playerCharacter: PlayerCharacter): Race {
        return when (raceName) {
            dragonborn -> Dragonborn(playerCharacter)
            dwarf -> Dwarf(playerCharacter)
            elf -> Elf(playerCharacter)
            gnome -> Gnome(playerCharacter)
            halfelf -> HalfElf(playerCharacter)
            halfling -> Halfling(playerCharacter)
            halforc -> HalfOrc(playerCharacter)
            human -> Human(playerCharacter)
            else -> Tiefling(playerCharacter)
        }
    }

    private fun getSubRaces(raceName: String): List<String> {
        val playerCharacter = PlayerCharacter(race = raceName)
        return getRace(raceName, playerCharacter).subRaces
    }

}
