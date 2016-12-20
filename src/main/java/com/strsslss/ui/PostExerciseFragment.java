package com.strsslss.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.strsslss.ExerciseBean;
import com.strsslss.ExerciseLog;
import com.strsslss.R;

public class PostExerciseFragment extends ListFragment {

    ExerciseLog log;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        log = new ExerciseLog();

        Object[] exercises = log.getLog().toArray();
        String[] values = new String[exercises.length];
        for (int i = 0; i < exercises.length; i++) {
            ExerciseBean temp = (ExerciseBean) exercises[i];
            values[i] = temp.getTitle();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Post Exercise");
    }

    @Override
    public void onListItemClick(ListView parent, View view, int position, long id) {
        //String itemValue = (String) getListView().getItemAtPosition(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popup, null, false),100,100, true);

        pw.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //Toast.makeText(getContext(), "Position : " + position + ", ListItem : " + itemValue , Toast.LENGTH_LONG).show();
    }
}