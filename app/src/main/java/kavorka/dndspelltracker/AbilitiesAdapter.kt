package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Ability
import kotlin.concurrent.thread

class AbilitiesAdapter(val characterViewModel: CharacterViewModel): RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {
    val abilitiesList = mutableListOf<Ability>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =  abilitiesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)

        holder.itemDown.setOnClickListener {
            characterViewModel.useAbility(position)
            thread {
                db.abilityDao().insert(characterViewModel.abilities[position])
            }
            notifyItemChanged(position)
        }

        holder.itemUp.setOnClickListener {
            characterViewModel.unUseAbility(position)
            thread {
                db.abilityDao().insert(characterViewModel.abilities[position])
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
            val ability = abilitiesList[index]
            name.text = ability.name + ":"
            used.text = ability.used.toString() + "/" + ability.max.toString()
        }
    }
}