package com.example.passrithm.controller.pwlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.passrithm.R;
import com.example.passrithm.databinding.ActivityPasswordShowBinding;


public class PasswordShowActivity extends AppCompatActivity {
    private ActivityPasswordShowBinding binding;
//    private FragmentManager fragmentManager;
    private PasswordListFragment fragmentpwlist;
    private PasswordLockFragment fragmentpwlock;
//    private FragmentTransaction transaction;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_pw__search);
//
//        fragmentManager = getSupportFragmentManager();
//
//        fragmentpwlist = new PasswordListFragment();
//        fragmentpwlock = new PasswordLockFragment();
//
//        transaction = fragmentManager.beginTransaction();
//       // transaction.replace(R.id.linearlayout, fragmentA).commitAllowingStateLoss();
//    }
//
//    public void setFragment(String fragment)
//    {
//        transaction = getSupportFragmentManager().beginTransaction();
//        PasswordListFragment passwordListFragment = new PasswordListFragment();
//        PasswordLockFragment passwordLockFragment = new PasswordLockFragment();
//
//        switch(fragment)
//        {
//            case "passwordList":
//                transaction.replace(R.id.password_list_main_frm, passwordListFragment);
//                break;
//            case "passwordLock":
//                transaction.replace(R.id.password_lock_main_frm, passwordLockFragment);
//                break;
//        }transaction.commit();
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        binding = ActivityPasswordShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentpwlist = new PasswordListFragment();
        fragmentpwlock = new PasswordLockFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.password_list_main_frm, fragmentpwlist);
        fragmentTransaction.commit();
    }
}


