package com.example.project.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.viewAdapters.PollViewHolderStudent;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ViewPollsActivityStudent extends AppCompatActivity {

    RecyclerView pollView;
    FirestoreRecyclerAdapter adapter;
    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_polls_student);


        pollView = findViewById(R.id.studentRecycler);
        database = FirebaseFirestore.getInstance();
        adapter = setUpAdapter(database);
        setUpRecyclerView(pollView, adapter);
    }


    /**
     * Function to set up the recycler view
     * @param pollView
     * @param adapter1
     */
    private void setUpRecyclerView(RecyclerView pollView, FirestoreRecyclerAdapter adapter1) {
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getApplicationContext());
        pollView.setLayoutManager(manager1);
        pollView.setItemAnimator(new DefaultItemAnimator());
        pollView.setAdapter(adapter1);
    }

    /**
     * We use the FireStore recycler adapter to display all the polls in the FireStore.
     * @param db
     * @return
     */
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db){
        Query query = db.collection("polls").whereEqualTo("isOpen",true);



        FirestoreRecyclerOptions<Poll> options = new FirestoreRecyclerOptions.Builder<Poll>()
                .setQuery(query, Poll.class)
                .build();

        FirestoreRecyclerAdapter adapter1 = new FirestoreRecyclerAdapter<Poll, PollViewHolderStudent>(options){
            @Override
            protected void onBindViewHolder(@NonNull PollViewHolderStudent holder, int pos, final Poll model) {

                holder.titleText.setText(model.title);
                holder.viewBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), PollResultsActivity.class);
                            intent.putExtra("poll", model);
                            startActivity(intent);

                    }
                });

                holder.voteProcess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!checkDatesIfOpen(model.sDate, model.eDate)) {
                            Intent intent = new Intent(getApplicationContext(), outOfDatePage.class);
                            startActivity(intent);

                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), VoteQuestionActivity.class);
                            intent.putExtra("poll", model);
                            startActivity(intent);
                        }

                    }
                });
            }


            @Override
            public PollViewHolderStudent onCreateViewHolder(ViewGroup parent, int i){
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_poll_view_holder_student, parent, false);
                return new PollViewHolderStudent(view);
            }
        };
        return adapter1;
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

    /**
     * A method to check the dates of the poll if its open or not to allow access for students
     *
     * @param s, its the start date of the poll
     * @param e, its the end date of the poll
     * @return true if the poll is open (start date is before today's date, close date is after
     * today's date)
     */
    private boolean checkDatesIfOpen(String s, String e){
        boolean isOpen = false;
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date open = null, close = null;
        try {
            open = sdf.parse(s);
            close = sdf.parse(e);
        } catch (Exception x) {
            x.printStackTrace();
        }
        Calendar openDate = Calendar.getInstance();
        openDate.setTime(open);

        Calendar closeDate = Calendar.getInstance();
        closeDate.setTime(close);

        if(today.compareTo(openDate)>0)
            isOpen = true;

        else
            isOpen = false;

        if(today.compareTo(closeDate)>0)
            isOpen = false;

        else
            isOpen = true;

        return isOpen;
    }

}
