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

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editTextEmail = findViewById(R.id.inputTextEmail);
        EditText editTextPassword = findViewById(R.id.inputTextPassword);

        Button buttonRegister = findViewById(R.id.loginButton);

        TextView loginText = findViewById(R.id.loginText);

        // register sign up button callback
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            // this is the callback
            @Override
            public void onClick(View view) {

                // extract email and password from input text fields
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // call the actual createAccount function
                createAccount(email, password);
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, SignIn.class);

                startActivity(intent);
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
                            Toast.makeText(Register.this, user.getEmail(), Toast.LENGTH_SHORT).show();

                        } else {

                            // this toast should be replaced with whatever code we want to run when registration fails
                            Toast.makeText(
                                    Register.this,
                                    "Failed to create new account",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                });


    }
}