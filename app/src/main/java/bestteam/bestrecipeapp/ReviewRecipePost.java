package bestteam.bestrecipeapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewRecipePost extends AppCompatActivity {

    private TextView recipeTitleTextView, recipeDescriptionTextView;
    private LinearLayout ingredientsListLayout, directionsListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_recipe);


        // Initialize views
        recipeTitleTextView = findViewById(R.id.recipe_title);
        recipeDescriptionTextView = findViewById(R.id.recipe_description);
        ingredientsListLayout = findViewById(R.id.ingredients_list);
        directionsListLayout = findViewById(R.id.directions_list);

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
            }
        }
    }
}
