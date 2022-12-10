package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.passrithm.R;
import com.example.passrithm.controller.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.base.SignupActivity;
import com.example.passrithm.controller.base.UserAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PinSettingActivity extends AppCompatActivity {
    //private boolean changePwdUnlock = false;
    private ImageView lockButton;

    EditText etPasscode1;
    EditText etPasscode2;
    EditText etPasscode3;
    EditText etPasscode4;
     Button[] btnTime;
    private DatabaseReference mDatabaseRef;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_setting);


        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Passrithm");
        //edittext
        etPasscode1=findViewById(R.id.passcode1);
        etPasscode2=findViewById(R.id.passcode2);
        etPasscode3=findViewById(R.id.passcode3);
        etPasscode4=findViewById(R.id.passcode4);

        btnTime = new Button[12];
        int[] btnId={R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,R.id.btn9, R.id.btnErase,R.id.btn_ok};
        for(int i=0;i< btnTime.length;i++){
            this.btnTime[i]=findViewById(btnId[i]);
        }
        for(int i=0;i< btnTime.length;i++){
            this.btnTime[i].setOnClickListener(btnListener);
        }




    }


    private Button.OnClickListener btnListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            int currentValue = -1;
            switch (v.getId()) {
                case R.id.btn0:
                    currentValue = 0;
                    break;
                case R.id.btn1:
                    currentValue = 1;
                    break;
                case R.id.btn2:
                    currentValue = 2;
                    break;
                case R.id.btn3:
                    currentValue = 3;
                    break;
                case R.id.btn4:
                    currentValue = 4;
                    break;
                case R.id.btn5:
                    currentValue = 5;
                    break;
                case R.id.btn6:
                    currentValue = 6;
                    break;
                case R.id.btn7:
                    currentValue = 7;
                    break;
                case R.id.btn8:
                    currentValue = 8;
                    break;
                case R.id.btn9:
                    currentValue = 9;
                    break;
                case R.id.btn_ok:
                    onOk();
                    break;
                case R.id.btnErase:
                    onDeleteKey();
                    break;
            }
            String strCurrentValue = Integer.toString(currentValue);

            if(currentValue != -1){
                if(etPasscode1.isFocused()){
                    setEditText(etPasscode1,etPasscode2,strCurrentValue);
                    etPasscode1.setBackgroundResource(R.drawable.change_circle);
                }
                else if(etPasscode2.isFocused()){
                    setEditText(etPasscode2,etPasscode3,strCurrentValue);
                    etPasscode2.setBackgroundResource(R.drawable.change_circle);
                }
                else if(etPasscode3.isFocused()){
                    setEditText(etPasscode3,etPasscode4,strCurrentValue);
                    etPasscode3.setBackgroundResource(R.drawable.change_circle);
                }
                else if(etPasscode4.isFocused()){
                    etPasscode4.setText(strCurrentValue);
                    etPasscode4.setBackgroundResource(R.drawable.change_circle);
                }

            }


        }






        private void setEditText(EditText currentEditText, EditText nextEditText, String strCurrentValue) {
            currentEditText.setText(strCurrentValue);
            nextEditText.requestFocus();
            nextEditText.setText("");
          //  currentEditText.setBackgroundColor(Color.parseColor("#87CF00"));
        }


        private void onDeleteKey() {

            if(etPasscode1.isFocused()){
                etPasscode1.setText("");
                etPasscode1.setBackgroundResource(R.drawable.circle);
            }
            else if(etPasscode2.isFocused()){
                etPasscode1.setText("");
                etPasscode1.requestFocus();
                etPasscode2.setBackgroundResource(R.drawable.circle);
            }
            else if(etPasscode3.isFocused()){
                etPasscode2.setText("");
                etPasscode2.requestFocus();
                etPasscode3.setBackgroundResource(R.drawable.circle);
            }
            else if(etPasscode4.isFocused()){
                etPasscode3.setText("");
                etPasscode3.requestFocus();
                etPasscode4.setBackgroundResource(R.drawable.circle);
            }
        }

        private void onOk() {
            Intent before_intent = getIntent();
            String state=before_intent.getStringExtra("state");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(state==null||!state.equals("false")){
            unlock();}
            else if(state.equals("false")){
                setting();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("setting", "password_list");

                startActivity(intent);

            }

        }
        private String inputPassword(){
            String password1= etPasscode1.getText().toString();
           // Log.d("input1",password1);
            String password2=etPasscode2.getText().toString();
            //Log.d("input2",password2);
            String password3= etPasscode3.getText().toString();
            //Log.d("input3",password3);
            String password4= etPasscode4.getText().toString();
            //Log.d("input4",password4);
            return password1+password2+password3+password4;
        }
        public void setting(){
            Log.d("seon",inputPassword());
            String strPin = inputPassword();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(strPin!=null)
            mDatabaseRef.child("UserAccount").child(user.getUid()).child("pinId").setValue(strPin);
        }
        public void unlock(){

            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //DatabaseReference conditionRef=mRootRef.child("Passrithm").child(user.getUid()).child("pinId");
            String strPin = inputPassword();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   String pinNum= snapshot.child("Passrithm").child("UserAccount").child(user.getUid()).child("pinId").getValue(String.class);
                   // String pinNum=.child(user.getUid()).child("pinId").getValue(String.class);
                    //Log.d("uid_u",user.getUid());
                    Log.d("input1",pinNum);
                    Log.d("uid_u",user.getUid());
                    if(strPin!=null){
                        if(strPin.equals(pinNum)){
//                            Toast.makeText(PinSettingActivity.this, "잠금이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("setting", "password_list");
                            intent.putExtra("state","true");
                            startActivity(intent);
                            finish();

                        }else Toast.makeText(PinSettingActivity.this, "다시시도하십시오.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    };





}

