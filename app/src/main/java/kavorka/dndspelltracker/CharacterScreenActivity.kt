package kavorka.dndspelltracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread

class CharacterScreenActivity : AppCompatActivity() {
    lateinit var viewModel: CharacterViewModel
    lateinit var playerCharacter: PlayerCharacter
    lateinit var spellRecycler: RecyclerView
    lateinit var spellsAdapter: SpellsAdapter
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_screen)


        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        initRecyclerView()

        thread {
            playerCharacter = db.charactersDao().getCharacterByName(intent.getStringExtra("Name"))
            val nameTextView = findViewById<TextView>(R.id.textViewName)
            name = playerCharacter.name
            nameTextView.text = name
            val spells = db.spellsDao().getSpellsByCharacter(name)
            spellsAdapter.list.addAll(spells)
            spellsAdapter.notifyDataSetChanged()
//            viewModel.level = playerCharacter.level
        }


        ViewModelProviders.of(this)
                .get(CharacterViewModel::class.java)
                .getSpells(name)
                .observe(this, Observer {
                    if (it != null) {
//                        spellsAdapter.list.clear()
//                        spellsAdapter.list.addAll(it)
//                        spellsAdapter.notifyDataSetChanged()
                    }
                })


//        val hpTextView = findViewById<TextView>(R.id.textViewHP)
//        hpTextView.text = playerCharacter.hitPoints.toString() + ""

        val btnShortRest = findViewById<Button>(R.id.buttonShortRest)
        val btnLongRest = findViewById<Button>(R.id.buttonLongRest)


//        btnShortRest.setOnClickListener { characte.doShortRest() }

//        btnLongRest.setOnClickListener {
//            character.doLongRest()
//            resetRecyclerView()
//        }


    }

    private fun initRecyclerView() {
        spellRecycler = findViewById(R.id.recyclerView)
        spellsAdapter = SpellsAdapter(viewModel)
        spellRecycler.adapter = spellsAdapter
        spellRecycler.layoutManager = LinearLayoutManager(this)
    }

}
