package bestteam.bestrecipeapp;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.Timestamp;

public class PostView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        // Intents allows us to pass data from one activity to another
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        String description = intent.getStringExtra("DESCRIPTION");
        String post_date = intent.getStringExtra("POST_DATE");

        TextView titleView = findViewById(R.id.postTitle);
        TextView descriptionView = findViewById(R.id.bodyText);
        TextView authorView = findViewById(R.id.author);

        titleView.setText(title);
        descriptionView.setText(description);
        authorView.setText(author);

        // we can only pass some primitive types between activities, so we need to convert them back


    }
}