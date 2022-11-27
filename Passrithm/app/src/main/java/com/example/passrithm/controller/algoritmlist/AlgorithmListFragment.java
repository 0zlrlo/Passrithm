package com.example.passrithm.controller.algoritmlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;
import com.example.passrithm.controller.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.AlgorithmRecyclerActivity;
import com.example.passrithm.controller.algorithmmaker.PostAlgorithm;
import com.example.passrithm.controller.algorithmmaker.PostSelectedBox;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmListFragment extends Fragment {
    ImageView plusButton;
    ViewGroup rootView;

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

        getAlgoList();

        return rootView;
    }

    private void variableInitialization() {
        FirebaseDatabase.getInstance().getReference("Algorithm list");
        plusButton = rootView.findViewById(R.id.algorithm_list_plus_iv);
    }

    public void getAlgoList() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PostSelectedBox> algorithmList = new ArrayList<>();
                List<PostAlgorithm> postAlgorithms = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    PostSelectedBox info_each = postSnapshot.getValue(PostSelectedBox.class);
                    postAlgorithms.add(new PostAlgorithm(key, info_each));
                    algorithmList.add(info_each);
                }

                AlgorithmListRVAdapter algorithmListRVAdapter = new AlgorithmListRVAdapter(getContext(), algorithmList, new AlgorithmListRVAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(getActivity(), AlgorithmRecyclerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("key", postAlgorithms.get(position).getKey());
                        intent.putExtra("selectedBoxes", (Serializable) algorithmList.get(position).getSelectedBoxes());
                        startActivity(intent);
                    }
                });
                RecyclerView algorithmListRc = rootView.findViewById(R.id.algorithm_list_rc);
                algorithmListRc.setAdapter(algorithmListRVAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.child("Passrithm").child("UserAccount").child(user.getUid()).child("algorithmList").addValueEventListener(mValueEventListener);
    }
}
