package kavorka.dndspelltracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharacterScreenActivity : AppCompatActivity() {
    lateinit var character: CharacterViewModel
    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_screen)

        character = ViewModelProviders.of(this).get(CharacterViewModel::class.java)

        val nameTextView = findViewById<TextView>(R.id.textViewName)
        nameTextView.text = character.name

        val hpTextView = findViewById<TextView>(R.id.textViewHP)
        hpTextView.text = character.hitPoints.toString() + ""

        val btnShortRest = findViewById<Button>(R.id.buttonShortRest)
        val btnLongRest = findViewById<Button>(R.id.buttonLongRest)
        initRecyclerView()

        btnShortRest.setOnClickListener { character.doShortRest() }

        btnLongRest.setOnClickListener {
            character.doLongRest()
            resetRecyclerView()
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        val adapter = SpellsAdapter(character)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun resetRecyclerView() {
        for (i in 0..8) {
            mRecyclerView.adapter!!.notifyItemChanged(i)
        }
    }

}
