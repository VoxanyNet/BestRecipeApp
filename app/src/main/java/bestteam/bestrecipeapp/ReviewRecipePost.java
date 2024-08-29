package bestteam.bestrecipeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewRecipePost extends AppCompatActivity {

    private TextView recipeTitleTextView, recipeDescriptionTextView;
    private LinearLayout ingredientsListLayout, directionsListLayout;
    private Button postRecipeButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_recipe);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");

        // Initialize views
        recipeTitleTextView = findViewById(R.id.recipe_title);
        recipeDescriptionTextView = findViewById(R.id.recipe_description);
        ingredientsListLayout = findViewById(R.id.ingredients_list);
        directionsListLayout = findViewById(R.id.directions_list);
        postRecipeButton = findViewById(R.id.post_recipe_button);

        // Retrieve data passed from AddDirections activity
        String recipeTitle = getIntent().getStringExtra("RECIPE_TITLE");
        String recipeDescription = getIntent().getStringExtra("RECIPE_DESCRIPTION");
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        ArrayList<String> stepTitles = getIntent().getStringArrayListExtra("STEP_TITLES");
        ArrayList<String> stepDescriptions = getIntent().getStringArrayListExtra("STEP_DESCRIPTIONS");

        // Set the recipe title and description
        recipeTitleTextView.setText(recipeTitle);
        recipeDescriptionTextView.setText(recipeDescription);

        // Populate the ingredients list
        if (ingredientsList != null) {
            for (String ingredient : ingredientsList) {
                TextView ingredientTextView = new TextView(this);
                ingredientTextView.setText(ingredient);
                ingredientTextView.setTextSize(16);
                ingredientTextView.setTextColor(getResources().getColor(android.R.color.black));
                ingredientsListLayout.addView(ingredientTextView);
            }
        }

        // Populate the directions list
        ArrayList<String> stepsList = new ArrayList<>();
        if (stepTitles != null && stepDescriptions != null) {
            for (int i = 0; i < stepTitles.size(); i++) {
                String stepTitle = stepTitles.get(i);
                String stepDescription = stepDescriptions.get(i);

                TextView stepTextView = new TextView(this);
                stepTextView.setText((i + 1) + ". " + stepTitle + "\n" + stepDescription);
                stepTextView.setTextSize(16);
                stepTextView.setTextColor(getResources().getColor(android.R.color.black));
                stepTextView.setPadding(0, 8, 0, 8);
                directionsListLayout.addView(stepTextView);

                // Combine title and description for each step
                stepsList.add((i + 1) + ". " + stepTitle + "\n" + stepDescription);
            }
        }

        // Set the OnClickListener for the Post Recipe button
        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipe = new Recipe(recipeTitle, recipeDescription, ingredientsList, stepsList);
                saveRecipeToFirebase(recipe);
            }
        });
    }

    private void saveRecipeToFirebase(Recipe recipe) {
        // Save the recipe to Firebase
        databaseReference.push().setValue(recipe);
    }
}
