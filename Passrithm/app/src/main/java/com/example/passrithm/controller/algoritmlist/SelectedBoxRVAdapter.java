package com.example.passrithm.controller.algoritmlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;

import org.w3c.dom.Text;

import java.util.List;

public class SelectedBoxRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperCallback.ItemTouchHelperListener {

    private List<SelectedBox> BoxList;

    public SelectedBoxRVAdapter(Context requireContext, List<SelectedBox> selectedBoxes) {
        this.BoxList = selectedBoxes;
    }

    @Override
    public boolean onItemMove(int form_position, int to_position) {
        SelectedBox item = BoxList.get(form_position);
        BoxList.remove(form_position);
        BoxList.add(to_position, item);
//        item.setNumber(to_position);
        notifyItemMoved(form_position, to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        BoxList.remove(position);
        notifyItemRemoved(position);
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

    public class ForTopViewHolder extends RecyclerView.ViewHolder {
        TextView repeatCount;
        public ForTopViewHolder(@NonNull View itemView) {
            super(itemView);
            this.repeatCount = (TextView) itemView.findViewById(R.id.for_top_box_repeat_tv);
        }
    }

    public class ForBottomViewHolder extends RecyclerView.ViewHolder {
        public ForBottomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return BoxList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == Code.ViewType.FOR_TOP_CONTENT)
        {
            view = inflater.inflate(R.layout.item_for_box_top, parent, false);
            return new ForTopViewHolder(view);
        }
        else if(viewType == Code.ViewType.FOR_BOTTOM_CONTENT)
        {
            view = inflater.inflate(R.layout.item_for_box_bottom, parent, false);
            return new ForBottomViewHolder(view);
        }
        else
        {
            view = inflater.inflate(R.layout.item_chosen_algorithm, parent, false);
            return new SelectedBoxViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ForTopViewHolder) {
            ((ForTopViewHolder) holder).repeatCount.setText(BoxList.get(position).inputData);
        } else if(holder instanceof ForBottomViewHolder) {
        }
        else
        {
            ((SelectedBoxViewHolder) holder).name.setText(BoxList.get(position).name);
            ((SelectedBoxViewHolder) holder).inputData.setText(BoxList.get(position).inputData);
        }
    }

    public List<SelectedBox> getBoxList() {
        return BoxList;
    }

    @Override
    public int getItemCount() {
        return (null != BoxList ? BoxList.size() : 0);
    }
}
