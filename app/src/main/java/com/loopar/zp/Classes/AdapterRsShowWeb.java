package com.loopar.zp.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopar.zp.Fragment.FragmentHome;
import com.loopar.zp.Model.ModelShowWeb;
import com.loopar.zp.R;

import java.util.List;

import static com.loopar.zp.Fragment.FragmentHome.url_showWeb;
import static com.loopar.zp.Fragment.FragmentHome.webview;

public class AdapterRsShowWeb extends RecyclerView.Adapter<AdapterRsShowWeb.ViewHolderCoin> {

    private Context context;
    private List<ModelShowWeb> listwebshow;


    String url = "https://www.google.com/search?q=";
    public static String url1;
    String query;

    public AdapterRsShowWeb(Context context, List<ModelShowWeb> listwebshow) {

        this.context = context;
        this.listwebshow = listwebshow;
    }

    @NonNull
    @Override
    public ViewHolderCoin onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_web, viewGroup, false);
        return new ViewHolderCoin(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCoin viewHolderCoin, int i) {
        final ModelShowWeb modelShowWeb = listwebshow.get(i);
        viewHolderCoin.tv_namesite_web.setText(modelShowWeb.getNameSite());
        viewHolderCoin.tv_linksite_web.setText(modelShowWeb.getLinkSite());

        viewHolderCoin.btn_opensite_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, modelShowWeb.getNameSite() + "", Toast.LENGTH_SHORT).show();


                query = modelShowWeb.getNameSite();
                 url1 = url + query;


                Log.e("q-----------" , query);
                webview.getSettings().setLoadsImagesAutomatically(true);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webview.loadUrl(url1);
                url_showWeb = url1;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listwebshow.size();
    }

    class ViewHolderCoin extends RecyclerView.ViewHolder {

        private TextView tv_namesite_web;
        private TextView tv_linksite_web;
        private Button btn_opensite_web;


        public ViewHolderCoin(@NonNull View itemView) {
            super(itemView);

            tv_namesite_web = itemView.findViewById(R.id.tv_namesite_web);
            tv_linksite_web = itemView.findViewById(R.id.tv_linksite_web);
            btn_opensite_web = itemView.findViewById(R.id.btn_opensite_web);


        }
    }
}
