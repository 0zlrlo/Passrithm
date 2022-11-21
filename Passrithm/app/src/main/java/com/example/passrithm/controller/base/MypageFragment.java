package com.example.passrithm.controller.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.google.firebase.auth.FirebaseAuth;

public class MypageFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private View view;
    private String Tag="프로그먼트";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        mFirebaseAuth=FirebaseAuth.getInstance();

        Button btn_logout= view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        Log.i(Tag, "onCreateView");
        return view;
    }

}
