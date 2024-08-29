package bestteam.bestrecipeapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Recipe {
    public String author;
    public String title;
    public String description;
    public ArrayList<String> ingredients;
    public ArrayList<String> steps;

    // Default constructor required for calls to DataSnapshot.getValue(Recipe.class)
    public Recipe() {
    }

    // Constructor with parameters
    public Recipe(String title, String description, ArrayList<String> ingredients, ArrayList<String> steps) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        this.author = auth.getCurrentUser().getUid();
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}

