package com.loopar.zp.Classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopar.zp.Model.ModelAc;
import com.loopar.zp.Model.ModelShowWeb;
import com.loopar.zp.R;

import java.util.List;

public class AdapterRsAc extends RecyclerView.Adapter<AdapterRsAc.ViewHolderAc> {

    private Context context;
    private List<ModelAc> list;

    public AdapterRsAc(Context context , List<ModelAc> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolderAc onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rs_ac,viewGroup,false);
        return new ViewHolderAc(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAc viewHolderAc, int i) {
        final ModelAc modelAc = list.get(i);
        viewHolderAc.title.setText(modelAc.getTitle());
        viewHolderAc.img.setImageResource(modelAc.getImage());

        viewHolderAc.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, modelAc.getTitle()+"", Toast.LENGTH_SHORT).show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderAc extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView title;

        public ViewHolderAc(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img_rs_ac);
            title=itemView.findViewById(R.id.tv_rs_ac);
        }
    }
}
