package com.example.filesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button img=(Button)findViewById(R.id.img);
        final Button video=(Button)findViewById(R.id.video);
        final Button audio=(Button)findViewById(R.id.audio);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Home.this,ImageEncrypt.class);
                startActivity(n);

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Home.this,VideoEncrypt.class);
                startActivity(n);

            }
        });
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Home.this,AudioEncrypt.class);
                startActivity(n);

            }
        });
    }
}
