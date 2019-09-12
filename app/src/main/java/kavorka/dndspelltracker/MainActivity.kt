package kavorka.dndspelltracker

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kavorka.dndspelltracker.data.CharacterDatabase
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration


const val bard = "Bard"
const val barbarian = "Barbarian"
const val cleric = "Cleric"
const val druid = "Druid"
const val fighter = "Fighter"
const val monk = "Monk"
const val paladin = "Paladin"
const val ranger = "Ranger"
const val rogue = "Rogue"
const val sorcerer = "Sorcerer"
const val warlock = "Warlock"
const val wizard = "Wizard"

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
        val itemDecor = DividerItemDecoration(this, HORIZONTAL)
        recyclerView.addItemDecoration(itemDecor)

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
