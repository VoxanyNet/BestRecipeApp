package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        // Set OnClickListener for the back button
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the HomeActivity
                Intent intent = new Intent(FavoritesActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        // Set OnClickListener for the filters button
        Button btnFilters = findViewById(R.id.btn_filters);
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start FiltersActivity when the button is clicked
                Intent intent = new Intent(FavoritesActivity.this, FiltersActivity.class);
                startActivity(intent);
            }
        });
    }
}
