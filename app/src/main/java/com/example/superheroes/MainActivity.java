package com.example.superheroes;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface, NavigationView.OnNavigationItemSelectedListener {
private RecyclerView recyclerView;
List<data> data1, data2,data3,data4;
private RecyclerViewAdapter recyclerViewAdapter;
private ImageView imageView;
private TextView textView;
LazyLoader lazyloader;
android.widget.SearchView searchView;
private DrawerLayout drawerLayout;
private int check=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout=findViewById(R.id.draw_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        searchView= findViewById(R.id.action_search);

        lazyloader=(LazyLoader)findViewById(R.id.lazyloader);
        LazyLoader loader = new LazyLoader(MainActivity.this, 30, 20, ContextCompat.getColor(MainActivity.this, R.color.loader_selected),
                ContextCompat.getColor(MainActivity.this, R.color.loader_selected),
                ContextCompat.getColor(MainActivity.this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyloader.addView(loader);

        data2=new ArrayList<>();
        data3=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);





        Call<List<data>> call= Apiclient.getapi1().getData();
        call.enqueue(new Callback<List<data>>() {
            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {
                data1 = response.body();
                lazyloader.removeAllViews();
                    for (data d : data1) {
                        data2.add(d);
                        data3.add(d);
                    }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,data2,MainActivity.this::onItemClick);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {
                Log.d("myapp","Something went wrong!"+t.getLocalizedMessage());
            }
        });
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new android.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                data4=new ArrayList<>();
                data4.addAll(data3);
                Log.d("shivam","clicked");
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(),data4, MainActivity.this::onItemClick);
                recyclerView.setAdapter(recyclerViewAdapter);
                return false;

            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_all:
                check=0;
                data2.clear();
                data3.clear();
                for (data d : data1) {
                    data2.add(d);
                    data3.add(d);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,data2,MainActivity.this::onItemClick);
                recyclerView.setAdapter(recyclerViewAdapter);
                break;
            case R.id.nav_male:

                data3.clear();
                data2.clear();
                check=1;
                for(data d:data1){
                    if(d.getAppearance().getGender().toLowerCase().equalsIgnoreCase("male")){
                        data2.add(d);
                        data3.add(d);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,data2,MainActivity.this::onItemClick);
                recyclerView.setAdapter(recyclerViewAdapter);
                break;
            case R.id.nav_female:
                data3.clear();
                data2.clear();
                check=2;
                for(data d:data1){
                    if(d.getAppearance().getGender().toLowerCase().equalsIgnoreCase("female")){
                        data2.add(d);
                        data3.add(d);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,data2,MainActivity.this::onItemClick);
                recyclerView.setAdapter(recyclerViewAdapter);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

    @Override
    public void onItemClick(int position,ImageView imageView) {
        Intent intent =new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("name",recyclerViewAdapter.dataList.get(position).getName());
        intent.putExtra("url",recyclerViewAdapter.dataList.get(position).getImages().md);
        intent.putExtra("weight",recyclerViewAdapter.dataList.get(position).getAppearance().getWeight()[1]);
        intent.putExtra("height",recyclerViewAdapter.dataList.get(position).getAppearance().getHeight()[1]);
        intent.putExtra("gender",recyclerViewAdapter.dataList.get(position).getAppearance().getGender());
        intent.putExtra("race",recyclerViewAdapter.dataList.get(position).getAppearance().getRace());
        intent.putExtra("intelligence",recyclerViewAdapter.dataList.get(position).getPowerstats().getIntelligence());
        intent.putExtra("strength",recyclerViewAdapter.dataList.get(position).getPowerstats().getStrength());
        intent.putExtra("speed",recyclerViewAdapter.dataList.get(position).getPowerstats().getSpeed());
        intent.putExtra("durability",recyclerViewAdapter.dataList.get(position).getPowerstats().getDurability());
        intent.putExtra("power",recyclerViewAdapter.dataList.get(position).getPowerstats().getPower());
        intent.putExtra("combat",recyclerViewAdapter.dataList.get(position).getPowerstats().getCombat());
        ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, ViewCompat.getTransitionName(imageView));
        startActivity(intent,options.toBundle());
    }

}
