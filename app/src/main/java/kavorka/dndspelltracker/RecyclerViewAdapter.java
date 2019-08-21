package kavorka.dndspelltracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Character mCharacter;

    public RecyclerViewAdapter(Character character) {
        mCharacter = character;
    }

    private void useSpell(int lvl) {
        mCharacter.useSpell(lvl);
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
        for (int i : mCharacter.getSpellSlots()) {
            if (i > 0) count++;
        }
        return count;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int used = mCharacter.getSpellsUsed()[position];
        int max = mCharacter.getSpellSlots()[position];

        holder.spellLevel.setText("Level " + (position + 1) + ":  ");
        holder.spellSlots.setText(max - used + " / " + max);

        holder.spellDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacter.useSpell(position);
                notifyItemChanged(position);
            }
        });

        holder.spellUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacter.unUseSpell(position);
                notifyItemChanged(position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parentLayout;
        TextView spellLevel;
        TextView spellSlots;

        Button spellUp;
        Button spellDown;

        public ViewHolder(final View itemView) {
            super(itemView);
            spellLevel = itemView.findViewById(R.id.textViewLvl);
            spellSlots = itemView.findViewById(R.id.textViewSlotsUsed);
            parentLayout = itemView.findViewById(R.id.spell_slot_parent);
            spellDown = itemView.findViewById(R.id.buttonSpellDown);
            spellUp = itemView.findViewById(R.id.buttonSpellUp);
        }
    }

}
