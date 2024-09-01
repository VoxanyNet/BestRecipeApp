package bestteam.bestrecipeapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ReviewRecipePostFragment extends Fragment {

    public ReviewRecipePostFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_recipe_post, container, false);

        // Initialize views
        TextView recipeTitleTextView = view.findViewById(R.id.recipe_title);
        TextView recipeDescriptionTextView = view.findViewById(R.id.recipe_description);
        LinearLayout ingredientsListLayout = view.findViewById(R.id.ingredients_list);
        LinearLayout directionsListLayout = view.findViewById(R.id.directions_list);

        // Retrieve arguments
        if (getArguments() != null) {
            RecipePost recipePost = (RecipePost) getArguments().getSerializable("POST");

            // Set the recipe title and description
            recipeTitleTextView.setText(recipePost.title);
            recipeDescriptionTextView.setText(recipePost.description);

            // Populate the ingredients list
            for (String ingredient : recipePost.ingredients) {
                TextView ingredientTextView = new TextView(getContext());
                ingredientTextView.setText(ingredient);
                ingredientTextView.setTextSize(16);
                ingredientTextView.setTextColor(getResources().getColor(android.R.color.black));
                ingredientsListLayout.addView(ingredientTextView);
            }

            // Populate the directions list
            for (int i = 0; i < recipePost.steps.size(); i++) {

                RecipeStep step = recipePost.steps.get(i);

                String stepTitle = step.title;
                String stepDescription = step.content;

                TextView stepTextView = new TextView(getContext());
                stepTextView.setText((i + 1) + ". " + stepTitle + "\n" + stepDescription);
                stepTextView.setTextSize(16);
                stepTextView.setTextColor(getResources().getColor(android.R.color.black));
                stepTextView.setPadding(0, 8, 0, 8);
                directionsListLayout.addView(stepTextView);
            }
        }

        return view;
    }
}