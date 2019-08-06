package com.example.agright;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TimelineAdapter extends FirestoreRecyclerAdapter<TimelineX, TimelineAdapter.TimelineHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TimelineAdapter(@NonNull FirestoreRecyclerOptions<TimelineX> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TimelineHolder holder, int position, @NonNull TimelineX model) {
        holder.date.setText(model.getDate());
        holder.title.setText(model.getTitle());
        holder.summary.setText(model.getSummary());
    }

    @NonNull
    @Override
    public TimelineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timelinervlay,
                viewGroup,false);
        return new TimelineHolder(view);
    }

    class TimelineHolder extends RecyclerView.ViewHolder {
        TextView date, title, summary;
        public TimelineHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.datervtv);
            title = itemView.findViewById(R.id.titlervtv);
            summary = itemView.findViewById(R.id.subtitlervtv);
        }
    }
}

