package com.aframe.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aframe.R;
import com.aframe.adapter.RxJavaAdapter;
import com.aframelib.util.log.L;
import com.aframelib.view.decoration.LinearLayoutItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by zc on 2017/10/11.
 */

public class RxJavaActivity extends AppCompatActivity{
    private static final String[] METHODS = {"Create", "Just", "From Iterable", "From Array"};
    RecyclerView rvShow;
    RxJavaAdapter rvAdapter;
    ArrayList<String> mItems;
    private Consumer<String> commonConsumer;
    private Observer<String> commonObserver;
    private static final int CODE_CREATE = 0;
    private static final int CODE_JUST = 1;
    private static final int CODE_FROM_ITERABLE = 2;
    private static final int CODE_FROM_ARRAY = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initDatas();
        initViews();
    }

    private void initDatas(){
        mItems = new ArrayList<>(Arrays.asList(METHODS));
    }

    private class RxJavaItemCallback implements RxJavaAdapter.ItemCallback {
        @Override
        public void onItemClick(int position) {
            mItems.set(position, "RxJava用例："+ METHODS[position] + "方式");
            rvAdapter.notifyItemChanged(position);
            switch (position){
                case CODE_CREATE:
                    usageCreate();
                    break;
                case CODE_JUST:
                    usageJust();
                    break;
                case CODE_FROM_ITERABLE:
                    usageFromIterable();
                    break;
                case CODE_FROM_ARRAY:
                    usageArray();
                    break;
            }
        }
    }

    /**
     * 最基本的用法：create方式
     */
    private void usageCreate(){
        Observable<ArrayList<String>> observable = Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<String>> emitter) throws Exception {
                emitter.onNext(mItems);
                emitter.onComplete();
            }
        });
        Observer<ArrayList<String>> observer = new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                L.e("");
            }

            @Override
            public void onNext(@NonNull ArrayList<String> mList) {
                StringBuilder mBuilder = new StringBuilder();
                for(int i=0;i<mList.size();i++){
                    mBuilder.append(mList.get(i)).append(" --> ");
                }
                mBuilder.delete(mBuilder.length()-5, mBuilder.length());
                L.e(mBuilder.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                L.e("");
            }

            @Override
            public void onComplete() {
                L.e("");
            }
        };
        observable.subscribe(observer);
    }

    /**
     * just用法
     * 填充内容，依次onNext()
     * 如果只是接受内容，不考虑结束标志，可以直接用Consumer
     * 如果需要考虑结束标志，可以考虑使用原始的observer
     */
    private void usageJust(){
        Observable<String> observable = Observable.just("北方有佳人 绝世而独立", "一顾倾人城 再顾倾人国", "宁不知倾城与倾国 佳人难再得");
//        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    /**
     * fromIterable用法
     * 填充集合Collection，Collection接口是Iterable接口的子接口
     */
    private void usageFromIterable(){
        Observable<String> observable = Observable.fromIterable(mItems);
//        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    private void usageArray(){
        Observable<String> observable = Observable.fromArray(METHODS);
        // TODO: 2017/10/11 这个网址是RxJava的一些简单使用方式 
//        http://www.jianshu.com/p/d149043d103a
        // TODO: 2017/10/11 此处需要检查是否支持集合，如果支持，为什么报有异常 Unchecked generics array creation for varargs parameter
//        Observable.fromArray(mItems);
        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    private Observer<String> getObserver(){
        if(commonObserver == null){
            commonObserver = new Observer<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    L.e("");
                }

                @Override
                public void onNext(@NonNull String s) {
                    L.e(s);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    L.e("");
                }

                @Override
                public void onComplete() {
                    L.e("");
                }
            };
        }

        return commonObserver;
    }

    private Consumer<String> getConsumer(){
        if(commonConsumer == null){
            commonConsumer = new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    L.e(s);
                }
            };
        }

        return commonConsumer;
    }

    private void initViews(){
        rvShow = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShow.setLayoutManager(mLayoutManager);

        rvAdapter = new RxJavaAdapter(this, mItems);
        rvAdapter.setmItemCallback(new RxJavaItemCallback());
        rvShow.setAdapter(rvAdapter);

        LinearLayoutItemDecoration mItemDecoration2 = new LinearLayoutItemDecoration(this, LinearLayoutItemDecoration.VERTICAL_LIST, R.drawable.shape_rv, false);
        rvShow.addItemDecoration(mItemDecoration2);
        rvShow.setItemAnimator(new DefaultItemAnimator());
    }

}




























