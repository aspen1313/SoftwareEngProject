package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.activities.LoginActivity;
import com.example.project.managers.UserManager;
import com.example.project.models.User;

public class MainActivity extends AppCompatActivity {
    /**
     * Leads the user to the login activity.
     */
    private Button loginButton;

    /**
     * Allows the user to continue on to the correct page for their role.
     */
    private Button continueButton;

    /**
     * Shows the user who the current logged-in user is.
     */
    private TextView currentUser;

    /**
     * Captures the views that we need and adds handlers to the buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonHandler();
            }
        });

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueButtonHandler();
            }
        });

        currentUser = findViewById(R.id.currentUserText);
        populateCurrentUser();
    }

    /**
     * Repopulates the current user field so that the user has feedback on who is logged in if they
     * came back from the login page.
     */
    @Override
    protected void onResume(){
        super.onResume();
        populateCurrentUser();
    }

    /**
     * Sets the appropriate value for currentUser.
     */
    private void populateCurrentUser(){
        if(UserManager.getUser() != null){
            currentUser.setText("Current User: "+ UserManager.getUser());
        }
        else{
            currentUser.setText("NO USER LOGGED IN");
        }
    }

    /**
     * Brings the user to the login activity
     */
    private void loginButtonHandler(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to the admin page if the current user is an admin, the student page if they're not.
     * If no user is logged in, writes an error to the view.
     */
    private void continueButtonHandler(){
        User u = UserManager.getUser();
        if(u == null){
            currentUser.setText("ERROR: Not logged in.");
        }
        else if(u.isAdmin()){
            Intent intent = new Intent(getApplicationContext(), AdminPage.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getApplicationContext(), StudentPage.class);
            startActivity(intent);
        }
    }
}
