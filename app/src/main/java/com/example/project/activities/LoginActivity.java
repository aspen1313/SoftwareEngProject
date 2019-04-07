package com.example.project.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.managers.UserManager;
import com.example.project.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
        db = FirebaseFirestore.getInstance();

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
        String username = usernameText.getText().toString();
        db.collection("users").whereEqualTo("username", username).get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();
                    if(!querySnapshot.isEmpty()){
                        DocumentSnapshot docSnapshot = querySnapshot.getDocuments().get(0);
                        loginCallback(docSnapshot.toObject(User.class));
                    }
                }
            }
        });
    }
    private void loginCallback(User user){
        UserManager.login(user);
    }

    /**
     * Handles the register login for our register button
     */
    private void registerButtonHandler(){
        final String username = usernameText.getText().toString();
        // TODO Fix all registering users being admins.
        final boolean isAdmin = true;

        db.collection("users").whereEqualTo("username", username).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.isSuccessful()){
                            registerButtonCallback(username, isAdmin);
                        }
                    }
                });
        DocumentReference ref = db.collection("users").document();
        ref.getId();
    }
    private void registerButtonCallback(String username, boolean isAdmin){
        DocumentReference ref = db.collection("users").document();
        String id = ref.getId();
        User user = new User(id, username, isAdmin);
        ref.set(user);
        UserManager.login(user);
        finish();
    }

    /**
     * Handles the logout logic for our logout button
     */
    private void logoutButtonHandler(){

    }
}
