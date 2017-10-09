package com.aframe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aframe.R;
import com.aframe.adapter.LaucherAdapter;
import com.aframelib.util.L;

import java.util.ArrayList;

public class LaucherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);

        initViews();
    }

    private void initViews(){
        ListView lvLaucher = (ListView) findViewById(R.id.lv_laucher);
        LaucherAdapter mAdapter = new LaucherAdapter(getContents(), this);
        lvLaucher.setAdapter(mAdapter);

        lvLaucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:     //测试打印工具
                        L.e("XXXX", "测试打印工具");
                        break;
                }
            }
        });
    }

    private ArrayList<String> getContents(){
        ArrayList<String> contents = new ArrayList<>();
        contents.add("测试打印工具");

        return contents;
    }
}
