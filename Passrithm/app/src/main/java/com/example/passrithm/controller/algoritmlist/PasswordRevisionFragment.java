package com.example.passrithm.controller.algoritmlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;

public class PasswordRevisionFragment extends Fragment {
    TextView saveButton;
    TextView bigLetterButton;
    TextView directRevisionButton;
    LinearLayout bigLetterBox;
    ViewGroup rootView;

    AlgorithmGeneratorActivity algorithmGeneratorActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        algorithmGeneratorActivity = (AlgorithmGeneratorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_password_revision , container, false);
        variableInitialization();
        
        bigLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBigLetterBoxVisibility(View.VISIBLE);
            }
        });
        directRevisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBigLetterBoxVisibility(View.GONE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithmGeneratorActivity.finish();
            }
        });
        return rootView;
    }

    private void setBigLetterBoxVisibility(int visibility){
        bigLetterBox.setVisibility(visibility);
    }
    private void variableInitialization(){
        saveButton = rootView.findViewById(R.id.last_revision_save_tv);
        bigLetterButton = rootView.findViewById(R.id.last_revision_big_letter_tv);
        directRevisionButton = rootView.findViewById(R.id.last_revision_modify_direct_tv);
        bigLetterBox = rootView.findViewById(R.id.last_revision_big_letter_box);
    }
}
