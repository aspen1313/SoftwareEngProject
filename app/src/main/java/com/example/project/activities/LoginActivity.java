package com.example.project.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private Button deleteButton;

    private CheckBox adminCheckbox;

    private TextView currentUser;
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

        db = FirebaseFirestore.getInstance();

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        logoutButton = findViewById(R.id.logoutButton);
        deleteButton = findViewById(R.id.deleteUserButton);

        usernameText = findViewById(R.id.userText);
        adminCheckbox = findViewById(R.id.adminCheckbox);
        currentUser = findViewById(R.id.currentUserText);

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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonHandler();
            }
        });

        if(UserManager.getUser() != null){
            currentUser.setText("Current User: " + UserManager.getUser().toString());
        }
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
                    else{
                        loginFailedCallback();
                    }
                }
            }
        });
    }

    /**
     * Called when login succeeds, logs in the user returned by the FireStore.
     * @param user
     */
    private void loginCallback(User user){
        UserManager.login(user);
        currentUser.setText("Current User: " + UserManager.getUser());
    }

    /**
     * Notifies the user that login failed.
     */
    private void loginFailedCallback(){
        Log.d("DBG", "Login failed.");
        currentUser.setText("ERROR: LOGIN FAILED");
    }

    /**
     * Handles the register login for our register button
     */
    private void registerButtonHandler(){
        final String username = usernameText.getText().toString();
        final boolean isAdmin = adminCheckbox.isChecked();

        db.collection("users").whereEqualTo("username", username).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot snapshot = task.getResult();
                            if(snapshot.isEmpty()){
                                registerButtonCallback(username, isAdmin);
                            }else{
                                failedRegisterCallback(username);
                            }
                        }
                    }
                });
    }

    /**
     * Handles the logic for when we can register a new user. Essentially just contacts FireStore
     * and adds the new user.
     * @param username
     * @param isAdmin
     */
    private void registerButtonCallback(String username, boolean isAdmin){
        DocumentReference ref = db.collection("users").document();
        String id = ref.getId();
        User user = new User(id, username, isAdmin);
        ref.set(user);
        UserManager.login(user);
        currentUser.setText("Current User: " + UserManager.getUser());
    }

    /**
     * Gives the user feedback that their login failed.
     */
    private void failedRegisterCallback(String username){
        Log.d("DBG", "Unable to create user with username: " + username);
        currentUser.setText("ERROR: Registration Failed");
    }

    /**
     * Handles the logout logic for our logout button.
     */
    private void logoutButtonHandler(){
        UserManager.logout();
        currentUser.setText("NO USER LOGGED IN");
    }

    /**
     * Deletes the user currently logged in.
     */
    private void deleteButtonHandler(){
        User u = UserManager.getUser();
        if(u != null){
            DocumentReference ref = db.collection("users").document(u.getId());
            ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    deleteCallback();
                }
            });
        }
    }

    /**
     * Ensures that once the data has been deleted from the FireStore, that the user is logged out
     * and that the text for which user is logged in gets updated.
     */
    private void deleteCallback(){
        UserManager.logout();
        currentUser.setText("NO USER LOGGED IN");
    }
}
