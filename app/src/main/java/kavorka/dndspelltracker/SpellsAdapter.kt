package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.SpellSlot

class SpellsAdapter(val mCharacter: CharacterViewModel) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    private fun useSpell(lvl: Int) {
        mCharacter.useSpell(lvl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        var count = 0
        for (i in mCharacter.getSpellSlots()) {
            if (i > 0) count++
        }
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val used = mCharacter.spellsUsed[position]
        val max = mCharacter.getSpellSlots()[position]

        holder.spellLevel.text = "Level " + (position + 1) + ":  "
        holder.spellSlots.text = (max - used).toString() + " / " + max

        holder.spellDown.setOnClickListener {
            mCharacter.useSpell(position)
            notifyItemChanged(position)
        }

        holder.spellUp.setOnClickListener {
            mCharacter.unuseSpell(position)
            notifyItemChanged(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var parentLayout: ConstraintLayout
        internal var spellLevel: TextView
        internal var spellSlots: TextView

        internal var spellUp: Button
        internal var spellDown: Button

        init {
            spellLevel = itemView.findViewById(R.id.textViewLvl)
            spellSlots = itemView.findViewById(R.id.textViewSlotsUsed)
            parentLayout = itemView.findViewById(R.id.spell_slot_parent)
            spellDown = itemView.findViewById(R.id.buttonSpellDown)
            spellUp = itemView.findViewById(R.id.buttonSpellUp)
        }
    }

}
