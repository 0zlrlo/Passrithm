package com.example.passrithm.controller.algoritmlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.AlgorithmGeneratorActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmListFragment extends Fragment {
    private DatabaseReference databaseReference;
    ImageView plusButton;
    ViewGroup rootView;
    List<Object> test = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_algorithm_list, container, false);
        variableInitialization();

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AlgorithmGeneratorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                test.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Object testData = dataSnapshot.getValue(Object.class);
//                    test.add(testData);
//                }
                Log.w("MainActivity", "treeList = " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", "onCancelled");
            }
        });

        return rootView;
    }

    private void variableInitialization() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Algorithm list");
        plusButton = rootView.findViewById(R.id.algorithm_list_plus_iv);
    }
}
