package com.loopar.zp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopar.zp.Classes.AdapterRsAc;
import com.loopar.zp.Classes.AdapterRsShowWeb;
import com.loopar.zp.Model.ModelAc;
import com.loopar.zp.Model.ModelShowWeb;
import com.loopar.zp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentShowWeb extends Fragment {


    public static FragmentShowWeb newInstance() {

        Bundle args = new Bundle();

        FragmentShowWeb fragment = new FragmentShowWeb();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_web, container ,false);


        RecyclerView rsShowWeb = view.findViewById(R.id.rsShowWeb);
        AdapterRsShowWeb adapterRsShowWeb = new AdapterRsShowWeb(getActivity(),modelShowWebs());
        rsShowWeb.setLayoutManager(new LinearLayoutManager(getActivity() , GridLayoutManager.VERTICAL , false));
        rsShowWeb.setAdapter(adapterRsShowWeb);



        return view;
    }

    private List<ModelShowWeb> modelShowWebs(){
        List<ModelShowWeb> list = new ArrayList<>();

        list.add(new ModelShowWeb("نیوشانوش" , "www.newshanoosh.com"));
        list.add(new ModelShowWeb("اینستاهیرو" , "www.instahero.ir"));
        list.add(new ModelShowWeb("یک در یک" , "www.yekdaryek.ir"));

        return list;
    }
}
