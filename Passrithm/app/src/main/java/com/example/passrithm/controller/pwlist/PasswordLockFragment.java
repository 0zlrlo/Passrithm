package com.example.passrithm.controller.pwlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.passrithm.R;
import com.example.passrithm.controller.MainActivity;

import org.jetbrains.annotations.Nullable;

public class PasswordLockFragment extends Fragment {
    private TextView lockText;
    private ImageView lockButton;
    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_password_lock, container, false);

        lockButton = view.findViewById(R.id.password_lock);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainActivity.FragmentChange("export");
                Intent intent = new Intent(getActivity(), PinSettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                getActivity().finish();

               /* Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                getParentFragmentManager().setFragmentResult("requestKey", result);*/
            }
        });
        return view;
    }
}
