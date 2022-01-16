package com.example.fragmenttest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;

public class FragmentC extends Fragment {

    TextView tvName, tvDesc;
    Button btnDialog, btnActivity;
    public static String EXTRA_NAME = "extra_name";
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tvReceive1);
        tvDesc = view.findViewById(R.id.tvReceive2);
        btnDialog = view.findViewById(R.id.btnShowDialog);
        btnActivity = view.findViewById(R.id.btnShowActivity);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialog dialog = new FragmentDialog();
                FragmentManager fm = getChildFragmentManager();
                dialog.show(fm, FragmentDialog.class.getSimpleName());
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);

            }
        });

    }

    // terhubung dengan method OnOptionDialogListener FragmentDialog.java
    FragmentDialog.OnOptionDialogListener onOptionDialogListener = new FragmentDialog.OnOptionDialogListener() {
        @Override
        public void onOptionChoose(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String name = getArguments().getString(EXTRA_NAME);
        tvName.setText(name);

        tvDesc.setText(getDescription());
    }

}