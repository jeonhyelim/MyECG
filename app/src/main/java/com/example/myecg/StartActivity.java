package com.example.myecg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class StartActivity extends AppCompatActivity {

    private Button start_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView heartbeat = (ImageView)findViewById(R.id.heart_beat);
        Glide.with(this).load(R.raw.heartbeat).into(heartbeat);

        start_bt  = findViewById(R.id.start_bt);



        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}