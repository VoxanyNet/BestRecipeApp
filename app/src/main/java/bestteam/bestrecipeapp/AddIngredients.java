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

public class AddIngredients extends AppCompatActivity {

    private EditText etAddIngredients;
    private LinearLayout ingredientsListLayout;
    private Button addIngredientButton, btnNext;

    // List to store ingredients
    private ArrayList<String> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe_ingredients);

        etAddIngredients = findViewById(R.id.etAddIngredients);
        ingredientsListLayout = findViewById(R.id.ingredientsListLayout);
        addIngredientButton = findViewById(R.id.addIngredientButton);
        btnNext = findViewById(R.id.btnNext);

        // Initialize the ingredients list
        ingredientsList = new ArrayList<>();

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredient();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextStep();
            }
        });
    }

    private void addIngredient() {
        String ingredientText = etAddIngredients.getText().toString();
        if (!TextUtils.isEmpty(ingredientText)) {
            // Add ingredient to the list
            ingredientsList.add(ingredientText);

            // Inflate and add the view to the layout
            View ingredientView = LayoutInflater.from(this).inflate(R.layout.ingredient_item, ingredientsListLayout, false);
            TextView tvIngredientName = ingredientView.findViewById(R.id.tvIngredientName);
            tvIngredientName.setText(ingredientText);

            ingredientsListLayout.addView(ingredientView);
            etAddIngredients.setText("");
        } else {
            Toast.makeText(this, "Please enter an ingredient", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToNextStep() {
        if (ingredientsList.isEmpty()) {
            Toast.makeText(this, "Please add at least one ingredient", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the data passed from the previous activity (NewRecipePost)
        String recipeTitle = getIntent().getStringExtra("RECIPE_TITLE");
        String recipeDescription = getIntent().getStringExtra("RECIPE_DESCRIPTION");
        String difficulty = getIntent().getStringExtra("DIFFICULTY");

        // Create an intent to go to the AddDirections activity
        Intent intent = new Intent(AddIngredients.this, AddDirections.class);
        intent.putExtra("RECIPE_TITLE", recipeTitle);
        intent.putExtra("RECIPE_DESCRIPTION", recipeDescription);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);

        // Start the next activity
        startActivity(intent);
    }
}
