package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class FiltersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters); // Ensure you have this layout file

        // Set OnClickListener for the back button
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the FavoritesActivity
                Intent intent = new Intent(FiltersActivity.this, FavoritesActivity.class);
                startActivity(intent);
                finish(); // Optionally, finish this activity to remove it from the back stack
            }
        });
    }
}
