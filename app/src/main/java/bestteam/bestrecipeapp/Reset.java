package bestteam.bestrecipeapp;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Reset extends AppCompatActivity
{

    private Button buttonPwsReset;
    private EditText editTextPwsResetEmail;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);

        editTextPwsResetEmail = findViewById(R.id.email_input);
        buttonPwsReset = findViewById(R.id.reset_btn);

        buttonPwsReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String email = editTextPwsResetEmail.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(Reset.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                    editTextPwsResetEmail.setError("Email is required");
                    editTextPwsResetEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(Reset.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    editTextPwsResetEmail.setError("Valid email is required");
                    editTextPwsResetEmail.requestFocus();
                }
                else {
                    resetPassword(email);
                }
                
            }
        });

    }

    private void resetPassword(String email)
    {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            Toast.makeText(Reset.this, "Please check your inbox for link",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Reset.this, MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Reset.this, "Something went wrong",
                    Toast.LENGTH_SHORT).show();
        }
    }

        });
    }
}

