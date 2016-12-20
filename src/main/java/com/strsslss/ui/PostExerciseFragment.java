package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.strsslss.ExerciseBean;
import com.strsslss.ExerciseLog;
import com.strsslss.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PostExerciseFragment extends ListFragment {

    ExerciseLog log;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        writeLog();

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(getContext().getFilesDir(), "exercise_log"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            log = (ExerciseLog) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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
        String itemValue = (String) getListView().getItemAtPosition(position);

        Toast.makeText(getContext(), "Position : " + position + ", ListItem : " + itemValue , Toast.LENGTH_LONG).show();
    }

    private void writeLog() {
        ExerciseLog newLog = new ExerciseLog();

        for (int i = 0; i < 15; i++) {
            newLog.getLog().add(new ExerciseBean("Item " + i, System.currentTimeMillis()));
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(getContext().getFilesDir(), "exercise_log"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(newLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}