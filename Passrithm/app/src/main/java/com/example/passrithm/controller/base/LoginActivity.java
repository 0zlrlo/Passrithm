package com.example.passrithm.controller.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.pwlist.PinSettingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//로그인 유지하는 거 구현하기!!

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatabaseRef;  //실시간 데이터베이스

    EditText email,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Passrithm");
        email=findViewById(R.id.et_loginemail);
        pw=findViewById(R.id.et_loginpassword);

        //로그인 버튼
        Button login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            //성공하면 비밀번호 리스트 화면으로 넘어가고
            //실패하면 재시도 토스트와 함께 일단 그대로,,,
            public void onClick(View view) {
                String strEmail=email.getText().toString();
                String strPw=pw.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();  //현재 액티비티 없앰
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"로그인에 실패하셨습니다",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

        //비밀번호 찾기 버튼
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
