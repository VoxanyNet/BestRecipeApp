package bestteam.bestrecipeapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText editTextEmail = findViewById(R.id.inputTextEmail);
        EditText editTextPassword = findViewById(R.id.inputTextPassword);

        TextView registerText = findViewById(R.id.loginText);

        Button buttonSignIn = findViewById(R.id.loginButton);

        // register login button callback
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            // this is the callback
            @Override
            public void onClick(View view) {

                // extract email and password from input text fields
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // call the actual signIn function
                signIn(email, password);
            }
        });

        // open register activity when clicking on register text
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, Register.class);

                startActivity(intent);
            }
        });
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

                            Toast.makeText(SignIn.this, toastString, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            // this toast should be be replaced with what we want to happen when the user fails to sign in
                            Toast.makeText(SignIn.this, "Failed to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}