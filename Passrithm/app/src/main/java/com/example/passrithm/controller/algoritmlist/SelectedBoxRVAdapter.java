package com.example.passrithm.controller.algoritmlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;

import java.util.List;

public class SelectedBoxRVAdapter extends RecyclerView.Adapter<SelectedBoxRVAdapter.SelectedBoxViewHolder> {

    private List<SelectedBox> BoxList;

    public SelectedBoxRVAdapter(Context requireContext, List<SelectedBox> selectedBoxes) {
        this.BoxList = selectedBoxes;
    }

    public class SelectedBoxViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView inputData;

        public SelectedBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.chosen_algorithm_name_tv);
            this.inputData = (TextView) itemView.findViewById(R.id.chosen_algorithm_example_tv);
        }
    }

    @NonNull
    @Override
    public SelectedBoxRVAdapter.SelectedBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chosen_algorithm, parent, false);

        return new SelectedBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedBoxRVAdapter.SelectedBoxViewHolder holder, int position) {
        holder.name.setText(BoxList.get(position).name);
        holder.inputData.setText(BoxList.get(position).inputData);
    }

    @Override
    public int getItemCount() {
        return (null != BoxList ? BoxList.size() : 0);
    }
}
