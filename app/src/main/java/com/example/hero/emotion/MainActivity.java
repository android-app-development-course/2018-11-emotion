package com.example.hero.emotion;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnAbout;
    private Button btnExit;
    private ImageView iv;
    private AnimationDrawable anim;
    private gradual gradual;
    private Intent intent_;
    private myConn conn;
    private music.MyBinder binder;
    private AssetManager assetManager;

    private MediaPlayer player = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.one);
        //mPlayer.start();

        iv=findViewById(R.id.iv);
        iv.setImageResource(R.drawable.fat_po);
        conn=new myConn();
        intent_=new Intent(this,music.class);
        bindService(intent_,conn,BIND_AUTO_CREATE);

        //binder.play();

        anim = (AnimationDrawable) iv.getDrawable();
        anim.start();

        btnStart=findViewById(R.id.btn_start);
        btnAbout=findViewById(R.id.btn_about);
        btnExit=findViewById(R.id.btn_exit);

        assetManager = getAssets();
        player = new MediaPlayer();
        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd("one.mp3");
            player.reset();
            //binder.play(fileDescriptor);
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }



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
                startActivityForResult(intent1,1);
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

    public class myConn implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            binder=(music.MyBinder)service;
        }
        public void onServiceDisconnected(ComponentName name)
        { }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1)
            if(resultCode==1)
            {}
    }



    protected void onDestory()
    {
        super.onDestroy();
    }

}
