package com.example.passrithm.controller.pwlist;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;

public class ExportFragment extends Fragment {

    ViewGroup rootView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=(ViewGroup) inflater.inflate(R.layout.fragment_export, container, false);
        return rootView;
    }


}
