package com.loopar.zp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.loopar.zp.Classes.AdapterRsAc;
import com.loopar.zp.Classes.AdapterRsShowWeb;
import com.loopar.zp.Classes.AppConfig;
import com.loopar.zp.Model.ModelAc;
import com.loopar.zp.Model.ModelShowWeb;
import com.loopar.zp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentShowWeb extends Fragment {

    private static ViewPager viewPager;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;

    public  FragmentShowWeb newInstance(ViewPager viewPager) {
        FragmentShowWeb.viewPager = viewPager;

        Bundle args = new Bundle();

        FragmentShowWeb fragment = new FragmentShowWeb();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_web, container, false);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefShowWeb);

        modelShowWebs(new onShowWeb() {
            @Override
            public void onRequest(List<ModelShowWeb> showWebList) {
                RecyclerView rsShowWeb = view.findViewById(R.id.rsShowWeb);
                AdapterRsShowWeb adapterRsShowWeb = new AdapterRsShowWeb(getActivity(), showWebList,viewPager);
                rsShowWeb.setLayoutManager(new LinearLayoutManager(getActivity(), GridLayoutManager.VERTICAL, false));
                rsShowWeb.setAdapter(adapterRsShowWeb);
            }
        });

        swipeRefreshLayout.setColorSchemeColors(Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                modelShowWebs(new onShowWeb() {
                    @Override
                    public void onRequest(List<ModelShowWeb> showWebList) {
                        RecyclerView rsShowWeb = view.findViewById(R.id.rsShowWeb);
                        AdapterRsShowWeb adapterRsShowWeb = new AdapterRsShowWeb(getActivity(), showWebList,viewPager);
                        rsShowWeb.setLayoutManager(new LinearLayoutManager(getActivity(), GridLayoutManager.VERTICAL, false));
                        rsShowWeb.setAdapter(adapterRsShowWeb);
                    }
                });
            }
        });

        return view;
    }

    private void modelShowWebs(final onShowWeb onShowWeb) {

        JsonArrayRequest arrayRequest = new JsonArrayRequest(AppConfig.URL_GETSITE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ModelShowWeb> webList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ModelShowWeb showWeb = new ModelShowWeb();
                        showWeb.setNameSite(jsonObject.getString("name"));
                        showWeb.setLinkSite(jsonObject.getString("link"));

                        webList.add(showWeb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                onShowWeb.onRequest(webList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "لطفا اتصال خود به اینترنت را برسی کنید!", Toast.LENGTH_SHORT).show();
            }
        });

        arrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000 , DefaultRetryPolicy.DEFAULT_MAX_RETRIES , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(arrayRequest);
    }

    public interface onShowWeb {
        void onRequest(List<ModelShowWeb> showWebList);
    }
}
