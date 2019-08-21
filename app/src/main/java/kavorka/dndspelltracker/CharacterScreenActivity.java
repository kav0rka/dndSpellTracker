package kavorka.dndspelltracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterScreenActivity extends AppCompatActivity {
    public Character mCharacter;
    public RecyclerView mRecyclerView;

    public CharacterScreenActivity() {
        mCharacter = new Character();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_screen);

        TextView nameTextView = findViewById(R.id.textViewName);
        nameTextView.setText(mCharacter.getName());

        TextView hpTextView = findViewById(R.id.textViewHP);
        hpTextView.setText(mCharacter.getHitPoints() + "");

        Button btnShortRest = findViewById(R.id.buttonShortRest);
        Button btnLongRest = findViewById(R.id.buttonLongRest);
        initRecyclerView();

        btnShortRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacter.doShortRest();
            }
        });

        btnLongRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacter.doLongRest();
                resetRecyclerView();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mCharacter);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void resetRecyclerView() {
        for (int i=0 ; i < 9 ; i++) {
            mRecyclerView.getAdapter().notifyItemChanged(i);
        }
    }

}
