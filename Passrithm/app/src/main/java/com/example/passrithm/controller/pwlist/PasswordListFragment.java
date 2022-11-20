package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

public class PasswordListFragment extends Fragment {
TextView exportButton;
  MainActivity mainActivity;
  ImageView lockButton;
  private View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);

        exportButton=view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainActivity.FragmentChange("export");
                Intent intent = new Intent(getActivity(), ExportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        lockButton = view.findViewById(R.id.password_lock);
        lockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(lockButton.getVisibility()==view.VISIBLE){
                    lockButton.setVisibility(view.GONE);
                }
            }
        });
        return view;
    }
}


