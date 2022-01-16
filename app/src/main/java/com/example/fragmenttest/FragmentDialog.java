package com.example.fragmenttest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Locale;

public class FragmentDialog extends DialogFragment implements View.OnClickListener{

    Button btnBatal, btnPilih;
    RadioGroup rgOptions;
    RadioButton rbYes, rbNo;
    OnOptionDialogListener optionDialogListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBatal = view.findViewById(R.id.btnBatal);
        btnPilih = view.findViewById(R.id.btnPilih);
        rgOptions = view.findViewById(R.id.rg_options);
        rbYes = view.findViewById(R.id.rbYes);
        rbNo = view.findViewById(R.id.rbNo);

        btnBatal.setOnClickListener(this);
        btnPilih.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnBatal:
                getDialog().cancel();
                break;

            case R.id.btnPilih:

                // membuat variabel untuk mendapatkan id saat memilih isi dari RadioGroup (saat tidak dipilih bernilai -1)
                int checkRadioButtonId = rgOptions.getCheckedRadioButtonId();

                if (checkRadioButtonId != -1) {
                    String answer = null;
                    switch (checkRadioButtonId){
                        case R.id.rbYes:
                            answer = rbYes.getText().toString().trim();
                            break;
                        case R.id.rbNo:
                            answer = rbNo.getText().toString().trim();
                            break;
                        }

                    if (optionDialogListener != null) {
                        // memasukkan nilai variabel answer pada method onOptionChoose pada OnOptionDialogListener
                        optionDialogListener.onOptionChoose(answer);
                    }

                    getDialog().dismiss();

                }
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Fragment fragment = getParentFragment();
        if (fragment instanceof FragmentC) {
            FragmentC fragmentC = (FragmentC) fragment;
            this.optionDialogListener = fragmentC.onOptionDialogListener;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    public interface OnOptionDialogListener{
        void onOptionChoose(String text);
    }


}