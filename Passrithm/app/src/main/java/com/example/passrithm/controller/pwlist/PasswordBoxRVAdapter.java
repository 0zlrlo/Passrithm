package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.passrithm.R;
import java.util.List;


public class PasswordBoxRVAdapter extends RecyclerView.Adapter<PasswordBoxRVAdapter.PasswordBoxViewHolder> {
    private List<PasswordBox> Boxlist;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    //private OnItemClickListener clickListener;
    public PasswordBoxRVAdapter(Context context,List<PasswordBox> list, OnItemClickListener clickListener) {
        this.Boxlist = list;
        this.clickListener=clickListener;
    }
    public class PasswordBoxViewHolder extends RecyclerView.ViewHolder{
        protected TextView domainText;
        protected ImageView shareButton;
        protected TextView passwordText;
        public PasswordBoxViewHolder(@NonNull View itemView){
            super(itemView);
            this.domainText=itemView.findViewById(R.id.password_domain_tv);
            this.passwordText=itemView.findViewById(R.id.password_tv);
            this.shareButton=itemView.findViewById(R.id.pw_share_btn);

        }
    }
    @Override
    public PasswordBoxRVAdapter.PasswordBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_password_list, parent, false);
        return new PasswordBoxViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PasswordBoxViewHolder holder, int position) {
        holder.passwordText.setText(Boxlist.get(position).password);
        holder.domainText.setText(Boxlist.get(position).domain);
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != Boxlist? Boxlist.size() : 0);
    }
}




