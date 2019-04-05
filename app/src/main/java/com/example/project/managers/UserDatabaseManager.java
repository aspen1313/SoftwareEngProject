package com.example.project.managers;

import android.support.annotation.NonNull;

import com.example.project.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

/**
 * This class allows us to abstract away our exact user database implementation from the usage.
 */
public class UserDatabaseManager {

    /**
     * We employ a singleton pattern to ensure we don't have references to the FireStore everywhere.
     */
    private static FirebaseFirestore dbInstance;
    private static HashMap<String, User> defaultStore;
    public static User user;

    /**
     * This method will set a FireStore database to be our backend store. If this is not set
     * then we'll default to a local RAM store for testing purposes.
     * @param db
     */
    public static void initialize(FirebaseFirestore db){
        dbInstance = db;
    }

    /**
     * Initializes the UserDatabaseManager with a default data store.
     */
    public static void initialize(){
        defaultStore = new HashMap<>();
    }

    /**
     * Allows us to add users to the database or to the test data store
     * @param collectionPath
     * @param u
     * @return
     */
    public static void add(String collectionPath, User u){
        if(dbInstance != null){
            DocumentReference docRef = dbInstance.collection(collectionPath).document();
            u.id = docRef.getId();
            docRef.set(u);
        }
        else{
            defaultStore.put(u.getUsername(), u);
        }
    }

    /**
     * Allows us to remove items from the FireStore or test data store.
     * @param collectionPath
     * @param id
     * @return
     */
    public static void remove(String collectionPath, String id){
        if(dbInstance != null){
            dbInstance.collection(collectionPath).document(id).delete();
        }
        else{
            defaultStore.remove(id);
        }
    }

    /**
     * Returns the document in the collectionPath with FireStoreID id. (Or null if it cannot find it).
     * @param collectionPath
     * @param id
     * @return
     */
    private static void tryGetObject(String collectionPath, String id){
        if(dbInstance != null){
            dbInstance.collection(collectionPath).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            user = document.toObject(User.class);
                        }
                    }
                }
            });
        }
        else{
            user = defaultStore.get(id);
        }
    }
}
