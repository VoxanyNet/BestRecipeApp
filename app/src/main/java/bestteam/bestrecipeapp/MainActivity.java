package bestteam.bestrecipeapp;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void signIn(String email, String password) {
        // retrieve the firebase auth instance
        // we don't need to pass mauth around, we can just call .getInstance() wherever we need it
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            // this toast should be replaced with what we want to happen when the user signs in
                            String userEmail = user.getEmail();
                            String toastString = String.format("Signed in as %s", userEmail);

                            Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            // this toast should be be replaced with what we want to happen when the user fails to sign in
                            Toast.makeText(MainActivity.this, "Failed to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void createAccount(String email, String password) {

        // retrieve the firebase auth instance
        // we don't need to pass mauth around, we can just call .getInstance() wherever we need it
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)

            // this registers a callback to run when this function completes
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                // this is the callback
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();

                        // this toast should be replaced with whatever code we want to run when registration is successful
                        Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();

                    } else {

                        // this toast should be replaced with whatever code we want to run when registration fails
                        Toast.makeText(
                        MainActivity.this,
                        "Failed to create new account",
                        Toast.LENGTH_SHORT
                        ).show();
                    }
                }

            });


    }
}