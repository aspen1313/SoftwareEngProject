package com.example.project.viewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

public class OptionResultsAdapter extends RecyclerView.Adapter<OptionResultsAdapter.OptionViewHolder> {
    private ArrayList<String> mDataset;

    public class OptionViewHolder extends RecyclerView.ViewHolder{
        public TextView optionName;
        public TextView nVotes;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionName = itemView.findViewById(R.id.optionName);
            nVotes = itemView.findViewById(R.id.nVotes);
        }
    }

    public OptionResultsAdapter(ArrayList<String> dataset){
        mDataset = dataset;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.options_results_view_holder, viewGroup, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
