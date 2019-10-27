package org.rebuildkerala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.rebuildkerala.R;
import org.rebuildkerala.data.model.Details;

import java.util.List;

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.MyViewHolder> {

    private List<Details> detailsList;
    private Context context;

    public DetailsRecyclerAdapter(List<Details> detailsList, Context context) {
        this.detailsList = detailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_details_recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleTextView.setText(detailsList.get(position).getTitle());
        holder.descriptionTextView.setText(detailsList.get(position).getDescription());
        holder.rightTextView.setText(detailsList.get(position).getRightText());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, descriptionTextView, rightTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.detailsLayoutTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.detailsLayoutTitleTextView);
            rightTextView = itemView.findViewById(R.id.detailsLayoutRightTextView);
        }
    }
}
