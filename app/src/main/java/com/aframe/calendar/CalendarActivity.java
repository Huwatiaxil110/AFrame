package com.aframe.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aframe.R;

/**
 * Created by zc on 2017/10/17.
 */

public class CalendarActivity extends AppCompatActivity{
    Toolbar[] toolbars = new Toolbar[4];
    FloatingActionButton afButton;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initViews();
    }

    private void initViews(){
        initToolbar();
    }

    private void initToolbar(){
        toolbars[0] = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbars[0]);

        toolbars[1] = (Toolbar) findViewById(R.id.toolbar_week1);
        toolbars[2] = (Toolbar) findViewById(R.id.toolbar_week2);
        toolbars[3] = (Toolbar) findViewById(R.id.toolbar_week3);

        afButton = (FloatingActionButton) findViewById(R.id.fab);
        afButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPositionToolbar(position++%4);
            }
        });
    }

    private void setPositionToolbar(int position){
        for(int i=0;i<toolbars.length;i++){
            if(i != position){
                toolbars[i].setVisibility(View.GONE);
            }else{
                toolbars[i].setVisibility(View.VISIBLE);
            }
        }
    }
}
