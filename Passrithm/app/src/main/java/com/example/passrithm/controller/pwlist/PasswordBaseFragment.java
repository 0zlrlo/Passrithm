package com.example.passrithm.controller.pwlist;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

import com.google.firebase.database.DatabaseReference;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PasswordBaseFragment extends Fragment {
    private TextView exportButton;
    private TextView lockText;
    private MainActivity mainActivity;
    private ImageView lockButton;

    //private AlertDialog pinInputDialog;
    private View view;
    private DatabaseReference mDatabaseRef;


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

        exportButton = view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainActivity.FragmentChange("export");
                Intent intent = new Intent(getActivity(), ExportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }

}