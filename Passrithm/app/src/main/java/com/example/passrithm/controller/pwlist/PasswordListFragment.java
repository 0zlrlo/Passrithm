package com.example.passrithm.controller.pwlist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PasswordListFragment extends Fragment {
    private List<PasswordBox> passwordBoxes;
    private MainActivity mainActivity;
    private AlertDialog pwShareDialog;
    private String shareEmail;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle saveInstanceState){
        View view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);
        passwordBoxes = new ArrayList<>();
        PasswordBox example= new PasswordBox("비밀번호");
        passwordBoxes.add(example);
        PasswordBoxRVAdapter passwordBoxRVAdapter = new PasswordBoxRVAdapter(requireContext(), passwordBoxes, new PasswordBoxRVAdapter.OnItemClickListener(){
            @Override
            public void OnItemClick(int position) {
                showDialog();
            }
        });
        RecyclerView passwordBox = view.findViewById(R.id.password_list_rc);
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
