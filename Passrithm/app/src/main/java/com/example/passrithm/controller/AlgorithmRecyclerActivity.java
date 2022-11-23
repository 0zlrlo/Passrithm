package com.example.passrithm.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.controller.algorithmmaker.AlgorithmMakeFragment;
import com.example.passrithm.controller.algorithmmaker.AlgorithmRemakeFragment;
import com.example.passrithm.controller.algorithmmaker.PasswordRevisionFragment;
import com.example.passrithm.controller.algorithmmaker.RecyclePasswordRevisionFragment;
import com.example.passrithm.controller.algorithmmaker.SelectedBox;
import com.example.passrithm.databinding.ActivityAlgorithmGeneratorBinding;
import com.example.passrithm.databinding.ActivityAlgorithmRecyclerBinding;

import java.util.List;

public class AlgorithmRecyclerActivity extends AppCompatActivity {
    private ActivityAlgorithmRecyclerBinding binding;
    private String key;
    private List<SelectedBox> selectedBoxes;
    public String result;
    public AlgorithmRemakeFragment algorithmRemakeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlgorithmRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        selectedBoxes = (List<SelectedBox>) intent.getSerializableExtra("selectedBoxes");

        setMakerFragment(selectedBoxes);
    }

    public void setMakerFragment(List<SelectedBox> selectedBoxes) {
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
        algorithmRemakeFragment = new AlgorithmRemakeFragment(selectedBoxes);

        transaction.replace(R.id.algorithm_recycler_main_frm, algorithmRemakeFragment);
        transaction.commit();
    }

    public void revisionPasswordFragment() {
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
        RecyclePasswordRevisionFragment recyclePasswordRevisionFragment = new RecyclePasswordRevisionFragment(key);

        transaction.replace(R.id.algorithm_recycler_main_frm, recyclePasswordRevisionFragment);
        transaction.commit();
    }

    public List<SelectedBox> getSelectedBoxes() {
        return selectedBoxes;
    }
}
