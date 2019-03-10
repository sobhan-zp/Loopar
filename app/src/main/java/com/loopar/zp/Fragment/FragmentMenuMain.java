package com.loopar.zp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopar.zp.R;


public class FragmentMenuMain extends Fragment {

    public static FragmentMenuMain newInstance() {

        Bundle args = new Bundle();

        FragmentMenuMain fragment = new FragmentMenuMain();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_main,container,false);
        return view;
    }
}
