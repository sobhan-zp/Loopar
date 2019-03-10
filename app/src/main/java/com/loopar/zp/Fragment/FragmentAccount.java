package com.loopar.zp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.loopar.zp.Classes.AdapterRsAc;
import com.loopar.zp.Model.ModelAc;
import com.loopar.zp.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class FragmentAccount extends Fragment {

    public static FragmentAccount newInstance() {

        Bundle args = new Bundle();

        FragmentAccount fragment = new FragmentAccount();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account , container , false);


        CircularImageView circularImageView = view.findViewById(R.id.imageViewAccount);
        circularImageView.setBorderColor(getResources().getColor(R.color.borderImage));
        circularImageView.setBorderWidth(10);
        circularImageView.setBackgroundColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
        circularImageView.setImageDrawable(getResources().getDrawable(R.mipmap.bg_login));


        RecyclerView recyclerView = view.findViewById(R.id.rsAccount);
        AdapterRsAc adapterRsAc = new AdapterRsAc(getActivity(),modelAcs());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(adapterRsAc);

        return view;
    }

    private List<ModelAc> modelAcs(){
        List<ModelAc> list = new ArrayList<>();

        list.add(new ModelAc(R.drawable.icon_edit,"ویرایش پروفایل"));
        list.add(new ModelAc(R.drawable.icon_email , "کیف پول"));
        list.add(new ModelAc(R.drawable.icon_bug , "سایت های بازدید شده"));
        return list;
    }
}
