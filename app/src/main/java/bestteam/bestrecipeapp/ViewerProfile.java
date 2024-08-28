package bestteam.bestrecipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;

public class ViewerProfile extends AppCompatActivity {

    private FirebaseFirestore db;
    private ImageView viewerProfileImage, viewerNoPostsIcon;
    private TextView viewerProfileName;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_profile);

        userId = getIntent().getStringExtra("USER_ID");

        viewerProfileImage = findViewById(R.id.viewerProfileImage);
        viewerProfileName = findViewById(R.id.viewerProfileName);
        // viewerNoPostsIcon = findViewById(R.id.no_posts_icon);

        db = FirebaseFirestore.getInstance();

        loadUserProfile();
    }

    private void loadUserProfile() {
        db.collection("users").document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String name = document.getString("name");
                                String email = document.getString("email");
                                String profileImageUrl = document.getString("profileImageUrl");

                                viewerProfileName.setText(name);

                                if (profileImageUrl != null) {
                                    Glide.with(ViewerProfile.this).load(profileImageUrl).into(viewerProfileImage);
                                } else {
                                    viewerProfileImage.setImageResource(R.drawable.default_profile_image);
                                }
                            }
                        } else {
                            Toast.makeText(ViewerProfile.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}