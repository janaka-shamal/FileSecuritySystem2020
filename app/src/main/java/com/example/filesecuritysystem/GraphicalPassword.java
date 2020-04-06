package com.example.filesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.akaita.android.circularseekbar.CircularSeekBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class GraphicalPassword extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_password);
        final EditText password=(EditText)findViewById(R.id.password);
        final StringBuilder pw=new StringBuilder();
        password.setText(pw);
        final CircularSeekBar circularSeekBar1=(CircularSeekBar)findViewById(R.id.seekbar1);
        final CircularSeekBar circularSeekBar2=(CircularSeekBar)findViewById(R.id.seekbar2);
        final CircularSeekBar circularSeekBar3=(CircularSeekBar)findViewById(R.id.seekbar3);
        final CircularSeekBar circularSeekBar4=(CircularSeekBar)findViewById(R.id.seekbar4);
        circularSeekBar1.setRingColor(Color.YELLOW);
        circularSeekBar1.setProgressTextFormat(new DecimalFormat("###,###,##0.0"));
        circularSeekBar1.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Checked",Snackbar.LENGTH_SHORT)
                        .show();
                pw.append("1");
                DecimalFormat d=new DecimalFormat("###,###,##0.0");
                pw.append(d.format(circularSeekBar1.getProgress()));
                password.setText(pw);
                seekBar.setProgress(0);
                circularSeekBar1.setEnabled(false);
            }

        });
        circularSeekBar1.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {
                if (progress<25){seekBar.setRingColor(Color.YELLOW);}
                else if(progress<50){seekBar.setRingColor(Color.GREEN);}
                else if(progress<75){seekBar.setRingColor(Color.BLUE);}
                else if(progress<100){seekBar.setRingColor(Color.RED);}
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });
//SeekBar2
        circularSeekBar2.setRingColor(Color.GREEN);
        circularSeekBar2.setProgressTextFormat(new DecimalFormat("###,###,##0.0"));
        circularSeekBar2.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Checked",Snackbar.LENGTH_SHORT)
                        .show();
                pw.append("2");
                DecimalFormat d=new DecimalFormat("###,###,##0.0");
                pw.append(d.format(circularSeekBar2.getProgress()));
                password.setText(pw);
                seekBar.setProgress(0);
                circularSeekBar2.setEnabled(false);
            }

        });
        circularSeekBar2.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {
                if (progress<25){seekBar.setRingColor(Color.GREEN);}
                else if(progress<50){seekBar.setRingColor(Color.BLUE);}
                else if(progress<75){seekBar.setRingColor(Color.RED);}
                else if(progress<100){seekBar.setRingColor(Color.YELLOW);}
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });
        //SeekBar3
        circularSeekBar3.setRingColor(Color.BLUE);
        circularSeekBar3.setProgressTextFormat(new DecimalFormat("###,###,##0.0"));
        circularSeekBar3.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Checked",Snackbar.LENGTH_SHORT)
                        .show();
                DecimalFormat d=new DecimalFormat("###,###,##0.0");
                pw.append("3");
                pw.append(d.format(circularSeekBar3.getProgress()));
                password.setText(pw);
                seekBar.setProgress(0);
                circularSeekBar3.setEnabled(false);
            }

        });
        circularSeekBar3.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {
                if (progress<25){seekBar.setRingColor(Color.BLUE);}
                else if(progress<50){seekBar.setRingColor(Color.RED);}
                else if(progress<75){seekBar.setRingColor(Color.YELLOW);}
                else if(progress<100){seekBar.setRingColor(Color.GREEN);}
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });
        //SeekBar4
        circularSeekBar4.setRingColor(Color.RED);
        circularSeekBar4.setProgressTextFormat(new DecimalFormat("###,###,##0.0"));
        circularSeekBar4.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Checked",Snackbar.LENGTH_SHORT)
                        .show();
                DecimalFormat d=new DecimalFormat("###,###,##0.0");
                pw.append("4");
                pw.append(d.format(circularSeekBar4.getProgress()));
                password.setText(pw);
                seekBar.setProgress(0);
                circularSeekBar4.setEnabled(false);
            }

        });
        circularSeekBar4.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {
                if (progress<25){seekBar.setRingColor(Color.RED);}
                else if(progress<50){seekBar.setRingColor(Color.YELLOW);}
                else if(progress<75){seekBar.setRingColor(Color.GREEN);}
                else if(progress<100){seekBar.setRingColor(Color.BLUE);}
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });

    }
}
