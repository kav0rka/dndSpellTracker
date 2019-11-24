package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Spells
import kotlin.concurrent.thread

class SpellsAdapter(val characterViewModel: CharacterViewModel) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {
    val spellList = mutableListOf<Spells>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =  spellList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)

        holder.itemDown.setOnClickListener {
            characterViewModel.useSpell(position)
            thread {
                db.spellsDao().insert(characterViewModel.spells[position])
            }
            notifyItemChanged(position)
        }

        holder.itemUp.setOnClickListener {
            characterViewModel.unUseSpell(position)
            thread {
                db.spellsDao().insert(characterViewModel.spells[position])
            }
            notifyItemChanged(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.nameTextView)
        var used = itemView.findViewById<TextView>(R.id.textViewUsed)
        var itemUp = itemView.findViewById<ImageButton>(R.id.buttonDown)
        var itemDown = itemView.findViewById<ImageButton>(R.id.buttonUp)


        fun updateView(index: Int) {
            val spells = spellList[index]
            name.text = "Level " + spells.level.toString() + ":"
            used.text = spells.used.toString() + "/" + spells.max.toString()
        }
    }

}
