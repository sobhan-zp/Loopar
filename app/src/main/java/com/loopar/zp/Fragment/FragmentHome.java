package com.loopar.zp.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.loopar.zp.Activity.LoadingActivity;
import com.loopar.zp.Activity.MainActivity;
import com.loopar.zp.R;

import static com.loopar.zp.Classes.AdapterRsShowWeb.url1;


public class FragmentHome extends Fragment {

    public static WebView webview;
    public static String url_showWeb;

    public static FragmentHome newInstance() {

        Bundle args = new Bundle();

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        webview = (WebView) view.findViewById(R.id.webview);

        webview.setWebViewClient(new MyBrowser());


        return view;
    }


    private class MyBrowser extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {


            //Toast.makeText(getContext(), "" + url_showWeb, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageFinished(WebView view, String url) {


        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if(url.indexOf("https://newshanoosh.com/") > -1 )
            {
                //Toast.makeText(getContext(), "newshaaaaaaanooosh", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), LoadingActivity.class);
                startActivity(i);
                return false;
            }else {
                Toast.makeText(getContext(), "سایت مورد نظر انتخاب نشده است", Toast.LENGTH_LONG).show();
                return true;
            }



        }


    }
}
