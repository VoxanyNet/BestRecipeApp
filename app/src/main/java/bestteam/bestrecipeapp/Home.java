package bestteam.bestrecipeapp;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<RecipePost> recipes = new ArrayList<RecipePost>();

        // create a query for the database to return the last 10 created recipes
        Query recipesQuery = db.collection("recipes").limit(10);

        // execute the query
        Task<QuerySnapshot> response = recipesQuery.get();

        // add on complete listener
        response.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    // assemble recipe models from response
                    for (QueryDocumentSnapshot document : response.getResult()) {

                        recipes.add(
                            new RecipePost(
                                document.get("title").toString(),
                                document.get("author").toString(),
                                document.get("description").toString()
                            )
                        );
                    }

                    // actually create the fragment
                    ScrollableRecipePostsFragment fragment = new ScrollableRecipePostsFragment(recipes);

                    // add the fragment to the activity
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragmentContainerView, fragment)
                            .commit();

                }

                else {
                    Log.println(Log.ERROR, "test", "Fail");

                    Toast.makeText(Home.this, "Failed to fetch recipes", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        for (int i = 0; i < 100; i++) {
//            recipes.add(
//                    new RecipePost(
//                            "Example Title",
//                            "Author",
//                            "Description"
//                    )
//            );
//        }

    }
}