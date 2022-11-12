package com.example.passrithm.controller.algoritmlist;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;

import java.util.List;

public class AlgorithmBoxRVAdapter extends RecyclerView.Adapter<AlgorithmBoxRVAdapter.AlgorithmBoxViewHolder> {

    private List<AlgorithmBox> BoxList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public AlgorithmBoxRVAdapter(Context requireContext, List<AlgorithmBox> BoxList, OnItemClickListener clickListener) {
        this.BoxList = BoxList;
        this.clickListener = clickListener;
    }

    public class AlgorithmBoxViewHolder extends RecyclerView.ViewHolder {
        protected TextView algoName;
        protected TextView algoDetail;
        protected TextView algoExample;
        protected TextView insertButton;

        public AlgorithmBoxViewHolder(View view) {
            super(view);
            this.algoName = (TextView) view.findViewById(R.id.algorithm_name_tv);
            this.algoDetail = (TextView) view.findViewById(R.id.algorithm_detail_tv);
            this.algoExample = (TextView) view.findViewById(R.id.algorithm_example_tv);
            this.insertButton = view.findViewById(R.id.algorithm_insert_bt);
        }
    }

    @NonNull
    @Override
    public AlgorithmBoxRVAdapter.AlgorithmBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_algorithm_box, parent, false);

        return new AlgorithmBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlgorithmBoxRVAdapter.AlgorithmBoxViewHolder holder, int position) {
        holder.algoName.setText(BoxList.get(position).name);
        holder.algoDetail.setText(BoxList.get(position).explanation);
        holder.algoExample.setText(BoxList.get(position).example);

        holder.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != BoxList ? BoxList.size() : 0);
    }
}
