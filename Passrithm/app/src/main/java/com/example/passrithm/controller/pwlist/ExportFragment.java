package com.example.passrithm.controller.pwlist;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.algoritmlist.AlgorithmGeneratorActivity;

public class ExportFragment extends Fragment {

    PasswordShowActivity passwordShowActivity;
    ViewGroup rootView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=(ViewGroup) inflater.inflate(R.layout.fragment_export, container, false);
        return rootView;
    }


}
