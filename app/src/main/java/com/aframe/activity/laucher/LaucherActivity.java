package com.aframe.activity.laucher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aframe.R;
import com.aframe.activity.rxjava.RxJavaActivity;
import com.aframe.activity.material.MDLaucherActivity;
import com.aframe.activity.toast.ToastActivity;
import com.aframe.adapter.LaucherAdapter;
import com.aframelib.util.log.L;
import com.aframelib.util.toast.T;

import java.util.ArrayList;

public class LaucherActivity extends AppCompatActivity {
    private static final ArrayList<String> DESCRIPTIONS = new ArrayList<>();
    private static final int CODE_LOG = 0;
    private static final int CODE_TOAST = 1;
    private static final int CODE_METARIAL_DESIGN = 2;
    private static final int CODE_RXJAVA = 3;

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
                    case CODE_LOG:             //测试打印工具
                        T.showToast(LaucherActivity.this, getDescription());
                        break;
                    case CODE_TOAST:                 //测试Toast工具
                        startActivity(new Intent(LaucherActivity.this, ToastActivity.class));
                        break;
                    case CODE_METARIAL_DESIGN:       //MD风格：ScrollingActivity
                        startActivity(new Intent(LaucherActivity.this, MDLaucherActivity.class));
                        break;
                    case CODE_RXJAVA:
                        startActivity(new Intent(LaucherActivity.this, RxJavaActivity.class));
                        break;

                }
            }
        });

    }

    private void initDatas(){
        DESCRIPTIONS.clear();

        DESCRIPTIONS.add("测试Log工具");
        DESCRIPTIONS.add("测试Toast工具");
        DESCRIPTIONS.add("Metarial Design风格");
        DESCRIPTIONS.add("RxJava用例");
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     */
    private static String getDescription() {
        String[] infos = new String[]{ "", "", "" };
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length >= 5) {
            infos[0] = elements[4].getClassName().substring(elements[4].getClassName().lastIndexOf(".") + 1, elements[4].getClassName().length()-2);
            infos[1] = elements[4].getMethodName();
            infos[2] = "line = " + elements[4].getLineNumber();
        } else {
            L.e("elements.length < 5");
        }

        return infos[0] + " -> " + infos[1] + "() -> " + infos[2] + "\n";
    }
}
