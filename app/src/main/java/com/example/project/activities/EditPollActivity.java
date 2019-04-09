package com.example.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.viewAdapters.QuestionAdapter;
import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.models.Question;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Allows the user to edit or create a poll.
 */
public class EditPollActivity extends AppCompatActivity {
    static final int EDIT_QUESTION_REQUEST = 1;

    /**
     * A reference to a poll object. Public for testing purposes.
     */
    public static Poll poll;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseFirestore database;
    private EditText pollTitle;

    /**
     * Contains the data stored in the recycler view (Question objects)
     */
    private ArrayList<Question> dataSet = new ArrayList<>();

    /**
     * Sets up our buttons and listeners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poll);
        database = FirebaseFirestore.getInstance();

        Button addNewItemButton = findViewById(R.id.addNewQuestionButton);
        Button removeItemButton = findViewById(R.id.removeQuestionButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        pollTitle = findViewById(R.id.pollName);

        // Call some setup methods.
        populateFromPoll();
        configureRecyclerView();

        addNewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonHandler();
            }
        });
        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeButtonHandler();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonHandler();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelButtonHandler();
            }
        });
    }

    /**
     * Sets up our recycler view using our custom QuestionAdapter for display.
     */
    private void configureRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new QuestionAdapter(dataSet){
            // We override this here because we need access to the application context in order
            // for the edit button to operate correctly.
            @Override
            public void onBindViewHolder(@NonNull QuestionViewHolder holder, final int i){
                holder.text.setText(getFromDataset(i).title);
                // The edit button should go to the edit question activity
                holder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =
                                new Intent(getApplicationContext(), EditQuestionActivity.class);
                        intent.putExtra("question", getFromDataset(i));
                        intent.putExtra("index", i);
                        startActivityForResult(intent, EDIT_QUESTION_REQUEST);
                    }
                });
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * A setup method that populates the view with the correct data.
     */
    private void populateFromPoll(){
        poll = (Poll)getIntent().getSerializableExtra("poll");
        dataSet = poll.questions;
        pollTitle.setText(poll.title);
    }

    /**
     * Adds a new item to the recycler view.
     */
    private void addButtonHandler(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Default Option");
        Question q = Question.getNewQuestion("Default Title", options);

        QuestionAdapter adapter = (QuestionAdapter)recyclerView.getAdapter();
        if(adapter !=null){
            dataSet.add(q);
            adapter.notifyItemInserted(dataSet.size() - 1);
        }
    }

    /**
     * Removes the last item from the recycler view.
     */
    private void removeButtonHandler(){
        QuestionAdapter adapter = (QuestionAdapter)recyclerView.getAdapter();
        if(adapter != null){
            if(dataSet.size() != 0) {
                dataSet.remove(dataSet.size() - 1);
                adapter.notifyItemRemoved(dataSet.size());
            }
        }
    }

    /**
     * Saves the modified questions and title to the poll object to the FireStore
     * and finishes the activity
     */
    private void saveButtonHandler(){
        poll.questions = dataSet;
        poll.title = pollTitle.getText().toString();

        // If this Poll has no ID then we must be creating a new object here.
        if(poll.id == null) {
            DocumentReference docRef = database.collection("polls").document();
            poll.id = docRef.getId();
            docRef.set(poll);
        }
        else{
            database.collection("polls").document(poll.id).set(poll);
        }
        finish();
    }

    /**
     * Finishes the activity without saving anything.
     */
    private void cancelButtonHandler(){
        finish();
    }

    /**
     * This allows us to retrieve results from our edit question activity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == EDIT_QUESTION_REQUEST && resultCode == Activity.RESULT_OK){
            Question q = (Question)data.getSerializableExtra("question");
            int i = (Integer)data.getSerializableExtra("index");
            if(q != null){
                try{
                    dataSet.set(i, q);
                    mAdapter.notifyItemChanged(i);
                }
                catch(IndexOutOfBoundsException e){
                    Log.d("DBG", "Could not set question at specified index.");
                }
            }
        }
    }
}
