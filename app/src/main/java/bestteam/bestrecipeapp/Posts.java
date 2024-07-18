package bestteam.bestrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Posts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        // retrieve the recycler view from the layout
        RecyclerView recyclerView = findViewById(R.id.postsRecyclerView);

        // create some example posts
        List<RecipePost> recipePosts = new ArrayList<RecipePost>();

        for (int i = 0; i < 100; i++) {
            recipePosts.add(new RecipePost("Example Title", "Example Author", "Example Description"));
        }


        // create the adapter
        RecipePostAdapter recipePostAdapter = new RecipePostAdapter(this, recipePosts);

        // attach the adapter to the recycler view
        recyclerView.setAdapter(recipePostAdapter);

        // not really sure what this does
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}