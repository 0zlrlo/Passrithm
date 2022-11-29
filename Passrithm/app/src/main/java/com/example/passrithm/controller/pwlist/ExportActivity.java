package com.example.passrithm.controller.pwlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.databinding.ActivityExportBinding;
import com.example.passrithm.databinding.ActivityMainBinding;

import java.util.List;

public class ExportActivity extends AppCompatActivity {
    private ActivityExportBinding binding;
    List<PasswordBox> testBox = List.of(
            new PasswordBox("naver", "djdejddl@naver.com"),
            new PasswordBox("kakao", "djdejddl@kakao.com")
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.exportTxtLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(Intent.EXTRA_TEXT, getTextString(testBox));
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "앱을 선택해주세요."));
            }
        });
    }

    private String getTextString(List<PasswordBox> testBox) {
        String result = "";

        for (PasswordBox test : testBox) {
            result += test.toString();
        }
        return result;
    }
}
