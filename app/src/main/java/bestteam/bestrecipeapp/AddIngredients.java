package bestteam.bestrecipeapp;

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

public class AddIngredients extends AppCompatActivity {

    private EditText etAddIngredients;
    private LinearLayout ingredientsListLayout;
    private Button addIngredientButton, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe_ingredients);

        etAddIngredients = findViewById(R.id.etAddIngredients);
        ingredientsListLayout = findViewById(R.id.ingredientsListLayout);
        addIngredientButton = findViewById(R.id.addIngredientButton);
        btnNext = findViewById(R.id.btnNext);

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
    //when "Add Ingredient" button is pressed, get string, add to ingredient_item.xml, ...
    // add ingredient_item to ingredientListLayout section in "activity_new_recipe_ingredents.xml
    private void addIngredient() {
        String ingredientText = etAddIngredients.getText().toString();
        if (!TextUtils.isEmpty(ingredientText)) {
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
        // This will take us to the next step, --NOT COMPLETE--
        Toast.makeText(this, "Go to next step", Toast.LENGTH_SHORT).show();
    }
}

