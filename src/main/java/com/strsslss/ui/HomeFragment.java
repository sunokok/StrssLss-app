package com.strsslss.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strsslss.R;
/**
 * Created by SunOK on 13/12/2016.
 */

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(){

        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    public HomeFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

}
