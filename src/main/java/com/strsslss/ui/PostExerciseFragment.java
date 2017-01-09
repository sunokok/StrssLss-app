package com.strsslss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.strsslss.ExerciseBean;
import com.strsslss.ExerciseLog;
import com.strsslss.R;

/**
 * Created by saul on 20-12-16.
 */

public class PostExerciseFragment extends ListFragment {

    private ExerciseBean[] list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        ExerciseLog log = new ExerciseLog(this.getContext().getFilesDir().getAbsolutePath());

        Object[] exercises = log.getLog().toArray();
        String[] values = new String[exercises.length];
        list = new ExerciseBean[exercises.length];
        for (int i = 0; i < exercises.length; i++) {
            ExerciseBean temp = (ExerciseBean) exercises[i];
            values[i] = temp.getTitle();
            list[i] = temp;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        this.getActivity().setTitle("Post Exercise");
    }

    @Override
    public void onListItemClick(ListView parent, View view, int position, long id) {
        //String itemValue = (String) getListView().getItemAtPosition(position);
        //Toast.makeText(getContext(), "Position : " + position + ", ListItem : " + itemValue , Toast.LENGTH_LONG).show();
        LogExerciseEntryFragment fragment = new LogExerciseEntryFragment();
        ExerciseBean bean = list[position];
        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        fragment.setArguments(args);

        FragmentTransaction ft = this.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}