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
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.models.Question;
import com.example.project.viewAdapters.QuestionResultsAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Allows the user to look at the questions in a specific poll.
 */
public class VoteQuestionActivity extends AppCompatActivity {
    static final int EDIT_QUESTION_REQUEST = 1;
    // Views
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private RecyclerView.Adapter mAdapter;
    private TextView titleView;
    private Button backButton, submitButton;

    // Data
    private Poll poll;
    private ArrayList<Question> dataSet;

    /**
     * Sets up our buttons and listeners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_view);
        database = FirebaseFirestore.getInstance();

        findAllViews();
        populateViewsWithData();
        configureRecyclerView();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonHandler();
            }
        });
    }

    /**
     * This is where all views are found and assigned
     */
    private void findAllViews(){
        backButton = findViewById(R.id.backButton);
        titleView = findViewById(R.id.pollTitle);
        recyclerView = findViewById(R.id.resultsRecycler);
        submitButton = findViewById(R.id.submitButton);
    }

    /**
     * A setup method that populates the view with the correct data.
     */
    private void populateViewsWithData(){
        setTitle("Vote Page");
        Intent intent = getIntent();
        poll = (Poll)intent.getSerializableExtra("poll");
        titleView.setText(poll.title);
        dataSet = poll.questions;
    }

    /**
     * Creates and overrides an adapter and sets up the recycler view with the correct params.
     */
    private void configureRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new QuestionResultsAdapter(dataSet){
            // We override this here because we need access to the application context in order
            // for the details button to operate correctly.
            @Override
            public void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int i){
                holder.setQuestion(getFromDataset(i));
                holder.mostPopular.setText(holder.getQuestion().getMostPopularOption());
                holder.title.setText(holder.getQuestion().title);
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =
                                new Intent(getApplicationContext(), activity_poll_question.class);
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
     * Saves the voted-on questions and title to the poll object to the FireStore
     * and finishes the activity.
     */
    private void submitButtonHandler(){
        poll.questions = dataSet;
        poll.title = titleView.getText().toString();

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
            int voteIndex = (Integer)data.getSerializableExtra("voteIndex");
            if(q != null){
                try{
                    q.vote(voteIndex);
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