package kavorka.dndspelltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Ability

class NewAbilityAdapter : RecyclerView.Adapter<NewAbilityAdapter.ViewHolder>(){
    val abilitiesList = mutableListOf<Ability>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_new_ability, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =  abilitiesList.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val featSpinner = itemView.findViewById<Spinner>(R.id.newAbilitySpinner)
        val feats = arrayOf(lucky, magicInitiate, martialAdept)
        val featSpinnerAdapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, feats)

        val addAbilityButton = itemView.findViewById<ImageButton>(R.id.addImageButton)
        val deleteAbilityButton = itemView.findViewById<ImageButton>(R.id.deleteImageButton)

        fun updateView(index: Int) {
            featSpinner.visibility = View.GONE
            addAbilityButton.visibility = View.GONE
            deleteAbilityButton.visibility = View.GONE

            if (index != abilitiesList.size) {
                featSpinner.adapter = featSpinnerAdapter
                featSpinner.visibility = View.VISIBLE
                if (abilitiesList[index].name != "") {
                    featSpinner.setSelection(featSpinnerAdapter.getPosition(abilitiesList[index].name))
                }
                featSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                        abilitiesList[index].name = featSpinner.selectedItem.toString()
                    }
                    override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
                }


                deleteAbilityButton.visibility = View.VISIBLE
                deleteAbilityButton.setOnClickListener {
                    abilitiesList.removeAt(index)
                    notifyDataSetChanged()
                }

            } else {
                addAbilityButton.visibility = View.VISIBLE
                addAbilityButton.setOnClickListener {
                    val ability = Ability("", "", 1)
                    abilitiesList.add(ability)
                    notifyDataSetChanged()
                }
            }


        }
    }
}