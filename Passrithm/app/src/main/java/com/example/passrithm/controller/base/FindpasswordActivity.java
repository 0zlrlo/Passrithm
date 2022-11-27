package com.example.passrithm.controller.base;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindpasswordActivity extends AppCompatActivity {
    //define view objects
    private EditText editTextUserEmail;
    private EditText editTextUserId;
    private Button buttonFind;
    private TextView textviewMessage;
    private ProgressDialog progressDialog;
    //define firebase object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);

        editTextUserEmail = (EditText) findViewById(R.id.et_findemail);
        editTextUserId = (EditText) findViewById(R.id.et_findid);
        buttonFind = (Button) findViewById(R.id.btn_modify);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonFind.setOnClickListener(this::onClick);

    }
    public void onClick(View view) {
            if(view == buttonFind){
                progressDialog.setMessage("처리중입니다. 잠시 기다려 주세요...");
                progressDialog.show();
                //비밀번호 재설정 이메일 보내기
                String emailAddress = editTextUserEmail.getText().toString().trim();
                firebaseAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(FindpasswordActivity.this, "이메일을 보냈습니다.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                } else {
                                    Toast.makeText(FindpasswordActivity.this, "메일 보내기 실패하였습니다", Toast.LENGTH_SHORT).show();
                                }
                                progressDialog.dismiss();
                            }
                        });

            }
        }
    }
