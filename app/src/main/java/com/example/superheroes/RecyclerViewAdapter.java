package com.example.superheroes;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context1;
    LayoutInflater inflater;
    List<data> dataList;

    public RecyclerViewAdapter(Context context,List<data> dataList){
        this.inflater=LayoutInflater.from(context);
        this.dataList=dataList;
        context1=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context1, MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("name",dataList.get(position).getName());
                intent.putExtra("url",dataList.get(position).getImages().sm);
                intent.putExtra("weight",dataList.get(position).getAppearance().getWeight()[1]);
                intent.putExtra("height",dataList.get(position).getAppearance().getHeight()[1]);
                intent.putExtra("gender",dataList.get(position).getAppearance().getGender());
                intent.putExtra("race",dataList.get(position).getAppearance().getRace());
                intent.putExtra("intelligence",dataList.get(position).getPowerstats().getIntelligence());
                intent.putExtra("strength",dataList.get(position).getPowerstats().getStrength());
                intent.putExtra("speed",dataList.get(position).getPowerstats().getSpeed());
                intent.putExtra("durability",dataList.get(position).getPowerstats().getDurability());
                intent.putExtra("power",dataList.get(position).getPowerstats().getPower());
                intent.putExtra("combat",dataList.get(position).getPowerstats().getCombat());
                context1.startActivity(intent);
            }
        });
        holder.textView.setText(dataList.get(position).getName());
        Picasso.get().load(dataList.get(position).getImages().getMd()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
