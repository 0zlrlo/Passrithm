package com.example.passrithm.controller.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//spf에 자동로그인 여부, 아이디 비밀번호 저장하기
//spf가 트루이면 스플래쉬에서 로그인 진행, flase이면 로그인 창을 넘김

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatabaseRef;  //실시간 데이터베이스

    private EditText email, pw;
    private CheckBox autoLogin;
    private Button login;
    private Boolean loginChecked;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Passrithm");
        email = (EditText) findViewById(R.id.et_loginemail);
        pw = (EditText) findViewById(R.id.et_loginpassword);
        login = (Button) findViewById(R.id.btn_login);
        autoLogin = (CheckBox) findViewById(R.id.cb_login);

        pref = getSharedPreferences("Login", Activity.MODE_PRIVATE);
        String loginEmail = email.getText().toString().trim();
        String loginPw = pw.getText().toString().trim();
//        loginChecked = pref.getBoolean("rememberMe", false);

        if (pref.getBoolean("rememberMe", false)) {
            email.setText(pref.getString("loginEmail", ""));
            pw.setText(pref.getString("loginPw", ""));
            autoLogin.setChecked(true);
        }

        //로그인 버튼
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            //성공하면 비밀번호 리스트 화면으로 넘어가고
            //실패하면 재시도 토스트와 함께 일단 그대로,,,
            public void onClick(View view) {
                String strEmail = email.getText().toString();
                String strPw = pw.getText().toString();

                if (autoLogin.isChecked()) {
                    // (파일 이름, 현재 파일 안에서만 사용) 파일 생성
                    pref = getSharedPreferences("Login", Activity.MODE_PRIVATE);
                    editor = pref.edit();
                    String loginEmail = email.getText().toString().trim();
                    String loginPw = pw.getText().toString().trim();
                    editor.putBoolean("rememberMe", true); // 파일에 자동로그인 체크 입력
                    editor.putString("loginEmail", loginEmail); // 파일에 이메일 정보 입력
                    editor.putString("loginPw", loginPw); // 파일에 이메일 정보 입력
                    editor.commit();
                } else {
                    pref = getSharedPreferences("Login", Activity.MODE_PRIVATE);
                    editor = pref.edit();
                    editor.clear();
                    editor.commit();
                }

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //로그인 성공
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();  //현재 액티비티 없앰
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인에 실패하셨습니다", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

        //비밀번호 찾기 버튼
        TextView find = findViewById(R.id.tv_wantpassfind);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindpasswordActivity.class);
                startActivity(intent);

            }
        });

    }
}






