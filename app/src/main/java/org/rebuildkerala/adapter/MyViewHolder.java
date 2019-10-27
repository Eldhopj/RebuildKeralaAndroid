package org.rebuildkerala.adapter;

import androidx.recyclerview.widget.RecyclerView;

import org.rebuildkerala.databinding.LayoutDetailsRecyclerViewBinding;

class MyViewHolder extends RecyclerView.ViewHolder {
    private LayoutDetailsRecyclerViewBinding binding;
    public MyViewHolder(LayoutDetailsRecyclerViewBinding binding) {
        super(binding.getRoot());
        this.binding =binding;
    }

    public LayoutDetailsRecyclerViewBinding getBinding() {
        return binding;
    }
}
