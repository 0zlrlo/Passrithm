package com.example.passrithm.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.passrithm.R;
import com.example.passrithm.controller.pwlist.ExportFragment;
import com.example.passrithm.controller.pwlist.PasswordListFragment;
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
       FragmentChange("password_lock");
    }
    FragmentTransaction transaction;
    public void FragmentChange(String fragment){
        transaction = getSupportFragmentManager().beginTransaction();

        ExportFragment exportFragment = new ExportFragment();
        PasswordLockFragment passwordLockFragment = new PasswordLockFragment();
        PasswordListFragment passwordListFragment = new PasswordListFragment();


        switch(fragment){
            case "export":
            transaction.replace(R.id.nav_host_fragment_activity_main, exportFragment);
                break;
            case "password_lock":
                transaction.replace(R.id.nav_host_fragment_activity_main,passwordLockFragment);
                transaction.remove(passwordListFragment);
                break;
            case "password_list":
                transaction.replace(R.id.nav_host_fragment_activity_main,passwordListFragment);
                break;

        }
        transaction.commit();
    }
}
