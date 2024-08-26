package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<RecipePost> recipes = new ArrayList<>();

        // Create a query for the database to return the last 10 created recipes
        Query recipesQuery = db.collection("recipes").limit(10);

        // Execute the query
        Task<QuerySnapshot> response = recipesQuery.get();

        // Add on complete listener
        response.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    // Assemble recipe models from response
                    for (QueryDocumentSnapshot document : response.getResult()) {
                        recipes.add(
                                new RecipePost(
                                        document.getString("title"),
                                        document.getString("author"),
                                        document.getString("description")
                                )
                        );
                    }

                    // Find the recent_posts_list container
                    LinearLayout recentPostsList = findViewById(R.id.recent_posts_list);

                    // Inflate post.xml for each RecipePost and add it to the container
                    LayoutInflater inflater = LayoutInflater.from(Home.this);

                    for (RecipePost recipe : recipes) {
                        View postView = inflater.inflate(R.layout.post, recentPostsList, false);

                        // Set the content of the inflated view
                        TextView title = postView.findViewById(R.id.recipePostTitle);
                        TextView author = postView.findViewById(R.id.recipePostAuthor);
                        TextView description = postView.findViewById(R.id.recipePostDescription);

                        title.setText(recipe.title);
                        author.setText(recipe.author);
                        description.setText(recipe.description);

                        // Add the view to the recent_posts_list container
                        recentPostsList.addView(postView);
                    }

                } else {
                    Log.e("Home", "Failed to fetch recipes");
                    Toast.makeText(Home.this, "Failed to fetch recipes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set OnClickListener for the bottom_btn_favorite button
        ImageButton btnFavorite = findViewById(R.id.bottom_btn_favorite);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the FavoritesActivity when the button is clicked
                Intent intent = new Intent(Home.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }
}
