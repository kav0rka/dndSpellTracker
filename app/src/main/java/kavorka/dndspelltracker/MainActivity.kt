package kavorka.dndspelltracker

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kavorka.dndspelltracker.data.CharacterDatabase
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread
import android.content.Context.ACTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.ActivityManager
import android.content.Context


lateinit var db: CharacterDatabase

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var mTestButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(applicationContext, CharacterDatabase::class.java, "CharacterDatabase").build()

        // Clear the data!
        //(applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).clearApplicationUserData()

        // For Testing
        thread {
            db.charactersDao().insert(PlayerCharacter("Gideon", level=2))
            db.charactersDao().insert(PlayerCharacter("Delg", level=4))
        }
        recyclerView = findViewById(R.id.charactersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val myAdapter = CharactersAdapter()
        thread {
            myAdapter.list.addAll(db.charactersDao().getAll())
        }
        recyclerView.adapter = myAdapter

        //TEMP
        mTestButton = findViewById(R.id.testButton)

        mTestButton.setOnClickListener {
            val myIntent = Intent(this@MainActivity, CharacterScreenActivity::class.java)
            startActivity(myIntent)
        }
    }
}
