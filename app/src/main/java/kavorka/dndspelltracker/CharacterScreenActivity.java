package kavorka.dndspelltracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterScreenActivity extends AppCompatActivity {
    public Character mCharacter;

    public CharacterScreenActivity() {
        mCharacter = new Character();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_screen);

        TextView nameTextView = (TextView) findViewById(R.id.textViewName);
        nameTextView.setText(mCharacter.getName());

        TextView hpTextView = (TextView) findViewById(R.id.textViewHP);
        hpTextView.setText(mCharacter.getHitPoints() + "");
        initRecyclerView();
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mCharacter.getSpellSlots());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
