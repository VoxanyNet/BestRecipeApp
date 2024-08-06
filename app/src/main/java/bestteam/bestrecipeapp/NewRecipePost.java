package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDescriptionActivity extends AppCompatActivity {

    private Button btnNext;
    private ImageView ivBack;
    private EditText etRecipeTitle, etRecipeDescription;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe_description);

        btnNext = findViewById(R.id.btnNext);
        ivBack = findViewById(R.id.ivBack);
        etRecipeTitle = findViewById(R.id.etRecipeTitle);
        etRecipeDescription = findViewById(R.id.etRecipeDescription);

        Button btnProChef = findViewById(R.id.btnProChef);
        Button btnHomeCook = findViewById(R.id.btnHomeCook);
        Button btnBeginner = findViewById(R.id.btnBeginner);

        btnProChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "PRO CHEF";
                btnProChef.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnHomeCook.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
                btnBeginner.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
            }
        });

        btnHomeCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "HOME COOK";
                btnHomeCook.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnProChef.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
                btnBeginner.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
            }
        });

        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "BEGINNER";
                btnBeginner.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnProChef.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
                btnHomeCook.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
            }
        });

        // Set up the Next button to navigate to AddIngredientsActivity and pass data
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeTitle = etRecipeTitle.getText().toString();
                String recipeDescription = etRecipeDescription.getText().toString();

                Intent intent = new Intent(RecipeDescriptionActivity.this, AddIngredients.class);
                intent.putExtra("RECIPE_TITLE", recipeTitle);
                intent.putExtra("RECIPE_DESCRIPTION", recipeDescription);
                intent.putExtra("DIFFICULTY", difficulty);
                startActivity(intent);
            }
        });

        // Optionally, set up the back button to finish the activity
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
