package kavorka.dndspelltracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kavorka.dndspelltracker.data.Ability

class NewAbilityAdapter(val viewModel: NewCharacterViewModel) : RecyclerView.Adapter<NewAbilityAdapter.ViewHolder>(){
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
        var feats = arrayOf(lucky, magicInitiate, martialAdept)

        val addAbilityButton = itemView.findViewById<ImageButton>(R.id.addImageButton)
        val deleteAbilityButton = itemView.findViewById<ImageButton>(R.id.deleteImageButton)

        fun updateView(index: Int) {
            val abilities = mutableListOf<String>()
            abilities.addAll(feats)
            if (viewModel.characterClass == warlock) abilities.addAll(availableInvocations(viewModel.level))
            val featSpinnerAdapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, abilities)

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
                        val newAbilityName = featSpinner.selectedItem.toString()
                        if (newAbilityName in feats) {
                            val ability: Ability = getFeat(newAbilityName)
                            abilitiesList[index] = ability
                        } else if (newAbilityName in availableInvocations()) {
                            val ability: Ability = getInvocation(newAbilityName)
                            abilitiesList[index] = ability
                        }

//                        abilitiesList[index].name = featSpinner.selectedItem.toString()
                    }
                    override fun onNothingSelected(parent: AdapterView<out Adapter>?) {}
                }


                deleteAbilityButton.visibility = View.VISIBLE
                deleteAbilityButton.setOnClickListener {
//                    viewModel.feats.removeAt(index)
                    abilitiesList.removeAt(index)
                    notifyDataSetChanged()
                }

            } else {
                addAbilityButton.visibility = View.VISIBLE
                addAbilityButton.setOnClickListener {
                    val ability = getFeat(lucky)
//                    viewModel.feats.add(ability)
                    abilitiesList.add(ability)
                    notifyDataSetChanged()
                }
            }


        }
    }
}