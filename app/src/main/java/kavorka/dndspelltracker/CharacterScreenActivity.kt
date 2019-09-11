package kavorka.dndspelltracker

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread

class CharacterScreenActivity : AppCompatActivity() {
    lateinit var viewModel: CharacterViewModel
    lateinit var playerCharacter: PlayerCharacter
    lateinit var spellRecycler: RecyclerView
    lateinit var spellsAdapter: SpellsAdapter
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_screen)


        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        initRecyclerView()
        name = intent.getStringExtra("Name")
        val nameTextView = findViewById<TextView>(R.id.textViewName)
        nameTextView.text = name


        thread {
            playerCharacter = db.charactersDao().getCharacterByName(name)
            viewModel.spells.clear()
            viewModel.spells.addAll(db.spellsDao().getSpellsByCharacter(name))
            spellsAdapter.list.addAll(viewModel.spells)
            spellsAdapter.notifyDataSetChanged()
        }



//        val hpTextView = findViewById<TextView>(R.id.textViewHP)
//        hpTextView.text = playerCharacter.hitPoints.toString() + ""

        val btnShortRest = findViewById<Button>(R.id.buttonShortRest)
        val btnLongRest = findViewById<Button>(R.id.buttonLongRest)


        btnShortRest.setOnClickListener { viewModel.doShortRest() }
//
        btnLongRest.setOnClickListener {
            viewModel.doLongRest()
            thread {
                viewModel.spells.forEach {
                    db.spellsDao().insert(it)
                }
            }
            spellsAdapter.notifyDataSetChanged()
        }


    }

    private fun initRecyclerView() {
        spellRecycler = findViewById(R.id.recyclerView)
        spellsAdapter = SpellsAdapter(viewModel)
        spellRecycler.adapter = spellsAdapter
        spellRecycler.layoutManager = LinearLayoutManager(this)
        val itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
        spellRecycler.addItemDecoration(itemDecor)
    }

}
