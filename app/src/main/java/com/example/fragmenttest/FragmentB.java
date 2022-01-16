package com.example.fragmenttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentB extends Fragment {

    Button btnToFragC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnToFragC = view.findViewById(R.id.btnToFragC);
        btnToFragC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentC fragmentC = new FragmentC();
                Bundle bundle = new Bundle();

                bundle.putString(FragmentC.EXTRA_NAME, "Data ini melalui bundle");
                fragmentC.setArguments(bundle);

                fragmentC.setDescription("Data ini dari getter setter");

                FragmentManager fm = getFragmentManager();
                if (fm != null){
                    fm.beginTransaction()
                            .replace(R.id.frame_container, fragmentC, FragmentC.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

    }
}