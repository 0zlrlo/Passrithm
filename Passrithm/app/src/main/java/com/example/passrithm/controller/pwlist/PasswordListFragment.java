package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.passrithm.R;
import com.example.passrithm.controller.algoritmlist.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.base.LoginActivity;
import com.example.passrithm.databinding.FragmentAlgorithmListBinding;
public class PasswordListFragment extends Fragment {
    TextView exportButton;
    PasswordShowActivity passwordShowActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        passwordShowActivity = (PasswordShowActivity) getActivity();
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_password_list, container, false);
        exportButton = rootView.findViewById(R.id.export);

        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordShowActivity.FragmentChange("export");
            }
        });return rootView;
    }
}


