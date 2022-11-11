package com.example.passrithm.controller.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.pwlist.PinSettingActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            //성공하면 비밀번호 리스트 화면으로 넘어가고
            //실패하면 재시도 토스트와 함께 일단 그대로,,,
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //회원가입 버튼
        TextView sign = findViewById(R.id.tv_wantsignup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);

            }
        });

        TextView find=findViewById(R.id.tv_wantpassfind);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FindpasswordActivity.class);
                startActivity(intent);

            }
        });
    }
}
