package com.example.hero.emotion;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnAbout;
    private Button btnExit;
    private ImageView iv;
    private AnimationDrawable anim;
    private gradual gradual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv);
        iv.setImageResource(R.drawable.fat_po);
        anim = (AnimationDrawable) iv.getDrawable();
        anim.start();

        btnStart=findViewById(R.id.btn_start);
        btnAbout=findViewById(R.id.btn_about);
        btnExit=findViewById(R.id.btn_exit);

        final Intent intent=new Intent(this,Main2Activity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        final Intent intent1=new Intent(this,aboutActivity.class);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                finish();
                System.exit(0);
            }
        });

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gradual=findViewById(R.id.gg);
                gradual.requestLayout();
                gradual.invalidate();
                handler.postDelayed(this,15000);
            }
        },15000);

    }


}
