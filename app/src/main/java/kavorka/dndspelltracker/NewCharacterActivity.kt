package kavorka.dndspelltracker

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread


class NewCharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        // Class spinner
        val classNames = arrayOf(bard, barbarian, cleric, druid, fighter, monk, paladin, ranger, rogue, sorcerer, warlock, wizard)
        val classSpinner = findViewById<Spinner>(R.id.classSpinner)

        val classArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classNames)
        classSpinner.adapter = classArrayAdapter

        // Level spinner
        val levels = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        val levelSpinner = findViewById<Spinner>(R.id.levelSpinner)

        val levelArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levels)
        levelSpinner.adapter = levelArrayAdapter



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
            val playerClass = classSpinner.selectedItem.toString()
            val newCharacter = PlayerCharacter(name, playerClass, level, strength, dexterity,
                    constitution, intelligence, wisdom, charisma)

            thread {
                db.charactersDao().insert(newCharacter)
                val myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
        }
    }

}
