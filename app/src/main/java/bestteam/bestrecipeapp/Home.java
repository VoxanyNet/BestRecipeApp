package bestteam.bestrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home);

        List<RecipePost> recipes = new ArrayList<RecipePost>();

        recipes.add(
                new RecipePost(
                        "Example Title",
                        "Author",
                        "Description"
                )
        );

        recipes.add(
                new RecipePost(
                        "Example Title",
                        "Author",
                        "Description"
                )
        );

        // actually create the fragment
        ScrollableRecipePostsFragment fragment = new ScrollableRecipePostsFragment(recipes);

        // add the fragment to the activity
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();

    }
}