package bestteam.bestrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ReviewRecipePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_recipe);

        Button publishButton = findViewById(R.id.publishRecipe);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // get the current logged in user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Retrieve data passed from AddDirections activity
        RecipePost newPost = (RecipePost) getIntent().getSerializableExtra("POST");
        
        // register login button callback
        publishButton.setOnClickListener(new View.OnClickListener() {
            // this is the callback
            @Override
            public void onClick(View view) {

                newPost.postToFirebase();

                Intent intent = new Intent(ReviewRecipePost.this, Home.class);

                startActivity(intent);

            }
        });


        ReviewRecipePostFragment fragment = new ReviewRecipePostFragment();
        Bundle args = new Bundle();
        args.putSerializable("POST", newPost);

        fragment.setArguments(args);

        // Load the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.postFragment, fragment);
        fragmentTransaction.commit();
    }
}
