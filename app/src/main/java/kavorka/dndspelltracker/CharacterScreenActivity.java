package kavorka.dndspelltracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }

}
