package com.example.filesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.os.Bundle;
import  android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickListner();
    }
    public void OnClickListner(){
        access = (Button)findViewById(R.id.accessButton);
        access.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(MainActivity.this,GraphicalPassword.class);
                        startActivity(myIntent);
                    }
                }
        );
    }
}
