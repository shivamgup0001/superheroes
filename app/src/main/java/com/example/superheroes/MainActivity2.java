package com.example.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ImageView imageView2;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    List<data> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Intent intent = getIntent();


            imageView2 = findViewById(R.id.imageView2);
            textView2 = findViewById(R.id.textView2);
            textView3 = findViewById(R.id.textView3);
            textView4 = findViewById(R.id.textView4);
            textView5 = findViewById(R.id.textView5);
            textView6 = findViewById(R.id.textView6);
            textView7 = findViewById(R.id.textView7);
            textView2.setText(intent.getStringExtra("name"));
            Picasso.get().load(intent.getStringExtra("url")).into(imageView2);
            textView3.setText("Weight:\t"+intent.getStringExtra("weight"));
            textView4.setText("Height:\t"+intent.getStringExtra("height"));
            textView5.setText("Gender:\t"+intent.getStringExtra("gender"));

            textView6.setText("Race:\t"+intent.getStringExtra("race"));
            textView7.setText("Intelligence:\t"+intent.getIntExtra("intelligence",0)+"\n"+"Strength:\t"+intent.getIntExtra("strength",0)+"\n"+"Speed:\t"+intent.getIntExtra("speed",0)+"\n"
            +"Durability:\t"+intent.getIntExtra("durability",0)+"\n"+"Power:\t"+intent.getIntExtra("power",0)+"\n"+"Combat:\t"+intent.getIntExtra("combat",0));

    }
}
