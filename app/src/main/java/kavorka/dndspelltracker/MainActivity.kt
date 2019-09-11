package kavorka.dndspelltracker

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kavorka.dndspelltracker.data.CharacterDatabase
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

val bard = "Bard"
val barbarian = "Barbarian"
val cleric = "Cleric"
val druid = "Druid"
val fighter = "Fighter"
val monk = "Monk"
val paladin = "Paladin"
val ranger = "Ranger"
val rogue = "Rogue"
val sorcerer = "Sorcerer"
val warlock = "Warlock"
val wizard = "Wizard"

lateinit var db: CharacterDatabase

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(applicationContext, CharacterDatabase::class.java, "CharacterDatabase").build()

        // Clear the data!
//        (applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).clearApplicationUserData()


        recyclerView = findViewById(R.id.charactersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val launchNewCharacterActivity = {name: String ->
            val intent = Intent(this, NewCharacterActivity::class.java)
            intent.putExtra("Name", name)
            startActivity(intent)
        }

        val launchCharacterScreenActivity = {name: String ->
            val intent = Intent(this, CharacterScreenActivity::class.java)
            intent.putExtra("Name", name)
            startActivity(intent)
        }

        val myAdapter = CharactersAdapter(this, launchCharacterScreenActivity, launchNewCharacterActivity)

        recyclerView.adapter = myAdapter

        ViewModelProviders.of(this)
                .get(MainViewModel::class.java)
                .getCharacters()
                .observe(this, Observer {
                    if (it != null) {
                        myAdapter.list.clear()
                        myAdapter.list.addAll(it)
                        myAdapter.notifyDataSetChanged()
                    }
                })



    }
}
