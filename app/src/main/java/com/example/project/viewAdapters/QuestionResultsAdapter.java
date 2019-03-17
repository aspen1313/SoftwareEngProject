package com.example.project.viewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;

import java.util.ArrayList;

public class QuestionResultsAdapter extends RecyclerView.Adapter<QuestionResultsAdapter.QuestionViewHolder> {
    private ArrayList<Question> mDataset;

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public Button detailsButton;
        public TextView mostPopular;
        private Question mQuestion;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questionName);
            detailsButton = itemView.findViewById(R.id.detailsButton);
            mostPopular = itemView.findViewById(R.id.mostPopularText);
        }

        public Question getQuestion(){
            return mQuestion;
        }

        public void setQuestion(Question question){
            mQuestion = question;
        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_results_view_holder, viewGroup, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder questionViewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public QuestionResultsAdapter(ArrayList<Question> dataSet){
        mDataset = dataSet;
    }

    public Question getFromDataset(int i){
        return mDataset.get(i);
    }
}
