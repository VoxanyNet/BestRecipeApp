package bestteam.bestrecipeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewRecipePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        RecipePost post = (RecipePost) getIntent().getSerializableExtra("POST");

        Toast.makeText(ViewRecipePost.this, post.title, Toast.LENGTH_SHORT).show();

        // Create fragment instance with arguments
        ReviewRecipePostFragment fragment = new ReviewRecipePostFragment();
        Bundle args = new Bundle();
        args.putSerializable("POST", post);

        fragment.setArguments(args);

        // Load the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.postFragment, fragment);
        fragmentTransaction.commit();
    }
}
