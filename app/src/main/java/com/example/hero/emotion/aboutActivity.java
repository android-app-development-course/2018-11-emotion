package com.example.hero.emotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class aboutActivity  extends AppCompatActivity
{

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        btnBack=findViewById(R.id.btn_back);

        final Intent intenta=new Intent(this,MainActivity.class);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1,intenta);
                finish();
            }
        });
    }
}
