package kavorka.dndspelltracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int[] mSpellSlots;
    private int[] mSpellSlotsUsed;

    public RecyclerViewAdapter(Context context, int[] spellSlots) {
        // TODO: remove context?
        mSpellSlots = spellSlots;
        mSpellSlotsUsed = new int[9];
    }

    private void useSpell(int lvl) {
        mSpellSlotsUsed[lvl] = mSpellSlotsUsed[lvl] + 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spell_slots, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i : mSpellSlots) {
            if (i > 0) count++;
        }
        return count;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int used = mSpellSlotsUsed[position];
        int max = mSpellSlots[position];

        holder.spellLevel.setText("Level " + (position + 1) + ":  ");
        holder.spellSlots.setText(max - used + " / " + max);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parentLayout;
        TextView spellLevel;
        TextView spellSlots;

        public ViewHolder(View itemView) {
            super(itemView);
            spellLevel = itemView.findViewById(R.id.textViewLvl);
            spellSlots = itemView.findViewById(R.id.textViewSlotsUsed);
            parentLayout = itemView.findViewById(R.id.spell_slot_parent);
        }
    }

}
