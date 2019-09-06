package kavorka.dndspelltracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.PlayerCharacter

class CharactersAdapter(val context: Context, val launchCharacterScreenActivity: (String) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
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


        fun updateView(index: Int) {
            nameText.visibility = View.GONE
            levelText.visibility = View.GONE
            goToCharacterButton.visibility = View.GONE
            newCharacterButton.visibility = View.GONE

            if (index != list.size) {
                val playerCharacter = list[index]

                nameText.visibility = View.VISIBLE
                levelText.visibility = View.VISIBLE
                goToCharacterButton.visibility = View.VISIBLE
                goToCharacterButton.setOnClickListener {
                    launchCharacterScreenActivity(playerCharacter.name)
                }

                nameText.text = playerCharacter.name
                levelText.text = "Level: " + playerCharacter.level
            } else {
                newCharacterButton.visibility = View.VISIBLE
                newCharacterButton.setOnClickListener{
                    context.startActivity(Intent(context, NewCharacterActivity::class.java))
//                    val intent = Intent(it.context, NewCharacterActivity::class.java)
//                    it.context.startActivity(intent)
//                    launchNewCharacterActivity
                }
            }
        }
    }
}