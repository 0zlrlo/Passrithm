package com.example.passrithm.controller.algoritmlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;

public class PasswordRevisionFragment extends Fragment {
    TextView saveButton;

    AlgorithmGeneratorActivity algorithmGeneratorActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        algorithmGeneratorActivity = (AlgorithmGeneratorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_password_revision , container, false);
        saveButton = rootView.findViewById(R.id.last_revision_save_tv);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithmGeneratorActivity.finish();
            }
        });
        return rootView;
    }
}
