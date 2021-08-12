package com.example.superheroes;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
List<data> data2;
private RecyclerViewAdapter recyclerViewAdapter;
private ImageView imageView;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data2=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);





        Call<List<data>> call= Apiclient.getapi1().getData();
        call.enqueue(new Callback<List<data>>() {
            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {
                List<data>data1 = response.body();
                for (data d:data1) {
                    data2.add(d);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,data2);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {
                Log.d("myapp","Something went wrong!"+t.getLocalizedMessage());
            }
        });




    }

}
