package com.example.project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.project.viewAdapters.PollViewHolder;
import com.example.project.R;
import com.example.project.models.Poll;
import com.google.firebase.firestore.FirebaseFirestore;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

/**
 * A very simple view for the Polls using the FirestoreRecyclerAdapter.
 */
public class ViewPollsActivity extends AppCompatActivity {
    RecyclerView pollView;
    FirestoreRecyclerAdapter adapter;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_polls);

        pollView = findViewById(R.id.pollRecyclerView);
        database = FirebaseFirestore.getInstance();
        adapter = setUpAdapter(database);
        setUpRecyclerView(pollView, adapter);
    }

    /**
     * Function to set up the recycler view
     * @param rv
     * @param adapter
     */
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    /**
     * We use the FireStore recycler adapter to display all the polls in the FireStore.
     * @param db
     * @return
     */
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db){
        Query query = db.collection("polls");

        FirestoreRecyclerOptions<Poll> options = new FirestoreRecyclerOptions.Builder<Poll>()
                .setQuery(query, Poll.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Poll, PollViewHolder>(options){
            @Override
            public void onBindViewHolder(PollViewHolder holder, int position, final Poll model){
                holder.text.setText(model.title);
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), EditPollActivity.class);
                        intent.putExtra("poll", model);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public PollViewHolder onCreateViewHolder(ViewGroup parent, int i){
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.poll_view_holder, parent, false);
                return new PollViewHolder(view);
            }
        };
        return adapter;
    }

    /**
     * We just tell the adapter to start listening
     */
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    /**
     * We just tell the adapter to stop listening
     */
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
