package kavorka.dndspelltracker

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.room.Room
import kavorka.dndspelltracker.data.CharacterDatabase

class MainActivity : AppCompatActivity() {

    lateinit var mTestButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, CharacterDatabase::class.java, "CharacterDatabase").build()

        //TEMP
        mTestButton = findViewById(R.id.testButton)

        mTestButton.setOnClickListener {
            val myIntent = Intent(this@MainActivity, CharacterScreenActivity::class.java)
            startActivity(myIntent)
        }
    }
}
