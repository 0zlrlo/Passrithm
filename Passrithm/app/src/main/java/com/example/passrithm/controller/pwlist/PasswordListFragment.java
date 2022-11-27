package com.example.passrithm.controller.pwlist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PasswordListFragment extends Fragment {
    private List<PasswordBox> passwordBoxes,receiveBoxes;
    private MainActivity mainActivity;
    private AlertDialog pwShareDialog;
    private String shareEmail;
    private DatabaseReference mDatabase;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }
// <<<<<<< jieun1
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);
// =======


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle saveInstanceState){
        View view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance().getReference("Passrithm").child("UserAccount").child(user.getUid()).child("passwordList");
        passwordBoxes = new ArrayList<>();
        PasswordBoxRVAdapter passwordBoxRVAdapter = new PasswordBoxRVAdapter(requireContext(), passwordBoxes, new PasswordBoxRVAdapter.OnItemClickListener(){
            @Override
            public void OnItemClick(int position) {
                showDialog();
            }
        });


mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
// >>>>>>> main

                int size = (int) snapshot.getChildrenCount();
                String pwsize = Integer.toString(size);
                Log.d("pw_size",pwsize);
                for(DataSnapshot messageData : snapshot.getChildren()){
                    PasswordBox example= new PasswordBox(messageData.child("domain").getValue().toString(),messageData.child("password").getValue().toString());
                    passwordBoxes.add(example);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RecyclerView passwordBox = view.findViewById(R.id.password_list_rc);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //passwordBox.setLayoutManager(layoutManager);
        passwordBox.setAdapter(passwordBoxRVAdapter);


        return view;
    }
    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View dialogView = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_password_share,null);
        builder.setView(dialogView)
                .setCancelable(true);
        pwShareDialog = builder.create();

        TextView shareButton=dialogView.findViewById(R.id.dialog_ok_btn);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = dialogView.findViewById(R.id.dialog_email_input_et);
                shareEmail = editText.getText().toString();
                if(!shareEmail.equals("")){
                    pwShareDialog.dismiss();
                }

            }
        });

    pwShareDialog.show();
    }
}
