package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.Spells
import kotlin.concurrent.thread

class SpellsAdapter(val characterViewModel: CharacterViewModel) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {
    val spellList = mutableListOf<Spells>()
    val abilityList = mutableListOf<Ability>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =  spellList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)

        holder.spellDown.setOnClickListener {
            characterViewModel.useSpell(position)
            thread {
                db.spellsDao().insert(characterViewModel.spells[position])
            }
            notifyItemChanged(position)
        }

        holder.spellUp.setOnClickListener {
            characterViewModel.unUseSpell(position)
            thread {
                db.spellsDao().insert(characterViewModel.spells[position])
            }
            notifyItemChanged(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var spellLevel = itemView.findViewById<TextView>(R.id.textViewLvl)
        var spellSlots = itemView.findViewById<TextView>(R.id.textViewSlotsUsed)
        var spellUp = itemView.findViewById<ImageButton>(R.id.buttonSpellDown)
        var spellDown = itemView.findViewById<ImageButton>(R.id.buttonSpellUp)


        fun updateView(index: Int) {
            val spells = spellList[index]
            spellLevel.text = "Level " + spells.level.toString() + ":"
            spellSlots.text = spells.used.toString() + "/" + spells.max.toString()
        }
    }

}
