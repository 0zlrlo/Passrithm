package com.example.passrithm.controller.pwlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;

import java.util.List;

public class ExportImgFragment extends Fragment {
    private View view;
    private List<PasswordBox> passwordBoxes;

    public ExportImgFragment(List<PasswordBox> passwordBoxes) {
        this.passwordBoxes = passwordBoxes;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_export_img, container, false);

        TextView passwordTv = view.findViewById(R.id.password_tv);
        passwordTv.setText(setPassword(passwordBoxes));

        return view;
    }

    private String setPassword(List<PasswordBox> passwordBoxes) {
        String result = "";
        for (PasswordBox passwordBox : passwordBoxes) {
            result += passwordBox.toString();
        }

        return result;
    }
}
