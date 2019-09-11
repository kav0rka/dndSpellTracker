package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Spells

class SpellsAdapter(val mCharacter: CharacterViewModel) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {
    val list = mutableListOf<Spells>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)
//
//        holder.spellDown.setOnClickListener {
//            mCharacter.useSpell(position)
//            notifyItemChanged(position)
//        }
//
//        holder.spellUp.setOnClickListener {
//            mCharacter.unuseSpell(position)
//            notifyItemChanged(position)
//        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var spellLevel = itemView.findViewById<TextView>(R.id.textViewLvl)
        var spellSlots = itemView.findViewById<TextView>(R.id.textViewSlotsUsed)
        var spellUp = itemView.findViewById<Button>(R.id.buttonSpellDown)
        var spellDown = itemView.findViewById<Button>(R.id.buttonSpellUp)


        fun updateView(index: Int) {
            val spells = list[index]
            spellLevel.text = "Level " + spells.level.toString() + ":"
            spellSlots.text = spells.used.toString() + "/" + spells.max.toString()
        }
    }

}
