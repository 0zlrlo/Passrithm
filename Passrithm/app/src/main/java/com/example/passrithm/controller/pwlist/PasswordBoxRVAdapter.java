package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.passrithm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PasswordBoxRVAdapter extends RecyclerView.Adapter<PasswordBoxRVAdapter.PasswordBoxViewHolder> implements ItemTouchHelperCb.ItemTouchHelperListener {
    private static List<PasswordBox> Boxlist;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public PasswordBoxRVAdapter(Context requireContext, List<PasswordBox> passwordBoxes) {
        this.Boxlist = passwordBoxes;
    }

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
        holder.passwordText.setText(Boxlist.get(position).getPassword());
        holder.domainText.setText(Boxlist.get(position).getDomain());
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

    public static PasswordBox getItem(int position) {
        return Boxlist.get(position); // ????????? ????????????
    }

    public void setItem(List<PasswordBox> list){
        Boxlist=list;
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        PasswordBox passwordBox = Boxlist.get(from_position); // ????????? ?????? ??????
        Boxlist.remove(from_position); // ????????? ?????? ??????
       Boxlist.add(to_position , passwordBox); // ???????????? ?????? position ??? ??????
        notifyItemMoved(from_position,to_position); //Adapter??? ????????? ????????????
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        /*onItemSwipe*/
        //???????????? ??????????????? ?????? ?????? ???????????? Swipe?????? ????????? ??????
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        PasswordBox currentBox = Boxlist.get(position);
        List<String> keyList= new ArrayList<>();
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keyList= new ArrayList<>();

                for(DataSnapshot ds : snapshot.getChildren()){
                    String key = ds.getKey();
                    Log.d("key", key);
                    keyList.add(key);
                }

                removePosition(position, keyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabase.child("Passrithm").child("UserAccount").child(user.getUid()).child("passwordList").addValueEventListener(mValueEventListener);
    }

    private void removePosition(int position, List<String> keyList) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Passrithm").child("UserAccount").child(user.getUid()).child("passwordList").child(keyList.get(position)).removeValue();
        Boxlist.remove(position); // ???????????? ??? ?????? ??????
        notifyItemRemoved(position); //Adapter??? ????????? ????????????
    }
}





