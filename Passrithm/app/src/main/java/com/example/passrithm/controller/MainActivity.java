package com.example.passrithm.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;
import com.example.passrithm.controller.pwlist.PasswordLockFragment;
import com.example.passrithm.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_password, R.id.navigation_algorithm, R.id.navigation_mypage)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        Intent intent = getIntent();
        String myData = intent.getStringExtra("setting");
       // Log.d("checking",myData);
        if(myData!=null){
            FragmentChange(myData);
            Log.d("checking",myData);
        }else FragmentChange("password_lock");
       // FragmentChange("password_list");
        SharedPreferences spf = getSharedPreferences("Login", Activity.MODE_PRIVATE);
        Log.d("login", spf.getString("loginEmail", ""));
        Log.d("login", spf.getString("loginPw", ""));

    }

    FragmentTransaction transaction;
    public void FragmentChange(String fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        final RecyclerView rcView = (RecyclerView)findViewById(R.id.password_list_rc);



        PasswordLockFragment passwordLockFragment = new PasswordLockFragment();

        switch(fragment){
            case "password_lock":
                transaction.replace(R.id.password_list_base_frm,passwordLockFragment);
                rcView.setVisibility(View.GONE);


                break;
            case "password_list":
                transaction.hide(passwordLockFragment);
                rcView.setVisibility(View.VISIBLE);


                break;

        }
        transaction.commit();
    }
}
