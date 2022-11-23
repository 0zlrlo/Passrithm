package com.example.passrithm.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.passrithm.R;
import com.example.passrithm.controller.pwlist.PasswordListFragment;
import com.example.passrithm.controller.pwlist.PasswordLockFragment;
import com.example.passrithm.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public PasswordListFragment passwordListFragment = new PasswordListFragment();
    public PasswordLockFragment passwordLockFragment = new PasswordLockFragment();

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
        if(myData!=null){
            FragmentChange(myData);
        }else FragmentChange("password_lock");

    }
    FragmentTransaction transaction;
    public void FragmentChange(String fragment){
        transaction = getSupportFragmentManager().beginTransaction();


       // PasswordListFragment passwordListFragment = new PasswordListFragment();
        //PasswordLockFragment passwordLockFragment = new PasswordLockFragment();

        switch(fragment){
            case "password_lock":
                transaction.replace(R.id.password_list_base_frm,passwordLockFragment);
                break;
            case "password_list":
                transaction.replace(R.id.password_list_base_frm,passwordListFragment);
                break;

        }
        transaction.commit();
    }
}
