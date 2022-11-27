package com.example.passrithm.controller.pwlist;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PasswordBaseFragment extends Fragment {
    private TextView exportButton;
    private TextView lockText;
    private MainActivity mainActivity;
    private ImageView lockButton;
    private DatabaseReference mDatabase;
    private List<PasswordBox> passwordBoxes,filteredList;
    //private AlertDialog pinInputDialog;
    private View view;
    private RecyclerView rcView;
    private EditText searchET;
    PasswordBoxRVAdapter passwordBoxRVAdapter;
    private ImageView searchBtn;
    private AlertDialog pwShareDialog;





    //private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_password_base, container, false);

        //exprot 부분
        exportButton = view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        //리사이클러뷰 부분, dialog 부착해야함
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Passrithm").child("UserAccount").child(user.getUid()).child("passwordList");
        passwordBoxes = new ArrayList<>();
        passwordBoxRVAdapter = new PasswordBoxRVAdapter(requireContext(), passwordBoxes, new PasswordBoxRVAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //showDialog(position);
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot messageData : snapshot.getChildren()) {
                    PasswordBox example = new PasswordBox(messageData.child("domain").getValue().toString(), messageData.child("password").getValue().toString());
                    passwordBoxes.add(example);
                    passwordBoxRVAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        /*RecyclerView passwordBox = view.findViewById(R.id.password_list_rc);
        //passwordBox.setLayoutManager(new LinearLayoutManager(this));
        passwordBox.setAdapter(passwordBoxRVAdapter);*/


        //검색 filter부분
        searchBtn=view.findViewById(R.id.search_img);
        filteredList=new ArrayList<>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList.clear();
                searchET = view.findViewById(R.id.search_et);
                String searchText=searchET.getText().toString();
               // Log.d("searchET",searchET.toString());
                if(searchText.equals("")){
                    passwordBoxRVAdapter.setItem(passwordBoxes);
                }else{
                for(int i =0;i<passwordBoxes.size();i++){
                    if (passwordBoxes.get(i).domain.equals(searchText)){
                        filteredList.add(passwordBoxes.get(i));

                    }
                    passwordBoxRVAdapter.setItem(filteredList);
                }
                }
            }
        });

        RecyclerView passwordBox = view.findViewById(R.id.password_list_rc);
        passwordBox.setLayoutManager(new LinearLayoutManager(getContext()));
        passwordBox.setAdapter(passwordBoxRVAdapter);


        return view;
    }

    void showDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View dialogView = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_password_share, null);
        builder.setView(dialogView)
                .setCancelable(true);
        pwShareDialog = builder.create();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Query sort = FirebaseDatabase.getInstance().getReference().child("Passrithm").child("UserAccount").orderByChild("emailId");;
        FirebaseAuth user = FirebaseAuth.getInstance();

        TextView shareButton = dialogView.findViewById(R.id.dialog_ok_btn);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = dialogView.findViewById(R.id.dialog_email_input_et);
                String shareEmail = editText.getText().toString();
                PasswordBox shareBox = PasswordBoxRVAdapter.getItem(position);
                Log.d("shareBox",shareBox.getPassword());

                /*sort.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (int i=0;i<position;i++) {

                     *//*      // String key = snapshot.child("Passrithm").child("UserAccount").push().getKey();
                          //  Firebasep=new UserAccount(allUser.getIdtoken(), allUser.getEmailId(), allUser.getPasswordId(), allUser.getNameId(), allUser.getPinId());
                            token.add(key);
                            Log.d("newkey",key);
*//*

                        }
                       *//* for(int i=0;i< token.size();i++){
                           mDatabase.child("Passrithm").child("UserAccount").child(token.get(i)).child("passwordList").
                            if (shareEmail.equals(들아가야하는값)) {
                                //receiveList(shareBox.domain,shareBox.password);
                                mDatabase.child("Passrithm").child("UserAccount").child(token.get(i)).child("passwordList").setValue(shareBox);

                            }
                        }*//*
                        pwShareDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

            }
        });

        pwShareDialog.show();
    }

}