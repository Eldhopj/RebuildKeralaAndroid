package org.rebuildkerala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.rebuildkerala.data.model.Details;
import org.rebuildkerala.databinding.LayoutDetailsRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Details> detailsList = new ArrayList<>();
    private Context context;


    public DetailsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutDetailsRecyclerViewBinding binding = LayoutDetailsRecyclerViewBinding
                .inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder;
        if (holder instanceof MyViewHolder) {
            myViewHolder = (MyViewHolder) holder;
            setMyViewHolder(myViewHolder, position);
        }
    }

    private void setMyViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.getBinding().txtTitle.setText(detailsList.get(position).getTitle());
        myViewHolder.getBinding().txtTimeStamp.setText(detailsList.get(position).getRightText());
        myViewHolder.getBinding().txtDescription.setText(detailsList.get(position).getRightText());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public void addItemRange(List<Details> items) {
        if (items != null) {
            int position = detailsList.size();
            detailsList.addAll(position, items);
            notifyItemRangeInserted(position, items.size());
        }
    }
}
