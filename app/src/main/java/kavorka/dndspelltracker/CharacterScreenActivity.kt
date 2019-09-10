package kavorka.dndspelltracker

import android.os.Bundle
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
    lateinit var mRecyclerView: RecyclerView
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_screen)


        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)

        thread {
            playerCharacter = db.charactersDao().getCharacterByName(intent.getStringExtra("Name"))
            val nameTextView = findViewById<TextView>(R.id.textViewName)
            name = playerCharacter.name
            nameTextView.text = name
            viewModel.level = playerCharacter.level
        }
        initRecyclerView()

        ViewModelProviders.of(this)
                .get(CharacterViewModel::class.java)
                .getSpells(name).observe(this, Observer {
                    if (it != null) {
//                        mRecyclerView.adapter
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
        mRecyclerView = findViewById(R.id.recyclerView)
        val adapter = SpellsAdapter(viewModel)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun resetRecyclerView() {
        for (i in 0..8) {
            mRecyclerView.adapter!!.notifyItemChanged(i)
        }
    }

}
