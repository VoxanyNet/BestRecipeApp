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

    public static ReviewRecipePostFragment newInstance(
        String recipeTitle, String recipeDescription,
        ArrayList<String> ingredientsList,
        ArrayList<String> stepTitles,
        ArrayList<String> stepDescriptions) {
        ReviewRecipePostFragment fragment = new ReviewRecipePostFragment();
        Bundle args = new Bundle();

        args.putString("RECIPE_TITLE", recipeTitle);
        args.putString("RECIPE_DESCRIPTION", recipeDescription);
        args.putStringArrayList("INGREDIENTS_LIST", ingredientsList);
        args.putStringArrayList("STEP_TITLES", stepTitles);
        args.putStringArrayList("STEP_DESCRIPTIONS", stepDescriptions);
        fragment.setArguments(args);
        return fragment;
    }

    public static ReviewRecipePostFragment newInstanceFromPost(RecipePost post) {
        ReviewRecipePostFragment fragment = new ReviewRecipePostFragment();
        Bundle args = new Bundle();
        args.putString("RECIPE_TITLE", post.title);
        args.putString("RECIPE_DESCRIPTION", post.description);

        // right now the fragment only supports the ingredient names
        ArrayList<String> ingredientsList =  new ArrayList<>();
        for (Ingredient ingredient : post.ingredients) {
            ingredientsList.add(ingredient.ingredient);
        }

        args.putStringArrayList("INGREDIENTS_LIST", ingredientsList);

        // pass steps
        ArrayList<String> stepTitles = new ArrayList<>();
        ArrayList<String> stepDescriptions = new ArrayList<>();

        for (RecipeStep step : post.steps) {
            stepTitles.add(step.title);
            stepDescriptions.add(step.content);
        }

        args.putStringArrayList("STEP_TITLES", stepTitles);
        args.putStringArrayList("STEP_DESCRIPTIONS", stepDescriptions);

        fragment.setArguments(args);

        return fragment;
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
            String recipeTitle = getArguments().getString("RECIPE_TITLE");
            String recipeDescription = getArguments().getString("RECIPE_DESCRIPTION");
            ArrayList<String> ingredientsList = getArguments().getStringArrayList("INGREDIENTS_LIST");
            ArrayList<String> stepTitles = getArguments().getStringArrayList("STEP_TITLES");
            ArrayList<String> stepDescriptions = getArguments().getStringArrayList("STEP_DESCRIPTIONS");

            // Set the recipe title and description
            recipeTitleTextView.setText(recipeTitle);
            recipeDescriptionTextView.setText(recipeDescription);

            // Populate the ingredients list
            if (ingredientsList != null) {
                for (String ingredient : ingredientsList) {
                    TextView ingredientTextView = new TextView(getContext());
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

                    TextView stepTextView = new TextView(getContext());
                    stepTextView.setText((i + 1) + ". " + stepTitle + "\n" + stepDescription);
                    stepTextView.setTextSize(16);
                    stepTextView.setTextColor(getResources().getColor(android.R.color.black));
                    stepTextView.setPadding(0, 8, 0, 8);
                    directionsListLayout.addView(stepTextView);
                }
            }
        }

        return view;
    }
}