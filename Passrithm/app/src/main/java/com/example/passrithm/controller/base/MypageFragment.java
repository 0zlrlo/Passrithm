package com.example.passrithm.controller.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;

public class MypageFragment extends Fragment {
    private View view;
    private String Tag="프로그먼트";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(Tag, "onCreateView");
        view = inflater.inflate(R.layout.fragment_mypage, container, false);

        return view;
    }

}
