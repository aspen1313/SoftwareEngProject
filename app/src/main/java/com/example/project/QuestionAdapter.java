package com.example.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.models.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    private ArrayList<Question> mDataset;
    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public Button editButton;

        public QuestionViewHolder(View view){
            super(view);
            text = view.findViewById(R.id.textView);
            editButton = view.findViewById(R.id.button);
        }
    }

    public QuestionAdapter(ArrayList<Question> questions){
        mDataset = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_view_holder, parent, false);

        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int i) {
        holder.text.setText(mDataset.get(i).title);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
