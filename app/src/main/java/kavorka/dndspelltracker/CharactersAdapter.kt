package kavorka.dndspelltracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter
import kotlin.concurrent.thread

class CharactersAdapter(val context: Context, val launchCharacterScreenActivity: (String) -> Unit, val launchNewCharacterActivity: (String) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    val list = mutableListOf<PlayerCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_characters, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.nameTextView)
        val levelText = view.findViewById<TextView>(R.id.levelTextView)
        val newCharacterButton = view.findViewById<Button>(R.id.newCharacterButton)
        val goToCharacterButton = view.findViewById<Button>(R.id.goToCharacterButton)
        val editImageButton = view.findViewById<ImageButton>(R.id.editImageButton)
        val deleteImageButton = view.findViewById<ImageButton>(R.id.deleteImageButton)


        fun updateView(index: Int) {
            nameText.visibility = View.GONE
            levelText.visibility = View.GONE
            goToCharacterButton.visibility = View.GONE
            newCharacterButton.visibility = View.GONE
            editImageButton.visibility = View.GONE
            deleteImageButton.visibility = View.GONE

            if (index != list.size) {
                val playerCharacter = list[index]

                nameText.visibility = View.VISIBLE
                levelText.visibility = View.VISIBLE
                goToCharacterButton.visibility = View.VISIBLE
                editImageButton.visibility = View.VISIBLE
                deleteImageButton.visibility = View.VISIBLE

                editImageButton.setOnClickListener {
                    launchNewCharacterActivity(playerCharacter.name)
                }

                goToCharacterButton.setOnClickListener {
                    launchCharacterScreenActivity(playerCharacter.name)
                }

                deleteImageButton.setOnClickListener {
                    thread {
                        db.charactersDao().deleteCharacterByName(playerCharacter.name)
                        db.spellsDao().deleteSpellsByCharacter(playerCharacter.name)
                        db.abilityDao().deleteAbilitiesByCharacter(playerCharacter.name)
                    }
                }

                nameText.text = playerCharacter.name
                levelText.text = "Level: " + playerCharacter.level
            } else {
                newCharacterButton.visibility = View.VISIBLE
                newCharacterButton.setOnClickListener{
                    launchNewCharacterActivity("")
                }
            }
        }
    }
}