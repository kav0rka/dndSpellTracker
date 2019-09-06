package kavorka.dndspelltracker

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class NewCharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        val classNames = arrayOf(bard, barbarian, cleric, druid, fighter, monk, paladin, ranger, rogue, sorcerer, warlock, wizard)
        val classSpinner = findViewById<Spinner>(R.id.classSpinner)

        val classArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classNames)
        classSpinner.adapter = classArrayAdapter
    }

}
