package com.example.passrithm.controller.pwlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.passrithm.R;
import com.example.passrithm.controller.algoritmlist.AlgorithmMakeFragment;
import com.example.passrithm.controller.algoritmlist.PasswordRevisionFragment;
import com.example.passrithm.databinding.ActivityPasswordShowBinding;


public class PasswordShowActivity extends AppCompatActivity {
    private ActivityPasswordShowBinding binding;
//    private FragmentManager fragmentManager;

     FragmentTransaction transaction;
//


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        binding = ActivityPasswordShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void FragmentChange(String fragment){
        transaction = getSupportFragmentManager().beginTransaction();

        ExportFragment exportFragment = new ExportFragment();

        if(fragment.equals("export")){
            transaction.replace(R.id.password_list_main_frm, exportFragment);

        }
        transaction.commit();
    }


}



