package com.example.hero.emotion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView tv;
    private TextView tv1;
    private int lastX;
    private int lastY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=findViewById(R.id.textView4);
        tv1=findViewById(R.id.textView3);
        final Intent intent=new Intent(this,Main3Activity.class);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        tv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();
                int x= (int) motionEvent.getX();
                int y= (int) motionEvent.getY();
                switch(action){
                    case MotionEvent.ACTION_DOWN://按下
                        lastX=x;
                        lastY=y;
                        Log.d("TAG", "按下坐标----：("+lastX+","+lastY+")");
                        break;
                    case MotionEvent.ACTION_MOVE://移动
                        float offsetX=x-lastX;
                        float offsetY=y-lastY;
                        Log.d("TAG", "移动X轴位移量offsetX----："+offsetX);
                        Log.d("TAG", "移动X轴位移量offsetY----："+offsetY);
                        break;
                    case MotionEvent.ACTION_UP://抬起
                        Log.d("TAG", "抬起坐标----：("+x+","+y+")");
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }
}
