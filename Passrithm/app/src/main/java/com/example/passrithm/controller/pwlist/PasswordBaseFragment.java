package com.example.passrithm.controller.pwlist;


import android.annotation.SuppressLint;
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
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.passrithm.R;
import com.example.passrithm.controller.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.MainActivity;

import com.example.passrithm.controller.algorithmmaker.PostPassword;
import com.example.passrithm.controller.algorithmmaker.PostSelectedBox;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
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
    private AlertDialog pwShareDialog, pwAcceptDialog;
    String name;


  /*  public static PasswordBaseFragment newInstance(String acccept){
        PasswordBaseFragment pbf= new PasswordBaseFragment();
        Bundle args = new Bundle();
        args.putString("accept",acccept);
        pbf.setArguments(args);
        return pbf;
    }*/








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
        //accept dialog 부분
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
                Log.d("value",result);
                // Do something with the result...
            }
        });


        waitDialog();



        //exprot 부분
        exportButton = view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("passwordBox", (Serializable) passwordBoxes);
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
                showDialog(position);
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
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
        FirebaseAuth user = FirebaseAuth.getInstance();

        TextView shareButton = dialogView.findViewById(R.id.dialog_ok_btn);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = dialogView.findViewById(R.id.dialog_email_input_et);
                String shareEmail = editText.getText().toString();
                PasswordBox shareBox = PasswordBoxRVAdapter.getItem(position);
                String userEmail = emailSplit(shareEmail);
                SharePassword sharePassword = new SharePassword(shareBox.getPassword(),shareBox.getDomain(),userEmail);
                mDatabase.child("Passrithm").child("SharePassword").child(userEmail).setValue(sharePassword);
            }
        });
        pwShareDialog.show();
    }

    void showAcceptDialog(String domain, String password, String checkEmail){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View dialogView = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_password_accept, null);
        builder.setView(dialogView)
                .setCancelable(true);
        pwAcceptDialog = builder.create();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView acceptBtn=dialogView.findViewById(R.id.dialog_accept_btn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordBox passwordBox = new PasswordBox(domain,password);
                passwordBoxes.add(passwordBox);
                passwordBoxRVAdapter.notifyDataSetChanged();
                saveAlgorithm(passwordBox);
                pwAcceptDialog.dismiss();
                deletePassword(checkEmail);
            }
        });
        TextView rejectBtn=dialogView.findViewById(R.id.dialog_reject_btn);
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwAcceptDialog.dismiss();
                deletePassword(checkEmail);
            }
        });

        pwAcceptDialog.show();
    }

    void waitDialog(){
        // newInstance("accept");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

        final String[] userEmail = new String[2];

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//

                Log.d("value", snapshot.getValue(String.class));
                String email = snapshot.getValue(String.class);
                //String checkEmail = snapshot.getValue(String.class);
                String[] array=null;
                if(email != null){
                    array = email.split("@");
                    Log.d("value",array[0]);
                    userEmail[0] = array[0];
                    // databaseReference.child("Passrithm").child("SharePassword").child(userEmail[0]).addValueEventListener(mValueEventListener);
                    ValueEventListener mValueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String domain = snapshot.child("domain").getValue(String.class);
                            String password = snapshot.child("password").getValue(String.class);
                            String checkEmail = snapshot.child("email").getValue(String.class);
                            if(userEmail[0].equals(checkEmail)) {
                                showAcceptDialog(domain, password, checkEmail);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    databaseReference.child("Passrithm").child("SharePassword").child(userEmail[0]).addValueEventListener(mValueEventListener);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child("Passrithm").child("UserAccount").child(user1.getUid()).child("emailId").addValueEventListener(valueEventListener);



    }
    private void saveAlgorithm(PasswordBox passwordBox) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase.child("Passrithm").child("UserAccount").child(user.getUid()).child("passwordList").push().setValue(passwordBox);
    }

    private void deletePassword(String email){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Passrithm").child("SharePassword").child(email).removeValue();
    }

    private String emailSplit(String shareEmail) {
        String[] userEmail = new String[1];
        String[] array = null;

        if (shareEmail != null) {
            array = shareEmail.split("@");
            Log.d("value", array[0]);
            userEmail[0] = array[0];
        }
        return userEmail[0];
    }


}