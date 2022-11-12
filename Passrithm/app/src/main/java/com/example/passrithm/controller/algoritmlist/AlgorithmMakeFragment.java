package com.example.passrithm.controller.algoritmlist;

import static android.text.InputType.TYPE_CLASS_NUMBER;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passrithm.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AlgorithmMakeFragment extends Fragment {
    private TextView saveButton;
    private TextView siteInput;
    private AlgorithmGeneratorActivity algorithmGeneratorActivity;
    private ViewGroup rootView;
    private AlertDialog siteInputDialog;
    private AlertDialog algoBoxDialog;
    private List<SelectedBox> selectedBoxes = new ArrayList<>();
    private List<AlgorithmBox> algorithmBoxes = Arrays.asList(
            new AlgorithmBox(0, "사이트 도메인", "사이트주소의 이름이 들어갑니다. (글자수 선택 가능)", "예시) https://passrithm.com -> pa", true, "몇 글자를 넣을건지 선택해주세요"),
            new AlgorithmBox(1, "키워드(0~5자)", "키워드가 그대로 들어갑니다.", "예시) min -> min", true, "0~5자 이내의 키워드를 입력해주세요.\n예시) hong"),
            new AlgorithmBox(2, "문자열", "원하는 문자열이 그대로 들어갑니다.", "예시) helloWorld -> helloWorld", true, "0~10자 이내의 문자열을 입력해주세요."),
            new AlgorithmBox(3, "랜덤 문자열", "문자열 길이 지정 시 랜덤으로 문자열이 생성되어 들어갑니다. (글자수 선택 가능)", "예시) as12k9wdk -> as12k9wdk", true, "숫자를 입력해주세요.")
    );

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        algorithmGeneratorActivity = (AlgorithmGeneratorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_algorithm_maker, container, false);
        variableInitialization();

        setSite();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithmGeneratorActivity.setFragment("passwordRevision");
            }
        });

        SelectedBoxRVAdapter selectedBoxRVAdapter;

        AlgorithmBoxRVAdapter algorithmBoxRVAdapter = new AlgorithmBoxRVAdapter(requireContext(), algorithmBoxes, new AlgorithmBoxRVAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Log.d("click", String.valueOf(position));
                showPopUp(position);
            }
        });
        RecyclerView algorithmBox = rootView.findViewById(R.id.algomake_box_rc);
        algorithmBox.setAdapter(algorithmBoxRVAdapter);

        return rootView;
    }

    private void setSite() { // view로 추후 이동예정. 이유 : 사이트 주소에 대한 데이터를 세팅해줘야함.
        AlertDialog.Builder builder = new AlertDialog.Builder(algorithmGeneratorActivity);
        View siteInputView = LayoutInflater.from(algorithmGeneratorActivity).inflate(R.layout.dialog_algomake_site, null);
        TextView siteInputBotton = siteInputView.findViewById(R.id.algomake_site_input_bt);
        builder.setView(siteInputView)
                .setCancelable(false);

        siteInputDialog = builder.create();
        siteInputBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteInputDialog.dismiss(); // 추후 사이트 주소 디비에 추가
            }
        });
        siteInputDialog.show();
    }

    private void variableInitialization() {
        saveButton = rootView.findViewById(R.id.algomake_save_tv);
        siteInput = rootView.findViewById(R.id.algomake_site_input_bt);
    }

    void showPopUp(int algoId) {
        if (!algorithmBoxes.get(algoId).isPopUp) {
            // return void
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(algorithmGeneratorActivity);
        View mDialogView = LayoutInflater.from(algorithmGeneratorActivity).inflate(R.layout.dialog_algorithm_box, null);
        builder.setView(mDialogView)
                .setCancelable(false);
        algoBoxDialog = builder.create();
        EditText editText = mDialogView.findViewById(R.id.dialog_input_et);
        TextView name = mDialogView.findViewById(R.id.dialog_name_tv);
        TextView explain = mDialogView.findViewById(R.id.dialog_explain_tv);
        TextView doneButton = mDialogView.findViewById(R.id.dialog_done_tv);

        name.setText(algorithmBoxes.get(algoId).name);
        explain.setText(algorithmBoxes.get(algoId).explain);
        if (algoId == 0 || algoId == 3) {
            editText.setInputType(TYPE_CLASS_NUMBER);
        }
        algoBoxDialog.show();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (algoId == 0) {
                }

                if (algoId == 1 || algoId == 2) {
                    selectedBoxes.add(new SelectedBox(algorithmBoxes.get(algoId).name, editText.getText().toString()));
                }

                if (algoId == 3) {
                    Random random = new Random();
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = Integer.parseInt(editText.getText().toString());
                    String generatedString = random.ints(leftLimit,rightLimit + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(targetStringLength)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

                    selectedBoxes.add(new SelectedBox(algorithmBoxes.get(algoId).name, generatedString));
                }
//                VarUtil.glob.chosenBoxRVAdapter.notifyDataSetChanged()
                algoBoxDialog.dismiss();
            }
        });
    }
}