package bestteam.bestrecipeapp;

import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipePost implements Serializable {
    public String title;
    public String author;
    public String description;
    public ArrayList<String> ingredients;
    public ArrayList<RecipeStep> steps;

    public static RecipePost fromDocument(QueryDocumentSnapshot document) {

        // cast abstract document fields to the correct types
        String title = (String) document.get("title");
        String author = (String) document.get("author");
        String description = (String) document.get("description");

        ArrayList<String> ingredients = (ArrayList<String>) document.get("ingredients");

        ArrayList<HashMap<String, String>> document_steps = (ArrayList<HashMap<String,String>>) document.get("steps");

        ArrayList<RecipeStep> steps = new ArrayList<>();

        for (HashMap<String, String> step : document_steps) {
            steps.add(
                    new RecipeStep(
                            step.get("title"),
                            step.get("content")
                    )
            );
        }

        return new RecipePost(title, author, description, ingredients, steps);

    }

    public void postToFirebase() {
        // get firestore and auth objects
        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        // create new document
//        Map<String, Object> newRecipe = new HashMap<>();
//
//        newRecipe.put("title", this.title);
//        newRecipe.put("author", auth.getCurrentUser().getUid());
//        newRecipe.put("created", Timestamp.now());
//        newRecipe.put("description", this.description);
//        newRecipe.put("steps", this.steps);
//        newRecipe.put("ingredients", this.ingredients);

        db.collection("recipes").add(this).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String documentId = documentReference.getId();
            }
        });
    }

    public RecipePost(
        String title,
        String author,
        String description,
        ArrayList<String> ingredients,
        ArrayList<RecipeStep> steps
    ) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}
