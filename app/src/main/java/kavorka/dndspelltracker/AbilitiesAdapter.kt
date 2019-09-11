package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Spells
import kotlin.concurrent.thread

class AbilitiesAdapter(val characterViewModel: CharacterViewModel): RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {
    val list = mutableListOf<Spells>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_spell_slots, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =  list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun updateView(index: Int) {

        }
    }
}