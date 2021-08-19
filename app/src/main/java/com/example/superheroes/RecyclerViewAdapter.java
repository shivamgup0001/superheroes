package com.example.superheroes;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private Context context1;
    LayoutInflater inflater;
    List<data> dataList;
    List<data> dataListAll;
    CardView cardView;
    RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewAdapter(Context context,List<data> dataList,RecyclerViewInterface recyclerViewInterface){
        this.inflater=LayoutInflater.from(context);
        this.dataList=dataList;
        this.recyclerViewInterface=recyclerViewInterface;
        this.dataListAll=dataList;
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
        holder.textView.setText(dataList.get(position).getName());
        holder.textView8.setText("Gender: "+dataList.get(position).getAppearance().getGender());
        Picasso.get().load(dataList.get(position).getImages().getMd()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerViewInterface.onItemClick(position,holder.imageView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<data> filteredList=new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(dataListAll);
            }else {
                for(data d:dataListAll){
                    if(d.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(d);
                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            dataList.clear();
            dataList.addAll((Collection<? extends data>) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView,textView8;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
           imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            textView8=itemView.findViewById(R.id.textView8);
        }
    }
}
