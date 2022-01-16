package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentA fragmentA = new FragmentA();
        Fragment fragment = fm.findFragmentByTag(FragmentA.class.getSimpleName());
        if (!(fragment instanceof FragmentA)){
            Log.d(TAG, "onCreate: " + FragmentA.class.getSimpleName());
            fm.beginTransaction()
                    .add(R.id.frame_container, fragmentA, FragmentA.class.getSimpleName())
                    .commit();
        }


    }
}