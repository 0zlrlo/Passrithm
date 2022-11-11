package com.example.passrithm.controller.algoritmlist;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

public class AlgorithmMakeFragment extends Fragment {
    TextView saveButton;
    TextView siteInput;
    AlgorithmGeneratorActivity algorithmGeneratorActivity;
    ViewGroup rootView;
    AlertDialog siteInputDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        algorithmGeneratorActivity = (AlgorithmGeneratorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_algorithm_maker, container, false);
        variableInitialization();

        setSite();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithmGeneratorActivity.setFragment("passwordRevision");
            }
        });
        return rootView;
    }

    private void setSite() { // view로 추후 이동예정. 이유 : 사이트 주소에 대한 데이터를 세팅해줘야함.
        AlertDialog.Builder builder = new AlertDialog.Builder(algorithmGeneratorActivity);
        View siteInputView = LayoutInflater.from(algorithmGeneratorActivity).inflate(R.layout.dialog_algomake_site, null);
        TextView siteInputBotton = siteInputView.findViewById(R.id.algomake_site_input_bt);
        builder.setView(siteInputView)
                .setCancelable(false);

        siteInputDialog = builder.create();
        siteInputBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteInputDialog.dismiss(); // 추후 사이트 주소 디비에 추가
            }
        });
        siteInputDialog.show();
    }

    private void variableInitialization() {
        saveButton = rootView.findViewById(R.id.algomake_save_tv);
        siteInput = rootView.findViewById(R.id.algomake_site_input_bt);
    }
}
