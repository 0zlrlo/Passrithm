package com.example.passrithm.controller.pwlist;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.passrithm.R;
import com.example.passrithm.controller.algoritmlist.AlgorithmGeneratorActivity;
import com.example.passrithm.databinding.FragmentAlgorithmListBinding;
public class PasswordListFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_list, container, false);
    }
    }


