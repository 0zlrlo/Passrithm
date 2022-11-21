package com.example.passrithm.controller.algorithmmaker;

import static com.example.passrithm.controller.algorithmmaker.Code.ViewType.FOR_BOTTOM_CONTENT;
import static com.example.passrithm.controller.algorithmmaker.Code.ViewType.FOR_TOP_CONTENT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.AlgorithmGeneratorActivity;
import com.example.passrithm.controller.MainActivity;
import com.example.passrithm.controller.pwlist.PinSettingActivity;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.List;
import java.util.stream.Stream;

public class PasswordRevisionFragment extends Fragment {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatabaseRef;  //실시간 데이터베이스
    TextView saveButton;
    TextView confirm;
    TextView bigLetterButton;
    TextView directRevisionButton;
    TextView bigLetterChangeButton;
    EditText resultBox;
    LinearLayout bigLetterBox;
    ViewGroup rootView;
    AlgorithmGeneratorActivity algorithmGeneratorActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        algorithmGeneratorActivity = (AlgorithmGeneratorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_password_revision , container, false);
        variableInitialization();
        resultBox.setText(algorithmGeneratorActivity.result);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Passrithm");

        bigLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBigLetterBoxVisibility(View.VISIBLE);
            }
        });
        bigLetterChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index;
                TextView alert = rootView.findViewById(R.id.last_revision_alert_tv);
                EditText bigLetterEt = rootView.findViewById(R.id.last_revision_et);
                try {
                    index = Integer.parseInt(bigLetterEt.getText().toString()) - 1;
                    char bigLetter = (char) (algorithmGeneratorActivity.result.charAt(index) + 'A' - 'a');

                    StringBuilder stringBuilder = new StringBuilder(algorithmGeneratorActivity.result);
                    stringBuilder.setCharAt(index, bigLetter);
                    algorithmGeneratorActivity.result = stringBuilder.toString();
                    resultBox.setText(algorithmGeneratorActivity.result);

                    alert.setVisibility(View.INVISIBLE);
                } catch (NumberFormatException e) {
                    alert.setVisibility(View.VISIBLE);
                }
            }
        });
        directRevisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBigLetterBoxVisibility(View.GONE);
                resultBox.setEnabled(true);

                //키보드 올리기
                resultBox.setFocusableInTouchMode(true);
                resultBox.requestFocus();
                InputMethodManager imm = (InputMethodManager) algorithmGeneratorActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(resultBox,0);

                //저장하기 구현
                confirm.setVisibility(View.VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) algorithmGeneratorActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(algorithmGeneratorActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                confirm.setVisibility(View.GONE);

                algorithmGeneratorActivity.result = resultBox.getText().toString();
                resultBox.clearFocus();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PostSelectedBox> postSelectedBoxes = new ArrayList<>();
                Stream<SelectedBox> stream = algorithmGeneratorActivity.algorithmMakeFragment.getSelectedBoxes().stream();
                stream.forEach(x -> {
                    postSelectedBoxes.add(new PostSelectedBox(x.name, x.getViewType()));
                });

                postFirebase(postSelectedBoxes);
                algorithmGeneratorActivity.finish();
            }
        });
        return rootView;
    }

    private void setBigLetterBoxVisibility(int visibility){
        bigLetterBox.setVisibility(visibility);
    }

    private void variableInitialization(){
        saveButton = rootView.findViewById(R.id.last_revision_save_tv);
        bigLetterButton = rootView.findViewById(R.id.last_revision_big_letter_tv);
        directRevisionButton = rootView.findViewById(R.id.last_revision_modify_direct_tv);
        bigLetterBox = rootView.findViewById(R.id.last_revision_big_letter_box);
        resultBox = rootView.findViewById(R.id.last_revision_result_string_tv);
        bigLetterChangeButton = rootView.findViewById(R.id.last_revision_big_letter_bt);                confirm = rootView.findViewById(R.id.last_revision_modify_direct_confirm_tv);
        confirm = rootView.findViewById(R.id.last_revision_modify_direct_confirm_tv);
    }

    private void postFirebase(List<PostSelectedBox> postSelectedBoxes) {
        List<String> name = new ArrayList<>();
        int count = 0;

        for (PostSelectedBox postSelectedBox : postSelectedBoxes) {
            if (count == 3) {
                name.add("etc");
                break;
            }

            if (postSelectedBox.getViewType() == FOR_BOTTOM_CONTENT) {
                name.add("for문(끝)");
            } else if (postSelectedBox.getViewType() == FOR_TOP_CONTENT) {
                name.add("for문(시작)");
            } else {
                name.add(postSelectedBox.name);
            }
            count++;
        }

        String result = String.join(" + ", name);
        saveAlgolist(result);
//        mDatabaseRef.child("Algorithm list").child(result).setValue(postSelectedBoxes);
    }

    private void saveAlgolist(String result) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase.child("Passrithm").child("UserAccount").child(user.getUid()).child("algorithmList")
                .push().setValue(result).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(algorithmGeneratorActivity, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

