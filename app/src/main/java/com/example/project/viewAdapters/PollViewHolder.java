package com.example.project.viewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;

/**
 * View holder for the Poll view.
 */
public class PollViewHolder extends RecyclerView.ViewHolder{
    public TextView text;
    public Button button;
    public Button edit;
    public Button viewAdmin;
    public TextView votesTotal;

    public PollViewHolder(View view) {
        super(view);
        text = view.findViewById(R.id.pollTitleTextView);
        button = view.findViewById(R.id.pollDetailsButton);
        edit = view.findViewById(R.id.EditInfoButton);
        viewAdmin = view.findViewById(R.id.viewResultsButtonAdmin);
        votesTotal = view.findViewById(R.id.totalVotesField);

    }
}
