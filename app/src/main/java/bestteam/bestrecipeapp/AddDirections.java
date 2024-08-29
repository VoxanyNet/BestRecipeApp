package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddDirections extends AppCompatActivity {

    private EditText stepTitleEditText, stepDescriptionEditText;
    private LinearLayout stepsListLayout;
    private Button addStepButton, ReviewRecipeButton;
    private ArrayList<String> stepTitles = new ArrayList<>();
    private ArrayList<String> stepDescriptions = new ArrayList<>();
    private int stepCount = 0;

    private String recipeTitle;
    private String recipeDescription;
    private String difficulty;
    private ArrayList<String> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe_directions);

        // Initialize views
        stepTitleEditText = findViewById(R.id.step_title);
        stepDescriptionEditText = findViewById(R.id.step_description);
        stepsListLayout = findViewById(R.id.steps_list_layout);
        addStepButton = findViewById(R.id.add_step_button);
        ReviewRecipeButton = findViewById(R.id.review_recipe_button);

        // Retrieve data passed from AddIngredients activity
        Intent intent = getIntent();
        recipeTitle = intent.getStringExtra("RECIPE_TITLE");
        recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION");
        difficulty = intent.getStringExtra("DIFFICULTY");
        ingredientsList = intent.getStringArrayListExtra("INGREDIENTS_LIST");

        // Set up button listeners
        addStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStep();
            }
        });

        ReviewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewRecipe();
            }
        });
    }

    private void addStep() {
        String stepTitle = stepTitleEditText.getText().toString();
        String stepDescription = stepDescriptionEditText.getText().toString();

        if (!TextUtils.isEmpty(stepTitle) && !TextUtils.isEmpty(stepDescription)) {
            stepCount++;

            // Inflate the step layout
            View stepView = LayoutInflater.from(this).inflate(R.layout.step_item, stepsListLayout, false);

            TextView stepNumberTextView = stepView.findViewById(R.id.step_number);
            TextView stepTitleTextView = stepView.findViewById(R.id.step_title_text);
            TextView stepDescriptionTextView = stepView.findViewById(R.id.step_description_text);

            // Set the values
            stepNumberTextView.setText(String.format("Step %d:", stepCount));
            stepTitleTextView.setText(stepTitle);
            stepDescriptionTextView.setText(stepDescription);

            // Add the step view to the steps layout
            stepsListLayout.addView(stepView);

            // Add the step to the lists
            stepTitles.add(stepTitle);
            stepDescriptions.add(stepDescription);

            // Clear the input fields
            stepTitleEditText.setText("");
            stepDescriptionEditText.setText("");
        } else {
            Toast.makeText(this, "Please enter both a step title and description", Toast.LENGTH_SHORT).show();
        }
    }

    private void reviewRecipe() {
        // Handle posting the recipe, including the steps and other data
        if (stepTitles.isEmpty() || stepDescriptions.isEmpty()) {
            Toast.makeText(this, "Please add at least one step before posting the recipe", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send to review recipe post page
        Intent intent = new Intent(AddDirections.this, ReviewRecipePost.class);
        intent.putExtra("RECIPE_TITLE", recipeTitle);
        intent.putExtra("RECIPE_DESCRIPTION", recipeDescription);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        intent.putStringArrayListExtra("STEP_TITLES", stepTitles);
        intent.putStringArrayListExtra("STEP_DESCRIPTIONS", stepDescriptions);

        startActivity(intent);
    }
}
