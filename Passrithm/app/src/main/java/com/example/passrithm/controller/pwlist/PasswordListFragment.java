package com.example.passrithm.controller.pwlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PasswordListFragment extends Fragment {
    private List<PasswordBox> passwordBoxes = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle saveInstanceState){
        View view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);

        PasswordBoxRVAdapter passwordBoxRVAdapter = new PasswordBoxRVAdapter(requireContext(), passwordBoxes);
        RecyclerView passwordBox = view.findViewById(R.id.password_list_rc);
        passwordBox.setAdapter(passwordBoxRVAdapter);
        return view;
    }
}
