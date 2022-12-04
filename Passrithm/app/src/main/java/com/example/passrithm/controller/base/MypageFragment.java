package com.example.passrithm.controller.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.passrithm.R;
import com.google.firebase.auth.FirebaseAuth;

public class MypageFragment extends Fragment {

    FirebaseAuth mFirebaseAuth;
    private View view;
    TextView etEmail;
    TextView etId;
    TextView etPw;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        mFirebaseAuth=FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.et_mypageemail);
        etId= view.findViewById(R.id.et_mypageid);
        etPw= view.findViewById(R.id.et_mypagepassword);
        

        Button btn_logout= view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                mFirebaseAuth.signOut();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"로그아웃 되었습니다",Toast.LENGTH_SHORT).show();

                SharedPreferences pref = mContext.getSharedPreferences("Logout", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.clear();
                editor.commit();

            }
        });
        return view;
    }

}
