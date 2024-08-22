package bestteam.bestrecipeapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class ReviewRecipePost extends AppCompatActivity {

    private TextView recipeTitleTextView, recipeDescriptionTextView;
    private LinearLayout ingredientsListLayout, directionsListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_recipe);

        // Retrieve data passed from AddDirections activity
        String recipeTitle = getIntent().getStringExtra("RECIPE_TITLE");
        String recipeDescription = getIntent().getStringExtra("RECIPE_DESCRIPTION");
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        ArrayList<String> stepTitles = getIntent().getStringArrayListExtra("STEP_TITLES");
        ArrayList<String> stepDescriptions = getIntent().getStringArrayListExtra("STEP_DESCRIPTIONS");

        // Create fragment instance with arguments
        ReviewRecipePostFragment fragment = ReviewRecipePostFragment.newInstance(recipeTitle, recipeDescription, ingredientsList, stepTitles, stepDescriptions);

        // Load the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
