package bestteam.bestrecipeapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScrollableRecipePostsFragment extends Fragment {

    public List<RecipePost> recipePosts;

    public ScrollableRecipePostsFragment(List<RecipePost> recipePosts) {
        this.recipePosts = recipePosts;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scrollablerecipeposts, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the parent activity that this fragment is attached to
        Activity parentActivity = getActivity();

        // retrieve the recycler view from the layout
        RecyclerView recyclerView = view.findViewById(R.id.postsRecyclerView);


        // create the adapter
        RecipePostAdapter recipePostAdapter = new RecipePostAdapter(parentActivity, this.recipePosts);

        // attach the adapter to the recycler view
        recyclerView.setAdapter(recipePostAdapter);

        // not really sure what this does
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }
}