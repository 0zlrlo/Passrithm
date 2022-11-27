package com.example.passrithm.controller.algoritmlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;
import com.example.passrithm.controller.algorithmmaker.PostSelectedBox;

import org.w3c.dom.Text;

import java.util.List;

public class AlgorithmListRVAdapter extends RecyclerView.Adapter<AlgorithmListRVAdapter.AlgorithmListViewHolder> {

    private List<PostSelectedBox> BoxList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public AlgorithmListRVAdapter(Context requireContext, List<PostSelectedBox> BoxList, OnItemClickListener clickListener) {
        this.BoxList = BoxList;
        this.clickListener = clickListener;
    }

    public class AlgorithmListViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView background;
        public AlgorithmListViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.algolist_item_tv);
            this.background = view.findViewById(R.id.algolist_item_background);
        }
    }

    @NonNull
    @Override
    public AlgorithmListRVAdapter.AlgorithmListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_algorithm_list, parent, false);

        return new AlgorithmListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlgorithmListRVAdapter.AlgorithmListViewHolder holder, int position) {
        holder.name.setText(BoxList.get(position).getName());

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("boxListSize", String.valueOf(BoxList.size()));
        return BoxList.size();
    }
}
