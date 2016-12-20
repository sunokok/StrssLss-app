package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.strsslss.ExerciseBean;
import com.strsslss.ExerciseLog;
import com.strsslss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnTouch;

/**
 * Created by SunOK on 15/12/2016.
 */

public class ExerciseDoneFragment extends Fragment {


    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_done, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercise Done!");
        ratingBarListener();
    }

    public void ratingBarListener(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                endExercise();
            }
        });
    }

    public void endExercise(){
        ExerciseBean eb = new ExerciseBean("Breathing Exercise", (int) ratingBar.getRating());
        ExerciseLog log = new ExerciseLog(getContext());
        log.addExercise(eb);

        Fragment fragment = new PostExerciseFragment();
        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

}
