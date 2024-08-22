package bestteam.bestrecipeapp;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecipePost {
    public String title;
    public String author;
    public String description;
    public ZonedDateTime creation_date;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<RecipeStep> steps;

    public static RecipePost fromDocument(QueryDocumentSnapshot document) {

        // cast abstract document fields to the correct types
        String title = (String) document.get("title");
        String author = (String) document.get("author");
        String description = (String) document.get("description");

        // need some more steps to convert here
        Timestamp creation_date_timestamp = (Timestamp) document.get("created");
        Instant creation_date_instant = creation_date_timestamp.toInstant();
        ZonedDateTime creation_date = ZonedDateTime.ofInstant(creation_date_instant, ZoneId.of("UTC")); // firebase stores creation date in UTC

        // somehow this casting works
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) document.get("ingredients");
        ArrayList<RecipeStep> steps = (ArrayList<RecipeStep>) document.get("steps");

        return new RecipePost(title, author, description, creation_date, ingredients, steps);

    }
    public RecipePost(
        String title,
        String author,
        String description,
        ZonedDateTime creation_date,
        ArrayList<Ingredient> ingredients,
        ArrayList<RecipeStep> steps
    ) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.creation_date = creation_date;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}
