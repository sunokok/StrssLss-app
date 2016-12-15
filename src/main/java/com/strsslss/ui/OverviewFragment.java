package com.strsslss.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strsslss.R;

/**
 * Created by SunOK on 15/12/2016.
 */

public class OverviewFragment extends Fragment {

    public static OverviewFragment newInstance(){

        OverviewFragment overviewFragment = new OverviewFragment();
        return overviewFragment;
    }

    public OverviewFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        return rootView;
    }

}
