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


public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private TextView followersView, followingView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ImageView profileImage;
    private TextView profileEmail, profileName;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profileImage = findViewById(R.id.profileImage);
        profileEmail = findViewById(R.id.profileEmail);
        profileName = findViewById(R.id.profileName);
        editProfileButton = findViewById(R.id.editProfileButton);

        followersView = findViewById(R.id.followers);
        followingView = findViewById(R.id.following);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadUserProfile();

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileButton(v);
            }
        });

    }

    // a function to make sure the profile changes show immediately

    protected void onResume() {
        super.onResume();
        loadUserProfile();
    }


    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            profileEmail.setText(user.getEmail());

            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl()).into(profileImage);
            }

            db.collection("users").document(user.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Map<String, Object> userData = document.getData();
                                    if (userData != null) {
                                        profileName.setText(userData.get("name").toString());
                                        String profileImageUrl = (String) userData.get("profileImageUrl");
                                        if (profileImageUrl != null) {
                                            Glide.with(Profile.this).load(profileImageUrl).into(profileImage);
                                        }
                                        long followersCount = document.getLong("followersCount") != null ? document.getLong("followersCount") : 0;
                                        long followingCount = document.getLong("followingCount") != null ? document.getLong("followingCount") : 0;
                                        followersView.setText("Followers: " + followersCount);
                                        followingView.setText("Following: " + followingCount);
                                    }
                                }
                            }
                        }
                    });

        }
    }



    public void editProfileButton(View view) {
        Intent i = new Intent(Profile.this, EditProfile.class);
        startActivity(i);
    }


}




