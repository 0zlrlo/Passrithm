package com.example.passrithm.controller.pwlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

public class PasswordLockFragment extends Fragment {
    MainActivity mainActivity;
    ImageButton lockButton;
    FragmentTransaction transaction;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_password_lock, container, false);
        lockButton = rootView.findViewById(R.id.lock);
       /* dialog=new Dialog(MainActivity);
        dialog.setContentView(R.layout.dialog_pw_input);*/

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*dialog.show();
                Button button =dialog.findViewById(R.id.pin_ok);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        mainActivity.FragmentChange("password_list");
                    }
                });*/
                mainActivity.FragmentChange("password_list");


            }
        });return rootView;
    }
}
