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
    lateinit var abilitiesRecycler: RecyclerView
    lateinit var abilitiesAdapter: AbilitiesAdapter
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_screen)


        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        name = intent.getStringExtra("Name")
        initRecyclerViews()

        val nameTextView = findViewById<TextView>(R.id.textViewName)
        nameTextView.text = name

        val hpTextView = findViewById<TextView>(R.id.textViewHP)

        thread {
            playerCharacter = db.charactersDao().getCharacterByName(name)

            viewModel.spells.clear()
            viewModel.spells.addAll(db.spellsDao().getSpellsByCharacter(name))
            spellsAdapter.spellList.addAll(viewModel.spells)
            spellsAdapter.notifyDataSetChanged()

            viewModel.abilities.clear()
            viewModel.abilities.addAll(db.abilityDao().getAbilitiesByCharacter(name))
            abilitiesAdapter.abilitiesList.addAll(viewModel.abilities)
            abilitiesAdapter.notifyDataSetChanged()

            viewModel.hitPoints = playerCharacter.hp
            hpTextView.text = "HP:  " + playerCharacter.hp.toString() + " / " + playerCharacter.maxHP.toString()
        }




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

    private fun initRecyclerViews() {
        // Spells
        spellRecycler = findViewById(R.id.spellsRecyclerView)
        spellsAdapter = SpellsAdapter(viewModel)
        spellRecycler.adapter = spellsAdapter
        spellRecycler.layoutManager = LinearLayoutManager(this)

        // Abilities
        abilitiesRecycler = findViewById(R.id.abilitiesRecyclerView)
        abilitiesAdapter = AbilitiesAdapter(viewModel)
        abilitiesRecycler.adapter = abilitiesAdapter
        abilitiesRecycler.layoutManager = LinearLayoutManager(this)


        val itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
        spellRecycler.addItemDecoration(itemDecor)
        abilitiesRecycler.addItemDecoration(itemDecor)
    }

}
