package com.example.passrithm.controller.pwlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.algoritmlist.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.base.FindpasswordActivity;
import com.example.passrithm.controller.base.LoginActivity;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PasswordListFragment extends Fragment {
   private TextView exportButton;
   private TextView lockText;
   private MainActivity mainActivity;
   private ImageView lockButton;
   //private AlertDialog pinInputDialog;
   private View view;
    private DatabaseReference mDatabaseRef;


    //private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=(View) inflater.inflate(R.layout.fragment_password_list, container, false);

        exportButton=view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainActivity.FragmentChange("export");
                Intent intent = new Intent(getActivity(), ExportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        lockButton = view.findViewById(R.id.password_lock);
        lockText=view.findViewById(R.id.password_lock_text);

        lockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                showCustomDialog();
                if(lockButton.getVisibility()==view.VISIBLE){
                    lockButton.setVisibility(view.GONE);
                    lockText.setVisibility(view.GONE);
                }
            }
        });
        return view;
    }
    private void showCustomDialog(){
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Passrithm");
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View pinInputView = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_pw_input, null);
        TextView pinInputBotton = pinInputView.findViewById(R.id.pin_ok);
        builder.setView(pinInputView)
                .setCancelable(false);
        AlertDialog alertDialog=builder.create();
        EditText etPasscode1=pinInputView.findViewById(R.id.passcode1);
        EditText etPasscode2=pinInputView.findViewById(R.id.passcode2);
        EditText etPasscode3=pinInputView.findViewById(R.id.passcode3);
        EditText etPasscode4=pinInputView.findViewById(R.id.passcode4);
        /*if(etPasscode1.isFocused()){
            setEditText(etPasscode1,etPasscode2);
            // etPasscode1.setBackgroundColor(Color.parseColor("#86B0DK"));
        }
        else if(etPasscode2.isFocused()){
            setEditText(etPasscode2,etPasscode3);
            //etPasscode2.setBackgroundColor(Color.parseColor("#86B0DK"));
        }
        else if(etPasscode3.isFocused()){
            setEditText(etPasscode3,etPasscode4);
            // etPasscode3.setBackgroundColor(Color.parseColor("#86B0DK"));
        }
        else if(etPasscode4.isFocused()){
            etPasscode4.getText();
            //etPasscode4.setBackgroundColor(Color.parseColor("#86B0DK"));
        }*/
        String password1= etPasscode1.getText().toString();
        String password2=etPasscode2.getText().toString();
        String password3= etPasscode3.getText().toString();
        String password4= etPasscode4.getText().toString();
        String result=password1+password2+password3+password4;
        pinInputBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String pinNum=mDatabaseRef.child("UserAccount").child(user.getUid()).orderByChild("pinId").toString();
                Log.d("minseon",pinNum);
                if(result.equals(pinNum)){*/
                    alertDialog.dismiss();
                  //}

            }
        });
        alertDialog.show();
    }
    /*private void setEditText(EditText currentEditText, EditText nextEditText) {
        currentEditText.getText();
        nextEditText.requestFocus();
        nextEditText.setText("");
        currentEditText.setBackgroundColor(Color.parseColor("#87CF00"));
    }*/
}


