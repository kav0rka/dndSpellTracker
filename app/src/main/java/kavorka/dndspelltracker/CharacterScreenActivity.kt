package kavorka.dndspelltracker

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread
import kotlinx.android.synthetic.main.activity_character_screen.*

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

        nameTextView.text = name

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
            hpEditText.setText(viewModel.hitPoints.toString())
            hpMaxTextView.text = " / " + (playerCharacter.maxHP.toString())

            viewModel.characterClass = playerCharacter.characterClass

            hpEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s != null) {
                        val hp = s.toString()
                        if (hp != "") {
                            viewModel.hitPoints = hp.toInt()
                            thread {
                                playerCharacter.hp = hp.toInt()
                                db.charactersDao().insert(playerCharacter)
                            }
                        }
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

        }

        shortRestButton.setOnClickListener {
            viewModel.doShortRest()
            thread {
                viewModel.spells.forEach {
                    db.spellsDao().insert(it)
                }
                viewModel.abilities.forEach {
                    db.abilityDao().insert(it)
                }
            }
            abilitiesAdapter.notifyDataSetChanged()
            spellsAdapter.notifyDataSetChanged()
        }
//
        longRestButton.setOnClickListener {
            viewModel.doLongRest()
            viewModel.hitPoints = playerCharacter.maxHP
            thread {
                viewModel.spells.forEach {
                    db.spellsDao().insert(it)
                }
                viewModel.abilities.forEach {
                    db.abilityDao().insert(it)
                }
                db.charactersDao().insert(playerCharacter)
            }
            hpEditText.setText(playerCharacter.maxHP.toString())
            abilitiesAdapter.notifyDataSetChanged()
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

        // Add Line spacer to both
        val itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
        spellRecycler.addItemDecoration(itemDecor)
        abilitiesRecycler.addItemDecoration(itemDecor)
    }

}
