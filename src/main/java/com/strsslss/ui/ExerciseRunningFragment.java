package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.strsslss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SunOK on 15/12/2016.
 */

public class ExerciseRunningFragment extends Fragment {

    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    static int runningSec = 5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_running, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercise Running");

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                double progress = 0;
                while (progressBar.getProgress() < 100) {
                    progress = progress + (100.0 / runningSec);
                    progressBar.setProgress((int) progress);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Go to done
                Fragment fragment = new ExerciseDoneFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragment);
                    ft.commit();
                }
            }
        }).start();
    }
}
