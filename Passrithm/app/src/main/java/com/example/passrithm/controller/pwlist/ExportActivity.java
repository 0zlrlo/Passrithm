package com.example.passrithm.controller.pwlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.passrithm.R;
import com.example.passrithm.controller.base.LoginActivity;
import com.example.passrithm.databinding.ActivityExportBinding;
import com.example.passrithm.databinding.ActivityMainBinding;

import java.util.List;

public class ExportActivity extends AppCompatActivity {
    private ActivityExportBinding binding;
    List<PasswordBox> passwordBoxes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        passwordBoxes = (List<PasswordBox>) intent.getSerializableExtra("passwordBox");

        binding.exportTxtLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(Intent.EXTRA_TEXT, getTextString(passwordBoxes));
                Log.d("Passrithm", "passwordBoxes : " + passwordBoxes.toString());
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "앱을 선택해주세요."));
            }
        });

        binding.imageTxtLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                ExportImgFragment exportImgFragment = new ExportImgFragment(passwordBoxes);
                transaction.add(R.id.export_frm, exportImgFragment);
                transaction.commit();

                Toast.makeText(ExportActivity.this, "화면을 캡쳐해서 공유해주세요!", Toast.LENGTH_SHORT).show();
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
