package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strsslss.ExerciseBean;
import com.strsslss.R;

/**
 * Created by saul on 20-12-16.
 */
public class LogExerciseEntryFragment extends Fragment {

    private ExerciseBean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_listentry, container, false);
        bean = (ExerciseBean) this.getArguments().getSerializable("bean");
        TextView textView = (TextView) myView.findViewById(R.id.textContainer);
        textView.setText(bean.toString());
        return myView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(bean.getTitle());
    }
}