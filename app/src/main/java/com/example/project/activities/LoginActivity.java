package com.example.project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.managers.UserDatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Allows users to log in to the app.
 */
public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    private Button logoutButton;

    private TextView usernameText;
    private FirebaseFirestore db;

    /**
     * Finds all our views and sets the listeners for our login / register buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Here we set the database to the serializable extra or the FirebaseFirestore instance
        // depending on whether or not a db was passed as an argument. Used as a way to test.
        Object b = getIntent().getSerializableExtra("database");

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        usernameText = findViewById(R.id.userText);
        logoutButton = findViewById(R.id.logoutButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonHandler();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButtonHandler();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutButtonHandler();
            }
        });
    }

    /**
     * Handles the login logic for our login button
     */
    private void loginButtonHandler(){

    }

    /**
     * Handles the register login for our register button
     */
    private void registerButtonHandler(){

    }

    /**
     * Handles the logout logic for our logout button
     */
    private void logoutButtonHandler(){

    }
}
