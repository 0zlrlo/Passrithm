package com.example.passrithm.controller.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passrithm.R;
import com.example.passrithm.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    EditText email,id,pw,repw;
    Button confirm, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //기입 항목
        email=findViewById(R.id.et_signupemail);
        id=findViewById(R.id.et_signupid);
        pw=findViewById(R.id.et_signuppassword);
        repw=findViewById(R.id.et_signuprepassword);

        //비밀번호 확인 버튼
        confirm = findViewById(R.id.btn_ok);
        confirm.setOnClickListener(v -> {
            if(pw.getText().toString().equals(repw.getText().toString())){
                confirm.setText("일치");
            }
            else{
                Toast.makeText(SignupActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
        });

        //회원가입 완료 버튼
        signup= findViewById(R.id.btn_signup);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });



    }
}
