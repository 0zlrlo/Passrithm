package com.example.passrithm.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.controller.algorithmmaker.AlgorithmMakeFragment;
import com.example.passrithm.controller.algorithmmaker.PasswordRevisionFragment;
import com.example.passrithm.databinding.ActivityAlgorithmGeneratorBinding;

public class AlgorithmRecyclerActivity extends AppCompatActivity {
    private ActivityAlgorithmGeneratorBinding binding;
    public String result = "";
    FragmentTransaction transaction;
    public AlgorithmMakeFragment algorithmMakeFragment = new AlgorithmMakeFragment();
    public PasswordRevisionFragment passwordRevisionFragment = new PasswordRevisionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlgorithmGeneratorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFragment("algorithmMake");
    }

    public void setFragment(String fragment){
        transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment){
            case "algorithmMake" :
                transaction.replace(R.id.algorithm_generator_main_frm, algorithmMakeFragment);
                break;
            case "passwordRevision" :
                transaction.replace(R.id.algorithm_generator_main_frm, passwordRevisionFragment);
                transaction.addToBackStack("passwordRevision"); // stack에 저장해서 뒤로 갔을때 다시 메이커 화면으로 갈 수 있도록 함.
                break;
        }
        transaction.commit();
    }
}
