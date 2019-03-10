package com.loopar.zp.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopar.zp.Model.ModelShowWeb;
import com.loopar.zp.R;

import java.util.List;

public class AdapterRsShowWeb extends RecyclerView.Adapter<AdapterRsShowWeb.ViewHolderCoin> {

    private Context context;
    private List<ModelShowWeb> listwebshow;

    public AdapterRsShowWeb(Context context , List<ModelShowWeb> listwebshow) {

        this.context = context;
        this.listwebshow = listwebshow;
    }

    @NonNull
    @Override
    public ViewHolderCoin onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_web, viewGroup , false);
        return new ViewHolderCoin(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCoin viewHolderCoin, int i) {
        final ModelShowWeb modelShowWeb = listwebshow.get(i);
        viewHolderCoin.tv_namesite_web.setText(modelShowWeb.getNameSite());
        viewHolderCoin.tv_linksite_web.setText(modelShowWeb.getLinkSite());

        viewHolderCoin.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, modelShowWeb.getNameSite()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listwebshow.size();
    }

    class ViewHolderCoin extends RecyclerView.ViewHolder{

        private TextView tv_namesite_web;
        private TextView tv_linksite_web;
        private Button btn_opensite_web;

        public ViewHolderCoin(@NonNull View itemView) {
            super(itemView);

            tv_namesite_web=itemView.findViewById(R.id.tv_namesite_web);
            tv_linksite_web=itemView.findViewById(R.id.tv_linksite_web);
            btn_opensite_web=itemView.findViewById(R.id.btn_opensite_web);
        }
    }
}
