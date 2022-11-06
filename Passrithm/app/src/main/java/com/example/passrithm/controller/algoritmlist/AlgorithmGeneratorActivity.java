package com.example.passrithm.controller.algoritmlist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.databinding.ActivityAlgorithmGeneratorBinding;

public class AlgorithmGeneratorActivity extends AppCompatActivity {
    private ActivityAlgorithmGeneratorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlgorithmGeneratorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFragment();
    }

    public void setFragment(){    // 데이터를 받아 나타내는 것이 아니므로 controller에 있는게 맞다고 생각함.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AlgorithmMakeFragment algorithmMakeFragment = new AlgorithmMakeFragment();

        transaction.replace(R.id.algorithm_generator_main_frm, algorithmMakeFragment);
        transaction.commit();
    }
}

