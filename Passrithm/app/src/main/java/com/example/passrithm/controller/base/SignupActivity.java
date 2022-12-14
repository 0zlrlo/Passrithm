package com.example.passrithm.controller.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.pwlist.ExportActivity;
import com.example.passrithm.controller.pwlist.PinSettingActivity;
import com.example.passrithm.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatabaseRef;  //실시간 데이터베이스

    private EditText email,id,pw,repw;
    private Button confirm, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Passrithm");

        //기입 항목
        email = findViewById(R.id.et_signupemail);
        id = findViewById(R.id.et_signupid);
        pw = findViewById(R.id.et_signuppassword);
        repw = findViewById(R.id.et_signuprepassword);
        //비밀번호 확인 버튼
        signup=findViewById(R.id.btn_signup);

        //비밀번호 찾기 버튼
        confirm = findViewById(R.id.btn_ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "비밀번호가 일치합니다", Toast.LENGTH_SHORT).show();
                Log.d("Passrithm","match password");
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = email.getText().toString();
                String strId = id.getText().toString();
                String strPw = pw.getText().toString();
                String strRepw = repw.getText().toString();
                String state="false";
                if (strPw.equals(strRepw)) {
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPw).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                UserAccount account = new UserAccount();
                                account.setIdtoken(firebaseUser.getUid());
                                account.setEmailId(firebaseUser.getEmail());
                                account.setPasswordId(strPw);
                                account.setNameId(strId);
                                account.setPinId("");
                                //setvalue는 데이터베이스에 인서트하는 행위
                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                                Toast.makeText(SignupActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PinSettingActivity.class);
                                intent.putExtra("state", "false");
                                startActivity(intent);
                                Log.d("Passrithm","sign up");
                            } else {
                                //if (task.getException().toString() != null) {
                                Toast.makeText(SignupActivity.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                                //}
                            }
                        }
                    });
                }
                else{
                        Toast.makeText(SignupActivity.this, "다시 한 번 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                switch (v.getId()){
//                    case R.id.btn_signup:
//                    signUp();
//                    break;
//                }
//            }
//        };
//
//    public void signUp() {
//        String strEmail = email.getText().toString();
//        String strId = id.getText().toString();
//        String strPw = pw.getText().toString();
//        String strRepw = repw.getText().toString();
//        String state="false";
//
//        if (strPw.equals(strRepw)) {
//            //파이어베이스 auth 진행
//            mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPw).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                        UserAccount account = new UserAccount();
//                        account.setIdtoken(firebaseUser.getUid());
//                        account.setEmailId(firebaseUser.getEmail());
//                        account.setPasswordId(strPw);
//                        account.setNameId(strId);
//                        account.setPinId("");
//                        //setvalue는 데이터베이스에 인서트하는 행위
//                        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
//                            Toast.makeText(SignupActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), PinSettingActivity.class);
//                        intent.putExtra("state", "false");
//                        startActivity(intent);
//                    }
//                    else {
//                        //if (task.getException().toString() != null) {
//                            Toast.makeText(SignupActivity.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
//                        //}
//                    }
//                }
//            });
//        }
//        else{
//            Toast.makeText(SignupActivity.this, "다시 한 번 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
//        }
//
//        //회원가입 완료 버튼
////        signup= findViewById(R.id.btn_signup);
////        signup.setOnClickListener(v -> {
//            Toast.makeText(SignupActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
////        });
//    }
}
