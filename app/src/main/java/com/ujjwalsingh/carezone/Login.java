package com.ujjwalsingh.carezone;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
        EditText email;
        Button login;
        FirebaseAuth mAuth;
    EditText pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        pas = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String emailText = email.getText().toString();
            String pasText = pas.getText().toString();
            login(emailText,pasText);
        }
    });


    }

    public void login(String email, String pas){
        mAuth.signInWithEmailAndPassword(email, pas)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(Login.this, DashBoard.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
