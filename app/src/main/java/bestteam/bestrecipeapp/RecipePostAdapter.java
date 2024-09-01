package bestteam.bestrecipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

// this class is very weird
// we give it raw posts data, then formats the data into layouts
// it also handles some logic for how to populate new posts as the user scrolls
// RecyclerView.Adapter basically gives us a template for how we should populate post layouts
public class RecipePostAdapter extends RecyclerView.Adapter<RecipePostAdapter.RecipePostViewHolder> {
    Context context;
    List<RecipePost> recipePosts;

    // construct the adapter by giving it the actual list of recipes we want to generate from
    public RecipePostAdapter(Context context, List<RecipePost> recipePosts) {
        this.context = context;
        this.recipePosts = recipePosts;
    }
    @NonNull
    @NotNull
    @Override
    public RecipePostAdapter.RecipePostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        // the inflater loads the layout xml into its actual View objects
        LayoutInflater inflater = LayoutInflater.from(context);
        // pretty sure that we are upcasting here
        View view = inflater.inflate(R.layout.post, parent, false);

        return new RecipePostViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecipePostAdapter.RecipePostViewHolder postViewHolder, int position) {
        // assign values to recycled view holders as they appear
        // we use the position parameter to determine what should go here

        // retrieve the recipe post at the provided position
        RecipePost post = recipePosts.get(position);

        postViewHolder.post = post;

        // we should instead load recipes directly from firebase as the user scrolls
        postViewHolder.recipePostAuthor.setText(post.author);
        postViewHolder.recipePostTitle.setText(post.title);
        postViewHolder.recipePostDescription.setText(post.description);
    }

    @Override
    public int getItemCount() {
        return recipePosts.size();
    }

    // this class is a little weird too
    // i think what it does is store references to the Views we want to populate when the user scrolls
    public static class RecipePostViewHolder extends RecyclerView.ViewHolder {
        // these are the View objects defined in the post.xml layout
        TextView recipePostTitle;
        TextView recipePostAuthor;
        TextView recipePostDescription;

        // store the associated post class
        RecipePost post;

        Context context;

        public RecipePostViewHolder(@NonNull @NotNull View itemView, Context context) {
            super(itemView);

            // actually retrieve the Views from the layout
            recipePostTitle = itemView.findViewById(R.id.recipePostTitle);
            recipePostAuthor = itemView.findViewById(R.id.recipePostAuthor);
            recipePostDescription = itemView.findViewById(R.id.recipePostDescription);

            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ViewRecipePost.class);

                    intent.putExtra("POST", post);

                    startActivity(context, intent, Bundle.EMPTY);
                }
            });

        }
    }
}
