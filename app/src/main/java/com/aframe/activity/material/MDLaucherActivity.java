package com.aframe.activity.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aframe.R;
import com.aframe.adapter.LaucherAdapter;
import com.aframelib.util.log.L;

import java.util.ArrayList;

/**
 * Created by ZC on 2017/10/10.
 */

public class MDLaucherActivity extends AppCompatActivity {
    private ArrayList<String> DESCRIPTIONS = new ArrayList<>();
    private static final int MD_SCROLLING = 0;
    private static final int MD_BUTTON_NAVIGATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);

        initDatas();
        initViews();
    }

    private void initViews(){
        ListView lvLaucher = (ListView) findViewById(R.id.lv_laucher);
        LaucherAdapter mAdapter = new LaucherAdapter(DESCRIPTIONS, this);
        lvLaucher.setAdapter(mAdapter);

        lvLaucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.e(DESCRIPTIONS.get(i));
                switch (i){
                    case MD_SCROLLING:          //MD风格：ScrollingActivity
                        startActivity(new Intent(MDLaucherActivity.this, ScrollingActivity.class));
                        break;
                    case MD_BUTTON_NAVIGATION:  //MD风格：ButtonNavigationActivity
                        startActivity(new Intent(MDLaucherActivity.this, ButtonNavigationActivity.class));
                        break;

                }
            }
        });
    }

    private void initDatas(){
        DESCRIPTIONS.add("MD风格：ScrollingActivity");
        DESCRIPTIONS.add("MD风格：ButtonNavigationActivity");
    }
}
