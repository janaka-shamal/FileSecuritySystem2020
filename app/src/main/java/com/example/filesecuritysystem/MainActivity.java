package com.example.filesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.os.Bundle;
import  android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources r=getResources();
        LinearLayout securityIcon = (LinearLayout) findViewById(R.id.securityIcon);
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
    }
}
