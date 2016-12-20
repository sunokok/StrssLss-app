package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.strsslss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SunOK on 15/12/2016.
 */

public class ExerciseFragment extends Fragment {

    @BindView(R.id.exercise_container)
    FrameLayout exercise_container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        ButterKnife.bind(this,view);
        Fragment fragment = new ExerciseStartFragment();
        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.exercise_container, fragment);
            ft.commit();
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercise");
    }
}
