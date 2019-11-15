package kavorka.dndspelltracker

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.data.Spells
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

        // Class spinner
        val classNames = arrayOf(bard, barbarian, cleric, druid, fighter, monk, paladin, ranger, rogue, sorcerer, warlock, wizard)
        val classSpinner = findViewById<Spinner>(R.id.classSpinner)
        val classArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classNames)
        classSpinner.adapter = classArrayAdapter

        // Sub class spinner
        val subClassNames = getSubClasses(classSpinner.selectedItem.toString(), 1)
        val subClassSpinner = findViewById<Spinner>(R.id.subClassSpinner)
        val subClassAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subClassNames)
        subClassSpinner.adapter = subClassAdapter

        // Update sub class when class is selected
        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                subClassAdapter.clear()
                subClassAdapter.addAll(getSubClasses(classSpinner.selectedItem.toString(), levelSpinner.selectedItemPosition +1))
                subClassAdapter.notifyDataSetChanged()

                viewModel.characterClass = classSpinner.selectedItem.toString()
                abilityAdapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
        }

        // Race spinner
        val races = arrayOf(dwarf, elf, halfling, human, dragonborn, gnome, halfelf, halforc, tiefling)
        val raceSpinner = findViewById<Spinner>(R.id.raceSpinner)
        val raceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, races)
        raceSpinner.adapter = raceAdapter

        // Update abilities when race is selected

        // Level spinner
        val levels = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        val levelSpinner = findViewById<Spinner>(R.id.levelSpinner)
        val levelArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levels)
        levelSpinner.adapter = levelArrayAdapter

        // Update sub class when level is selected
        levelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                subClassAdapter.clear()
                subClassAdapter.addAll(getSubClasses(classSpinner.selectedItem.toString(), level=pos +1))
                subClassAdapter.notifyDataSetChanged()
                viewModel.level = levelSpinner.selectedItem.toString().toInt()
                abilityAdapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
        }

        initRecyclerViews()

        // Set stats if editing a character
        if (name != "") {
            thread {
                val playerCharacter = db.charactersDao().getCharacterByName(name)
                oldName = name

                val nameField = findViewById<EditText>(R.id.nameEditText)
                val strength = findViewById<EditText>(R.id.strEditText)
                val dexterity = findViewById<EditText>(R.id.dexEditText)
                val constitution = findViewById<EditText>(R.id.conEditText)
                val intelligence = findViewById<EditText>(R.id.intEditText)
                val wisdom = findViewById<EditText>(R.id.wisEditText)
                val charisma = findViewById<EditText>(R.id.chaEditText)
                val hp = findViewById<EditText>(R.id.maxHPEditText)

                nameField.setText(name)
                // Set level
                levelSpinner.setSelection(playerCharacter.level -1)
                // Set Class
                val classPos = classArrayAdapter.getPosition(playerCharacter.characterClass)
                classSpinner.setSelection(classPos)
                // Set sub class
                subClassAdapter.clear()
                val allSubClasses = getSubClasses(playerCharacter.characterClass, level=playerCharacter.level)
                subClassAdapter.addAll(allSubClasses)
                subClassAdapter.notifyDataSetChanged()
                val subClassPos = allSubClasses.indexOf(playerCharacter.characterSubClass)
                subClassSpinner.setSelection(subClassPos)
                viewModel.characterClass = playerCharacter.characterClass
                // Set race
                val racePos = raceAdapter.getPosition(playerCharacter.race)
                raceSpinner.setSelection(racePos)
                viewModel.race = playerCharacter.race

                // Set Stats
                strength.setText(playerCharacter.strength.toString())
                dexterity.setText(playerCharacter.dexterity.toString())
                constitution.setText(playerCharacter.constitution.toString())
                intelligence.setText(playerCharacter.intelligence.toString())
                wisdom.setText(playerCharacter.wisdom.toString())
                charisma.setText(playerCharacter.charisma.toString())
                hp.setText(playerCharacter.maxHP.toString())

                // Add abilities
                db.abilityDao().getAbilitiesByCharacter(name).forEach {
                    Log.d("", playerCharacter.characterClass)
                    Log.d("", it.type)
                    if (it.type == feat) abilityAdapter.abilitiesList.add(it)
                    if (playerCharacter.characterClass == warlock) {
                        if (it.type == invocation) abilityAdapter.abilitiesList.add(it)
                    }
                }
            }
        }


        // Save button
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            // Name
            val name = findViewById<EditText>(R.id.nameEditText).text.toString()

            // Stats
            val strength = findViewById<EditText>(R.id.strEditText).text.toString().toInt()
            val dexterity = findViewById<EditText>(R.id.dexEditText).text.toString().toInt()
            val constitution = findViewById<EditText>(R.id.conEditText).text.toString().toInt()
            val intelligence = findViewById<EditText>(R.id.intEditText).text.toString().toInt()
            val wisdom = findViewById<EditText>(R.id.wisEditText).text.toString().toInt()
            val charisma = findViewById<EditText>(R.id.chaEditText).text.toString().toInt()
            val level = levelSpinner.selectedItem.toString().toInt()
            val maxHP = findViewById<EditText>(R.id.maxHPEditText).text.toString().toInt()

            // Class
            val playerClass = classSpinner.selectedItem.toString()
            var playerSubClass = ""
            if (subClassSpinner.selectedItem != null) {
                playerSubClass = subClassSpinner.selectedItem.toString()
            }

            // Race
            val race = raceSpinner.selectedItem.toString()

            // Make the new character
            val newCharacter = PlayerCharacter(name, playerClass, playerSubClass, race, level, strength, dexterity,
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
                        val spell = Spells(name, index + 1, i, 0)
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
        val playerCharacter = PlayerCharacter("", characterClass, "", "", level,10, 10, 10, 10, 10, 10)
        return getClass(characterClass, playerCharacter).subClasses
    }

}
