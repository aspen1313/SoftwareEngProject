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
import com.example.project.StudentPage;
import com.example.project.models.Poll;
import com.example.project.viewAdapters.PollViewHolderStudent;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        Query query = db.collection("polls").whereEqualTo("isOpen",false);



        FirestoreRecyclerOptions<Poll> options = new FirestoreRecyclerOptions.Builder<Poll>()
                .setQuery(query, Poll.class)
                .build();

        FirestoreRecyclerAdapter adapter1 = new FirestoreRecyclerAdapter<Poll, PollViewHolderStudent>(options){
            @Override
            protected void onBindViewHolder(@NonNull PollViewHolderStudent holder, int pos, final Poll model) {

                //TODO Fix the time issue it keeps giving me the time in AST not ADT
                /*
                Calendar today = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("ADT"));
                Date open = null;
                try {
                    open = sdf.parse(model.sDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar openDate = Calendar.getInstance();
                openDate.setTime(open);

                System.out.println(today.getTime()+"\n");
                System.out.println(openDate.getTime()+"\n");

                if(today.compareTo(openDate)>0)
                    System.out.println("***************************   Its open\n\n");
                else
                    System.out.println("***************************   Its Not open\n\n");

*/

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
                        Intent intent = new Intent(getApplicationContext(), VoteQuestionActivity.class);
                        intent.putExtra("poll", model);
                        startActivity(intent);

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


    private void deleteBusiness()
    {
        //DocumentReference ref = database.collection("Canada Businesses").document(business.id);
        //ref.delete();
        //finishes the activity
        finish();

    }
}
