package kavorka.dndspelltracker

import android.app.ActivityManager
import android.content.Context
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
import kavorka.dndspelltracker.data.Ability


// Classes
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

// Feats (that have abilities attached)
const val lucky = "Lucky"
const val magicInitiate = "Magic Initiate"
const val martialAdept = "Martial Adept"

// Get feat
fun getFeat(feat: String): Ability {
    return when(feat) {
        lucky -> Ability("", lucky, 3, type="feat")
        magicInitiate -> Ability("", magicInitiate, 1, type="feat")
        martialAdept -> Ability("", martialAdept, 1, type="feat")
        else -> Ability("", lucky, 3, type="feat")
    }
}

// Get the modifier for an ability score
fun getAbilityMod(stat: Int): Int {
    return when (stat >= 10) {
        true -> (stat - 10) / 2
        false -> (stat - 11) / 2
    }
}

// Database
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
